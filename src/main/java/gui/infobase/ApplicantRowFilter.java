package gui.infobase;

import data.Applicant;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ApplicantRowFilter extends RowFilter<TableModel, Integer> {
    private final Pattern namePattern;
    int[] filteredProcessStages;
    String[] skills;

    public ApplicantRowFilter(int[] filteredProcessStages, String regexNameMatch, String[] skills) {
        this.filteredProcessStages = filteredProcessStages;
        namePattern = Pattern.compile(".*(" + regexNameMatch + ").*");
        this.skills = skills;
    }

    @Override
    public boolean include(Entry entry) {
        Applicant applicant = ((Applicant)(entry.getValue(1)));

        if (!namePattern.matcher(applicant.getName()).find()){
            return false;
        }
        //TODO uncomment this snippet once there is an all skills option
//        if (skills != null) {
//        boolean notFoundSkill = true;
//
//            for (String skill : skills) {
//                String[] applicantSkills = applicant.getSkills();
//                if (Arrays.asList(applicantSkills).contains(skill)) {
//                    notFoundSkill = false;
//                    break;
//                }
//            }
//            if (notFoundSkill) {
//                return false;
//            }
//        }

        if (applicant.getSkills().length != 0 && skills != null) {
            boolean allSkillsMatch = true;
            for (String applicantsSkills : applicant.getSkills()) {
                if (!Arrays.asList(skills).contains(applicantsSkills)) {
                    allSkillsMatch = false;
                    break;
                }
            }
            if (allSkillsMatch) {
                return false;
            }
        }

        for (int filteredStage : filteredProcessStages) {
            switch (filteredStage) {
                case Applicant.WAITING_SHORTLIST:
                    if (applicant.getStatus() == Applicant.WAITING_SHORTLIST) {
                        return true;
                    }
                    break;
                case Applicant.SHORTLISTED_PENDING_DATE:
                    if (applicant.getStatus() == Applicant.SHORTLISTED_PENDING_DATE) {
                        return true;
                    }
                    break;
                case Applicant.SHORTLISTED_TO_INTERVIEW:
                    if (applicant.getStatus() == Applicant.SHORTLISTED_TO_INTERVIEW) {
                        return true;
                    }
                    break;
                case Applicant.ACCEPTED_WAITING_JOB:
                    if (applicant.getStatus() == Applicant.ACCEPTED_WAITING_JOB) {
                        return true;
                    }
                    break;
                case Applicant.ACCEPTED:
                    if (applicant.getStatus() == Applicant.ACCEPTED) {
                        return true;
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return false;
    }
}