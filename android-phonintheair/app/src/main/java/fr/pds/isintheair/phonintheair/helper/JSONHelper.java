package fr.pds.isintheair.phonintheair.helper;

import com.google.gson.Gson;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class JSONHelper {
    public static String serialize(Object object, Class clazz) {
        Gson gson = new Gson();

        return gson.toJson(object, clazz);
    }

    public static Object deserialize(String json, Class clazz) {
        Gson gson = new Gson();

        return gson.fromJson(json, clazz);
    }
}
