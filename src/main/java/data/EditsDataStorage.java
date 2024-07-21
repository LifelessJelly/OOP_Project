package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class EditsDataStorage {
    private final ArrayList<String> skills;
    private final ArrayList<ApplicantExperience> experience;

    public EditsDataStorage() {
        skills = new ArrayList<>();
        experience = new ArrayList<>();

    }

    //skill getter
    public String[] getSkills() {
        return skills.toArray(new String[0]);
    }

    //skill adder
    public void addSkill(String skill) {
        skills.add(skill);
    }

    //skill remove
    public void removeSkill(int index){
        skills.remove(index);
    }

    //get applicant experience array
    public ApplicantExperience[] getExperience() {
        return experience.toArray(new ApplicantExperience[0]);
    }

    public void importSkills(String[] skills) {
        if (skills != null) {
            this.skills.addAll(Arrays.asList(skills));
        }
    }

    public void importExperience(ApplicantExperience[] experience) {
        if (experience != null) {
            this.experience.addAll(Arrays.asList(experience));
        }
    }

    //add applicant experience array
    public void addExperience(String company, String position, int yearStart, int yearEnd) {
        experience.add(new ApplicantExperience(company, position, yearStart, yearEnd));

        experience.sort(new CompareJobDates());
    }

    //remove the experience, pass through index
    public void removeExperience(int index){
        experience.remove(index);
    }


    private static class CompareJobDates implements Comparator<ApplicantExperience> {
        @Override
        public int compare(ApplicantExperience o1, ApplicantExperience o2) {
            return o1.getYearBegin() - o2.getYearBegin();
            //compares the experience length of the applicants (is this to be implemented in the manager console josep?)
        }
    }
}
