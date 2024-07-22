package gui.infobase;

import data.Applicant;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ApplicantRowFilter extends RowFilter<TableModel, Integer> {
    public static final int STAGE_ANY = 0;
    public static final int  STAGE_AWAITING_SHORTLIST = 1;
    public static final int  STAGE_AWAITING_ACCEPTANCE = 2;
    public static final int STAGE_ACCEPTED = 3;
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
                case STAGE_ANY:
                    return true;
                case STAGE_AWAITING_SHORTLIST:
                    if (applicant.getStatus() == Applicant.WAITING_SHORTLIST) {
                        return true;
                    }
                    break;
                case STAGE_AWAITING_ACCEPTANCE:
                    if (applicant.getStatus() == Applicant.SHORTLISTED) {
                        return true;
                    }
                    break;
                case STAGE_ACCEPTED:
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