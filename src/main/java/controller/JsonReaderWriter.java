package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.InvocationTargetException;

public class JsonReaderWriter {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Constructs an instance of a class model from the given input JSON string. If an invalid JSON string is given, a base class with null objects is created
     * @param json The string in JSON format
     * @param classType the class type of the Java object to be created
     * @return an instance of the specified class type populated with data from the JSON string
     * @throws RuntimeException if there are any issues during the conversion process
     */
    public static <T> T jsonToModel(String json, Class<T> classType) {
        T t;
        t = gson.fromJson(json, classType);
        if (t == null){
            System.out.println("Bad JSON Text");
            try {
                t = classType.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }

    /**
     * Generates a JSON string from an instance of a class model object
     * @param classModel the class object model
     * @return a generated json string based off the class model
     */
    public static <T> String modelToJson(T classModel){
        System.out.println("json created");
        return gson.toJson(classModel);
    }
}



