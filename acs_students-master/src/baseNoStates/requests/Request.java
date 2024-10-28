package baseNoStates.requests;

import org.json.JSONException;
import org.json.JSONObject;

public interface Request {
  JSONObject answerToJson() throws JSONException;

  String toString();

  void process() throws JSONException;
}
