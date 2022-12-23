package utilities.dataHelpers;

import org.json.JSONException;

public class HelperFunctions {

    public static boolean checkJson(String str){
        try {
            new org.json.JSONObject(str);
        }catch (JSONException e){
            try {
                new org.json.JSONArray(str);
            }catch (Throwable exception){
                return false;
            }
        }
        return true;
    }
}
