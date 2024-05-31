package Controller;


import Data.ApplicantModel;
import Subsystems.Pointer;

import java.util.List;



public class ApplicantShortlister {
    Pointer<ApplicantModel> ptrToApplicantModel;
    public ApplicantShortlister(int[] applicantIndices, Pointer<ApplicantModel> applicants){
        ptrToApplicantModel = applicants;
        for (var a : ptrToApplicantModel.get().getApplicantsByIndex(applicantIndices)) {
            a.toggleApplicantShortlist();
        }
    }
}
