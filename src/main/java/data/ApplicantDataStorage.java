package data;

import java.util.ArrayList;
import java.util.List;

public class ApplicantDataStorage {
    List<Applicant> applicants;
    public ApplicantDataStorage() {
        applicants = new ArrayList<>();
    }
    public void addApplicant(Applicant applicant) {
        applicants.add(0, applicant);
    }
    public void removeApplicant(int index) {
        applicants.remove(index);
    }
    public void editApplicant(int index, Applicant applicant) {
        applicants.set(index, applicant);
    }
    public Applicant[] getApplicants() {
        return applicants.toArray(new Applicant[0]);
    }
}
