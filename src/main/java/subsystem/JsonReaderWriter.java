package subsystem;

import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;

public class JsonReaderWriter {
    private static final Gson gson = new Gson();

    /**
     * Constructs an instance of a class model from the given input json string. If an invalid json string is given,
     * a default object of the passed in class will be created.
     * @param json The string in json format
     * @param classType the class that the json text will model against
     * @return the class generated from the json string
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
     * Generates a json string from an instance of a class model object
     * @param classModel the class object model
     * @return a generated json string based off the class model
     */
    public static <T> String modelToJson(T classModel){
        return gson.toJson(classModel);
    }
}



