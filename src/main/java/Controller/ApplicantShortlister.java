package Controller;


import Data.ApplicantModel;

import java.util.List;

public class ApplicantShortlister {
    //Shortlists multiple applicants at once
    List<ApplicantModel.Applicant> applicantShortlistToBeChanged;
    public ApplicantShortlister(int[] applicantIndices, ApplicantModel applicants){
        applicantShortlistToBeChanged = applicants.getApplicantsByIndex(applicantIndices);
        for (var a : applicantShortlistToBeChanged) {
            a.toggleApplicantShortlist();
        }
    }
}
