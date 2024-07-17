package gui.infobase;

import data.Applicant;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.regex.Pattern;

public class ApplicantRowFilter extends RowFilter<TableModel, Integer> {
    public static final int STAGE_ANY = 0;
    public static final int  STAGE_AWAITING_SHORTLIST = 1;
    public static final int  STAGE_AWAITING_ACCEPTANCE = 2;
    public static final int STAGE_ACCEPTED = 3;
    private final Pattern namePattern;
    int[] filteredProcessStages;

    public ApplicantRowFilter(int[] filteredProcessStages, String regexNameMatch, String[] regexSkillMatch) {
        this.filteredProcessStages = filteredProcessStages;
        namePattern = Pattern.compile(".*(" + regexNameMatch + ").*");

    }

    @Override
    public boolean include(Entry entry) {
        Applicant applicant = ((Applicant)(entry.getValue(1)));


        if (!namePattern.matcher(applicant.getName()).find()){
            return false;
        }

        for (int filteredStage : filteredProcessStages) {
            switch (filteredStage) {
                case STAGE_ANY:
                    return true;
                case STAGE_AWAITING_SHORTLIST:
                    if (!applicant.isShortlisted()) {
                        return true;
                    }
                    break;
                case STAGE_AWAITING_ACCEPTANCE:
                    if (!applicant.isAccepted() && applicant.isShortlisted()) {
                        return true;
                    }
                    break;
                case STAGE_ACCEPTED:
                    if (applicant.isAccepted()) {
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
