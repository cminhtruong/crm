package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.helper;

import com.google.gson.Gson;

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
