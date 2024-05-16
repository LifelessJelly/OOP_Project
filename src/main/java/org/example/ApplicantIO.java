package org.example;

import com.google.gson.Gson;

public class ApplicantIO {
    static ApplicantModel.ApplicantList readApplicants(String json){
        Gson gson = new Gson();
        ApplicantModel applicantModel = gson.fromJson(json, ApplicantModel.class);

    }
}


