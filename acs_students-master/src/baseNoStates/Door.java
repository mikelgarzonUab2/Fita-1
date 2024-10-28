package baseNoStates;

import baseNoStates.areas.Space;
import baseNoStates.doorstates.Actions;
import baseNoStates.doorstates.DoorStates;
import baseNoStates.doorstates.Locked;
import baseNoStates.requests.RequestReader;

import org.json.JSONException;
import org.json.JSONObject;

public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorStates state;
  private Space fromSpace;
  private Space toSpace;

  public Door(String id, Space from, Space to) {
    this.id = id;
    closed = true;
    fromSpace = from;
    toSpace = to;
    state = new Locked(this);
  }

  public Space getFromSpace() {
    return fromSpace;
  }

  public Space getToSpace() {
    return toSpace;
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        state.unlocked_shortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public void setState(DoorStates nouEstat) {
    this.state = nouEstat;
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClose(boolean c) {
    this.closed = c;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return state.getNom();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
