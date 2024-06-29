package Data;

public class ApplicantExperience {

    private String previousCompanyName;
    private String previousJobTitle;
    private int yearBegin;
    private int yearEnd;

    public ApplicantExperience(String companyName, String jobTitle, int yearBegin, int yearEnd) {
        previousCompanyName = companyName;
        previousJobTitle = jobTitle;
        this.yearBegin = yearBegin;
        this.yearEnd = yearEnd;
    }

    public String getPreviousCompanyName() {
        return previousCompanyName;
    }

    public void setPreviousCompanyName(String previousCompanyName) {
        this.previousCompanyName = previousCompanyName;
    }

    public String getPreviousJobTitle() {
        return previousJobTitle;
    }

    public void setPreviousJobTitle(String previousJobTitle) {
        this.previousJobTitle = previousJobTitle;
    }

    public int getYearBegin() {
        return yearBegin;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd = yearEnd;
    }

    public void setYearBegin(int yearBegin) {
        this.yearBegin = yearBegin;
    }
}
