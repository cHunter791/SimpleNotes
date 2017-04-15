package com.christopher.hunter.simplenotes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Christopher on 15/04/2017.
 */

public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public JSONObject noteToJson(Note note) {
        Log.d(TAG, "noteToJson: converting note to JSON");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("_id", note.get_Id());
            jsonObject.put("title", note.getTitle());
            jsonObject.put("content", note.getContent());
        } catch (JSONException e) {
            Log.e(TAG, "noteToJson: JSONException: " + e);
        }

        return jsonObject;
    }

//    public Note jsonToNote(JSONObject jsonObject) {
//        Log.d(TAG, "jsonToNote: converting JSON to note");
//
//        Note note;
//
//        try {
//            note = new Note(
//                    jsonObject.getLong("_id"),
//                    jsonObject.getString("title"),
//                    jsonObject.getString("content")
//            );
//        } catch (JSONException e) {
//            Log.e(TAG, "jsonToNote: JSONException: " + e);
//        }
//
//        return note;
//    }
}
