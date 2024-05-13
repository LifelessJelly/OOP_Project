package org.example;

import java.util.ArrayList;
import java.util.List;

public class ApplicantLocalStore {
    List<ApplicantDetails> ApplicantDetailsList;
    public ApplicantLocalStore() {
        ApplicantDetailsList = new ArrayList<>();

    }
    boolean insertApplicantIsSuccessful(ApplicantDetails applicantDetails) {
        if (applicantDetails == null || applicantDetails.getPtrToSummary() == null) {
            return false;
        }
        ApplicantDetailsList.add(applicantDetails);
        return true;
    }
    ApplicantDetails searchApplicantDetails(int applicantId) {
        for (ApplicantDetails applicantDetails : ApplicantDetailsList) {
            if (applicantDetails.getApplicantID() == applicantId) {
                return applicantDetails;
            }
        }
        return null;
    }

}
