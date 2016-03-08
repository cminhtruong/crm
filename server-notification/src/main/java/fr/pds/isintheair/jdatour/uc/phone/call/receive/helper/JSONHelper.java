package fr.pds.isintheair.jdatour.uc.phone.call.receive.helper;

import com.google.gson.Gson;

public class JSONHelper {
    @SuppressWarnings("unchecked")
    public static String serialize(Object object, Class clazz) {
        Gson gson = new Gson();

        return gson.toJson(object, clazz);
    }

    @SuppressWarnings("unchecked")
    public static Object deserialize(String json, Class clazz) {
        Gson gson = new Gson();

        return gson.fromJson(json, clazz);
    }
}
