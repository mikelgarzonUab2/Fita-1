package baseNoStates.requests;

import baseNoStates.areas.DirectoryAreas;
import baseNoStates.users.DirectoryUserGroups;
import baseNoStates.Door;
import baseNoStates.areas.Space;
import baseNoStates.users.User;
import baseNoStates.users.UserGroup;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestReader implements Request { // Classe que gestiona les peticions
  private final String credential; // who
  private final String action; // what
  private final LocalDateTime now; // when
  private final String doorId; // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }

  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
        + "credential=" + credential
        + ", userName=" + userName
        + ", action=" + action
        + ", now=" + now
        + ", doorID=" + doorId
        + ", closed=" + doorClosed
        + ", authorized=" + authorized
        + ", reasons=" + reasons
        + "}";
  }

  public JSONObject answerToJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  public void process() { // Cerca la infromació necesari per realitzar la sol·licitut d'autoritzar
    User user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    door.processRequest(this); // Envia la sol·licitut a la porta per veure si es podrà fer l'acció o no
    doorClosed = door.isClosed();
  }

  private void authorize(User user, Door door) { // Mira si les condicions del usuari permeten realització l'acció
    if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
    } else {
      UserGroup u = user.getPare();

      Space from = door.getFromSpace();
      Space to = door.getToSpace();

      // Comprova si pot fer la sol·licitut en aquest moment, sí pot accedir tant a
      // l'àrea que està com a la que vol anar i tambés gestiona si pot fer l'acció
      // que sol·licita.
      authorized = u.canSendRequests(now) &&
          u.canBeInTheSpace(from) && u.canBeInTheSpace(to) &&
          u.canDoAction(action);

      if (!authorized) {
        addReason("L'usuari no té accés a l'espai");
      }
    }
  }
}
