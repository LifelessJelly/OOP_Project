package controller;

import data.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class InfobaseController {
    private final Staff loggedInStaffInstance;
    private EditsDataStorage editsDataStorageInstance;
    private ApplicantDataStorage applicantDataStorage;
    private int referenceIndex;

    public InfobaseController(Staff loggedInStaffInstance) {
        this.loggedInStaffInstance = loggedInStaffInstance;
        refreshIndices();
    }

    /**
     * Refreshes the applicant indices by loading applicant data from JSON files.
     * <p>
     * This method reads all JSON files from the "applicants" directory located in the
     * current working directory. It initializes a new ApplicantDataStorage instance,
     * sorts the files by their last modified date, and adds each valid applicant
     * (with a non-empty name) to the applicant data storage.
     * <p>
     * Assumes that the directory exists and contains JSON files representing applicants.
     */
    public void refreshIndices(){
        String path = System.getProperty("user.dir") + "\\" + "applicants";
        this.applicantDataStorage = new ApplicantDataStorage();
        File dir = new File(path);
        File[] files = dir.listFiles();
        assert files != null;
        Arrays.sort(files, new FileSortByDate());
        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                String json = DataIO.readFile(file.getAbsolutePath());
                Applicant applicant = JsonReaderWriter.jsonToModel(json, Applicant.class);
                if (!applicant.getName().isEmpty()){
                    applicantDataStorage.addApplicant(applicant);
                }
            }
        }
    }

    /**
     * Adds a new applicant to the system with the specified details.
     * <p>
     * This method constructs an applicant's birthdate from the provided day, month,
     * and year, creates an Applicant object, and stores it in the applicant data storage.
     * It also writes the applicant's information to a JSON file in the applicants' directory.
     *
     * @param name      the name of the applicant
     * @param day       the day of birth of the applicant
     * @param month     the month of birth of the applicant
     * @param year      the year of birth of the applicant
     * @param email     the email address of the applicant
     * @param nric      the NRIC number of the applicant
     * @param gender    the gender of the applicant
     * @param base64Img the base64 encoded image of the applicant
     * @param skills    an array of skills associated with the applicant
     * @param pdfFile   the path to the PDF file related to the applicant
     */
    public void addApplicant(String name, int day, String month, int year,String email, String nric, String gender, String base64Img, String[] skills, String pdfFile){
        String birthString = String.valueOf(day) + ' ' + month + ' ' + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate birthDate = LocalDate.parse(birthString, formatter);
        Applicant applicant=new Applicant(name, birthDate.toEpochDay(), LocalDate.now().getYear()-birthDate.getYear(), email, nric, gender, base64Img, skills, pdfFile);
        applicantDataStorage.addApplicant(applicant);
        System.out.println("applicant added");
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        DataIO.writeFile(applicantDirectory + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(System.nanoTime()))) + "_Applicant.json", JsonReaderWriter.modelToJson(applicant));
    }

    /**
     * Adds an applicant model to the data storage and saves it as a JSON file.
     * <p>
     * This method creates a directory for storing applicant files if it does not exist,
     * adds the provided applicant to the applicant data storage, generates a unique
     * filename based on the current time, converts the applicant model to JSON format,
     * and writes the JSON data to a file in the applicants' directory.
     *
     * @param applicant the Applicant object to be added to the storage
     */
    public void addApplicantModel(Applicant applicant){
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        applicantDataStorage.addApplicant(applicant);
        DataIO.writeFile(applicantDirectory + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(System.nanoTime()))) + "_Applicant.json", JsonReaderWriter.modelToJson(applicant));
        System.out.println("applicant added via object");
    }

    /**
     * Removes an applicant from the data storage and the file system based on the specified index.
     * <p>
     * This method first removes the applicant from the applicant data storage.
     * It then retrieves the list of applicant files from the "applicants" directory,
     * sorts them by date in descending order, and deletes the file corresponding
     * to the specified index. A confirmation message is printed if the deletion is successful.
     *
     * @param index the index of the applicant to be removed from both storage and file system
     */
    public void removeApplicant(int index){
        applicantDataStorage.removeApplicant(index);
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        File[] contents = applicantDirectory.listFiles();
        assert contents != null;
        Arrays.sort(contents, new FileSortByDate());
        Collections.reverse(Arrays.asList(contents));
        File targetApplicant = contents[index];
        if (targetApplicant.delete()){
            System.out.println("applicant removed");
        }
    }

    // CALL THIS METHOD BEFORE INTERFACING WITH DATA METHODS
    public void setApplicantInstance(int referenceIndex){
        this.referenceIndex = referenceIndex;
        editsDataStorageInstance = new EditsDataStorage(getApplicantInstance());
    }

    public Applicant getApplicantInstance(){
        return applicantDataStorage.getApplicantAt(referenceIndex);
    }

    /**
     * Applies edits to an applicant's information.
     * <p>
     * This method takes the applicant's details, constructs a LocalDate from the
     * provided day, month, and year, and creates a new Applicant object with the
     * updated information. It then updates the applicant's data in the storage
     * and overwrites the existing index with the edited applicant.
     *
     * @param name     the name of the applicant
     * @param day      the day of birth of the applicant
     * @param month    the month of birth of the applicant
     * @param year     the year of birth of the applicant
     * @param nricFin  the NRIC or FIN of the applicant
     * @param email    the email address of the applicant
     * @param gender   the gender of the applicant
     * @param image    the image associated with the applicant
     */
    public void applyApplicantEdits(String name, int day, String month, int year, String nricFin, String email, String gender, String image){
        String dateString = String.valueOf(day) + ' ' + month + ' ' + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        Applicant edittedApplicant = new Applicant(name, localDate.toEpochDay(), LocalDate.now().getYear()-localDate.getYear(), email, nricFin, gender, image, editsDataStorageInstance.getSkills(), getApplicantInstance());
        applicantDataStorage.editApplicant(referenceIndex, edittedApplicant);
        overwriteIndex(referenceIndex, edittedApplicant);
    }

    /**
     * Overwrites the applicant file at the specified index with new applicant data.
     * <p>
     * This method retrieves the list of applicant files from the "applicants" directory,
     * sorts them by date in descending order, and deletes the file at the given index.
     * It then writes the new applicant data in JSON format to the deleted file's location.
     *
     * @param index the index of the applicant file to be overwritten
     * @param newApplicant the new applicant data to write to the file
     */
    private void overwriteIndex(int index, Applicant newApplicant){
        File applicantDirectory = new File(System.getProperty("user.dir") + "\\" + "applicants");
        File[] contents = applicantDirectory.listFiles();
        if (contents != null) {
            Arrays.sort(contents, new FileSortByDate());
            Collections.reverse(Arrays.asList(contents));
            File targetApplicantFile = contents[index];
            if (targetApplicantFile.delete()) {
                DataIO.writeFile(targetApplicantFile.getAbsolutePath(), JsonReaderWriter.modelToJson(newApplicant));
            }
            else {
                System.out.println("Can't delete file");
            }
        }
    }

    public BufferedImage getImage() {
        return getApplicantInstance().getImage();
    }

    public String getName() {
        return getApplicantInstance().getName();
    }

    public String getNRIC() {
        return getApplicantInstance().getNRIC();
    }

    public String getEmail() {
        return getApplicantInstance().getEmail();
    }

    public String[] getSkills() {
        return editsDataStorageInstance.getSkills();
    }

    public void addSkill(String editSkillTextField) {
        editsDataStorageInstance.addSkill(editSkillTextField);
    }

    public void removeSkill(int selectedIndex) {
        editsDataStorageInstance.removeSkill(selectedIndex);
    }

    public void editSkill(int index, String editedSkill) {
        editsDataStorageInstance.editSkill(index, editedSkill);
    }

    public Applicant[] getApplicants() {
        return applicantDataStorage.getApplicants();
    }

    public Staff getUser() {
        return loggedInStaffInstance;
    }

    /**
     * Shortlists an applicant for an interview based on the selected row index.
     * <p>
     * This method retrieves the applicant corresponding to the provided row index,
     * updates their status to {@code SHORTLISTED_PENDING_DATE}, and saves the changes
     * back to the reference index.
     *
     * @param selectedRow the index of the applicant to be shortlisted
     */
    public void shortlistApplicantForInterview(int selectedRow) {
        setApplicantInstance(selectedRow);
        Applicant applicantsToShortlist = getApplicantInstance();
        applicantsToShortlist.setStatus(Applicant.SHORTLISTED_PENDING_DATE);
        overwriteIndex(referenceIndex, applicantsToShortlist);
    }

    /**
     * Accepts an applicant based on the specified row index.
     * <p>
     * This method retrieves the applicant instance corresponding to the
     * provided row index, updates the applicant's status to
     * {@code ACCEPTED_WAITING_JOB}, and overwrites the
     * existing applicant data in the reference index.
     *
     * @param selectedRow the index of the applicant to be accepted
     * @throws IndexOutOfBoundsException if the selectedRow is invalid
     */
    public void acceptApplicant(int selectedRow) {
        setApplicantInstance(selectedRow);
        Applicant applicantToAccept = getApplicantInstance();
        applicantToAccept.setStatus(Applicant.ACCEPTED_WAITING_JOB);
        overwriteIndex(referenceIndex, applicantToAccept);
    }

    public String getBirthdate() {
        return getApplicantInstance().getBirthdate();
    }

    public String getGender() {
        return getApplicantInstance().getGender();
    }

	/**
	 * Sets the interview time for the applicant and sends a notification email.
     * <p> 
	 * This method retrieves the current applicant instance, sets the specified
	 * interview time, and sends an email to the applicant notifying them of their
	 * interview details. The applicant's status is updated to
	 * {@code SHORTLISTED_TO_INTERVIEW}, and the applicant's information is overwritten in
	 * the reference index.
	 *
	 * @param time the time of the interview in milliseconds since epoch
	 */
	public void setInterviewTime(long time) {
		Applicant applicantToInterview = getApplicantInstance();
		applicantToInterview.setInterviewTime(time);
		new Thread(() -> new EmailSender("joseph_chiu@outlook.com", "Yay you made it", "Esteemed Sir/Madam,\n"
				+ "\n"
				+ "It is with the utmost pleasure and profound elation that we convey to you the magnificent news of your acceptance into the illustrious ranks of Operate On Peasants LLC. Your presence is hereby requested for a grand interview at the appointed hour of "
				+ applicantToInterview.getInterviewTime()
				+ ". We implore you to assemble and present the requisite documents, including but not limited to, a meticulously crafted copy of your resume, your esteemed identification papers, and any other pertinent documentation.\n" +
            "\n" +
            "We eagerly anticipate the honor of your attendance.\n" +
            "\n" +
            "With the highest regard,\n" +
            "Operate On Peasants LLC")).start();
    applicantToInterview.setStatus(Applicant.SHORTLISTED_TO_INTERVIEW);
    overwriteIndex(referenceIndex, applicantToInterview);
}

    
    /**
     * Retrieves the applicant at the specified index from the data storage.
     * <p>
     * This method accesses the applicant data storage and returns the applicant
     * object located at the given index. It is important to ensure that the index
     * is within the valid range to avoid exceptions.
     *
     * @param index the index of the applicant to retrieve
     * @return the Applicant object at the specified index
     */
    public Applicant getApplicantAt(int index) {
        return applicantDataStorage.getApplicantAt(index);
    }


	/**
	 * Assigns a job role to the applicant and sets their status to accepted.
	 *
	 * @param applicantAssignedField the job role to be assigned to the applicant
	 * @throws NullPointerException if the applicant instance is null
	 */
    public void setJobRole(String applicantAssignedField) {
        Applicant applicantToAssign = getApplicantInstance();
        applicantToAssign.setStatus(Applicant.ACCEPTED);
        applicantToAssign.setJobRole(applicantAssignedField);
    }

    /**
     * Opens a PDF file for the current applicant.
     * <p>
     * This method retrieves the applicant instance using {@link #getApplicantInstance()},
     * converts the applicant's PDF from Base64 format to a PDF file, and opens it.
     * The PDF file is saved temporarily in the directory specified by
     * {@code System.getProperty("user.dir") + "\temp\tempPDF.pdf"}.
     *
     * @throws IllegalArgumentException if the applicant's PDF Base64 string is invalid or null.
     */
    public void openPDF() {
        Applicant applicantToOpen = getApplicantInstance();
        PDFBase64.base64ToPdfAndOpen(applicantToOpen.getPdfBase64(), System.getProperty("user.dir") + "\\temp\\tempPDF.pdf");
    }

    /**
     * Removes all temporary PDF files from the temp directory.
     * <p>
     * This method locates the temporary directory specified by
     * {@code System.getProperty("user.dir") + "\temp"} and attempts to delete
     * each file within that directory. A message is printed to the console for
     * each successfully deleted file.
     * <p>
     * If the temp directory does not exist or contains no files, no action is taken.
     *
     * @throws SecurityException if a security manager exists and its
     *         {@code checkDelete} method denies deletion of a file.
     */
    public void removeTemp() {
        File pdfFile = new File(System.getProperty("user.dir") + "\\temp");
        File[] files = pdfFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.delete()){
                    System.out.println("file in pdf temp deleted");
                }
            }
        }
    }

    public String getLocale(String language, String key){
        TranslationKey translationKey = new TranslationKey(language, key);
        return TranslationTable.getInstance().getTranslation(translationKey);
    }

    public Image getImage(int currentTheme, String key){
        ThemeKey themeKey = new ThemeKey(currentTheme, key);
        return ThemeTable.getInstance().getImage(themeKey);
    }
}

/**
 * A comparator that compares two {@link File} objects based on their last modified date.
 * <p>
 * This class implements the {@link Comparator} interface and provides a method to
 * compare two files by their last modified timestamps. The comparison is done in
 * ascending order, meaning that a file with an earlier last modified date will be
 * considered "less than" a file with a later last modified date.
 */
class FileSortByDate implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        return (int) (o1.lastModified() - o2.lastModified());
    }

}
