package Subsystems;

import com.google.gson.Gson;

public class ApplicantIO {
    static Gson gson = new Gson();
    static ApplicantModel jsonToModel(String json){
        return gson.fromJson(json, ApplicantModel.class);
    }
    static String modelToJson(ApplicantModel applicants){
        return gson.toJson(applicants);
    }
}


