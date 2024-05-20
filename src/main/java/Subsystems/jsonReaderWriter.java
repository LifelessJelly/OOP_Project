package Subsystems;

import com.google.gson.Gson;

public class jsonReaderWriter {
    private static final Gson gson = new Gson();

    public static <T> T jsonToModel(String json, Class<T> userType){
        return gson.fromJson(json, userType);
    }
    public static <T> String modelToJson(T userType){
        return gson.toJson(userType);
    }
}


