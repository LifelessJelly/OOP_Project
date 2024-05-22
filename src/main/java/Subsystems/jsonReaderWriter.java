package Subsystems;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.InvocationTargetException;

public class jsonReaderWriter {
    private static final Gson gson = new Gson();

    public static <T> T jsonToModel(String json, Class<T> userType) {
        T t;
        t = gson.fromJson(json, userType);
        if (t == null){
            try {
                t = userType.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return t;
    }
    public static <T> String modelToJson(T userType){
        return gson.toJson(userType);
    }
}

class Reflector<T> {
    private final Class<T> reflection;
    public Reflector(Class<T> reflection) {
        this.reflection = reflection;
    }
}


