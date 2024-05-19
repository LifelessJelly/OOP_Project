package org.example;


import Subsystems.Models;

import java.util.ArrayList;

public class ApplicantShortlister {
    //Shortlists multiple applicants at once
    ArrayList<Models.ApplicantModel.Applicant> applicantShortlistToBeChanged;
    public ApplicantShortlister(int[] applicantIndices, Models.ApplicantModel applicants){
        applicantShortlistToBeChanged = applicants.getApplicantsByIndex(applicantIndices);
        for (var a : applicantShortlistToBeChanged) {
            a.toggleApplicantShortlist();
        }
    }
}
