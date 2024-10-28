package baseNoStates.requests;

import baseNoStates.areas.DirectoryAreas;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import baseNoStates.*;

import baseNoStates.doorstates.Actions;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RequestArea implements Request { //Classe que gestiona les àrees de tot l'edifici
  private final String credential;
  private final String action;
  private final String areaId;
  private final LocalDateTime now;
  private ArrayList<RequestReader> requests = new ArrayList<>();

  public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
    this.credential = credential;
    this.areaId = areaId;
    assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
        : "invalid action " + action + " for an area request";
    this.action = action;
    this.now = now;
  }

  public String getAction() {
    return action;
  }

  @Override
  public JSONObject answerToJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("action", action);
    json.put("areaId", areaId);
    JSONArray jsonRequests = new JSONArray();
    for (RequestReader rd : requests) {
      jsonRequests.put(rd.answerToJson());
    }
    json.put("requestsDoors", jsonRequests);
    json.put("todo", "request areas not yet implemented");
    return json;
  }

  @Override
  public String toString() {
    String requestsDoorsStr;
    if (requests.size() == 0) {
      requestsDoorsStr = "";
    } else {
      requestsDoorsStr = requests.toString();
    }
    return "Request{"
        + "credential=" + credential
        + ", action=" + action
        + ", now=" + now
        + ", areaId=" + areaId
        + ", requestsDoors=" + requestsDoorsStr
        + "}";
  }


  public void process() {
    Area area = DirectoryAreas.findAreaById(areaId); //Cerca l'àrea que s'ha sol·licitat modificar
    if (area != null) {
      for (Door door : area.getDoorsGivingAccess()) { //Iterem totes les portes d'aquesta àrea
        RequestReader requestReader = new RequestReader(credential, action, now, door.getId()); //Sol·licitem l'acció desitjada
        requestReader.process(); //Gestionem si és possible dur a terme l'acció
        requests.add(requestReader);
      }
    }

  }
}
