package com.fenghaha.mvpdemo.util;

import android.util.Log;



import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by FengHaHa on2018/5/25 0025 12:13
 */
public class JsonParser {
    private static final String TAG = "JsonParser";

    public static boolean has(String data, String name) {
        try {
            JSONObject js = new JSONObject(data);
            //检查存在才返回
            return js.has(name);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("tag", e.toString());
        }
        return false;
    }



    public static String getElement(String data, String name) {
        try {
            JSONObject js = new JSONObject(data);
            //检查存在才返回
            if (js.has(name)) {
                return js.getString(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("tag", e.toString());
        }
        return null;
    }
}
