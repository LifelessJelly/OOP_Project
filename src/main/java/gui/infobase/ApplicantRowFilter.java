package gui.infobase;

import data.Applicant;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * A custom {@link RowFilter} for filtering {@link Applicant} entries in a {@link TableModel}.
 *
 * <p>This filter allows for filtering applicants based on their name, skills, and
 * specified process stages. It uses a regular expression to match applicant names
 * and checks if the applicant's skills are contained within a specified set of skills.</p>
 *
 * <p>The filter will include an applicant if:</p>
 * <ul>
 *     <li>The applicant's name matches the provided regex pattern.</li>
 *     <li>The applicant's skills match the specified skills (if any).</li>
 *     <li>The applicant's status is one of the filtered process stages.</li>
 * </ul>
 *
 */
public class ApplicantRowFilter extends RowFilter<TableModel, Integer> {
    private final Pattern namePattern;
    final int[] filteredProcessStages;
    final String[] skills;

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