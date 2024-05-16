package org.example;

import java.util.ArrayList;
import java.util.Date;
import com.google.gson.Gson;

public class ApplicantModel {

    class ApplicantList {
        private ArrayList<Applicant> applicants;
    }

    class Applicant{
        ApplicantSummary applicantSummary;
        ApplicantDetails applicantDetails;
        ApplicantPreviousJobExperience applicantPreviousJobExperience;
    }
    class ApplicantDetails {
        private String applicantName;
        private long birthdate;
        private int age;
        private String nationality;
        private String gender;
        private String nric_Fin_Passport;
    }
    class ApplicantSummary {
        private ArrayList<String> resume;
        private ArrayList<String> skills;
    }
    class ApplicantPreviousJobExperience {
        private String previousCompanyName;
        private String previousJobTitle;
        private int yearBegin;
        int yearEnd;
    }
}
