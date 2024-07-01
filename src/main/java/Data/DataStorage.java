package Data;

import java.util.ArrayList;
import java.util.Comparator;

public class DataStorage {
    private final ArrayList<String> skills;
    private final ArrayList<ApplicantExperience> experience;
    public DataStorage() {
        skills = new ArrayList<>();
        experience = new ArrayList<>();
    }

    public String[] getSkills() {
        return skills.toArray(new String[0]);
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }

    public void removeSkill(int index){
        skills.remove(index);
    }

    public ApplicantExperience[] getExperience() {
        return experience.toArray(new ApplicantExperience[0]);
    }
    public void addExperience(String company, String position, int yearStart, int yearEnd) {
        experience.add(new ApplicantExperience(company, position, yearStart, yearEnd));

        experience.sort(new CompareJobDates());
    }
    public void removeExperience(int index){
        experience.remove(index);
    }

    private static class CompareJobDates implements Comparator<ApplicantExperience> {

        @Override
        public int compare(ApplicantExperience o1, ApplicantExperience o2) {
            return o1.getYearBegin() - o2.getYearBegin();
        }
    }
}
