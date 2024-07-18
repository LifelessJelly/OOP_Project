package generator;

import controller.DataIO;
import controller.ApplicantRegistrator;
import data.Applicant;
import subsystems.JsonReaderWriter;
import subsystems.SHA256;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicantSpammer {
    public static void generateApplicants(int number){
        for (int i = 0; i < number; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    ApplicantRegistrator registrator = new ApplicantRegistrator();
                    List<String> names = new ArrayList<>();
                    String allnames = DataIO.readFile(System.getProperty("user.dir") + "\\first-names.txt");
                    StringTokenizer st = new StringTokenizer(allnames, "\r\n");
                    while (st.hasMoreTokens()) {
                        names.add(st.nextToken());
                    }
                    String name = names.get(new Random().nextInt(names.size()));


                    //ignore case of Singaporeans born before 1965
                    int year = new Random().nextInt(2024 - 1965 + 1) + 1965;
                    final String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                    String month = months[new Random().nextInt(months.length)];
                    int day = 0;
                    if (year % 4 == 0 && year % 100 != 0 && month.equals("February")) {
                        day = new Random().nextInt(29 - 1) + 1;
                    } else if (month.equals("February")) {
                        day = new Random().nextInt(28 - 1) + 1;
                    }
                    //months that have 30 days
                    else if (month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")) {
                        day = new Random().nextInt(30 - 1) + 1;
                    } else {
                        day = new Random().nextInt(31 - 1) + 1;
                    }


                    char[] NRIC20thCentury = new char[]{'S', 'F'};
                    char[] NRIC21stCentury = new char[]{'T', 'G'};

                    StringBuilder NRIC = new StringBuilder();
                    if (year < 2000) {
                        NRIC.append(NRIC20thCentury[new Random().nextInt(NRIC20thCentury.length)]);
                    } else {
                        NRIC.append(NRIC21stCentury[new Random().nextInt(NRIC21stCentury.length)]);
                    }
                    NRIC.append(String.valueOf(year).charAt(2));
                    NRIC.append(String.valueOf(year).charAt(3));
                    for (int j = 0; j < 5; ++j) {
                        NRIC.append((char) (new Random().nextInt('9' - '0' + 1) + '0'));
                    }
                    int checksum = (Integer.parseInt(String.valueOf(NRIC.charAt(1))) * 2 +
                            Integer.parseInt(String.valueOf(NRIC.charAt(2) * 7)) +
                            Integer.parseInt(String.valueOf(NRIC.charAt(3) * 6)) +
                            Integer.parseInt(String.valueOf(NRIC.charAt(4) * 5)) +
                            Integer.parseInt(String.valueOf(NRIC.charAt(5) * 4)) +
                            Integer.parseInt(String.valueOf(NRIC.charAt(6) * 3)) +
                            Integer.parseInt(String.valueOf(NRIC.charAt(7) * 2))) % 11;

                    switch (NRIC.charAt(0)) {
                        case 'S':
                            NRIC.append((new char[]{'J', 'Z', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'})[checksum]);
                            break;
                        case 'T':
                            NRIC.append((new char[]{'G', 'F', 'E', 'D', 'C', 'B', 'A', 'J', 'Z', 'I', 'H'})[checksum]);
                            break;
                        case 'G':
                            NRIC.append((new char[]{'R', 'Q', 'P', 'N', 'M', 'L', 'K', 'X', 'W', 'U', 'T'})[checksum]);
                            break;
                        case 'F':
                            NRIC.append((new char[]{'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K'})[checksum]);
                            break;
                    }
                    //String of email domains in this folded block
                    String[] emailAddresses = new String[]
                            {"aol.com",
                                    "att.net",
                                    "comcast.net",
                                    "facebook.com",
                                    "gmail.com",
                                    "gmx.com",
                                    "googlemail.com",
                                    "google.com",
                                    "hotmail.com",
                                    "hotmail.co.uk",
                                    "mac.com",
                                    "me.com",
                                    "mail.com",
                                    "msn.com",
                                    "live.com",
                                    "sbcglobal.net",
                                    "verizon.net",
                                    "yahoo.com",
                                    "yahoo.co.uk",
                                    "email.com",
                                    "fastmail.fm",
                                    "games.com",
                                    "gmx.net",
                                    "hush.com",
                                    "hushmail.com",
                                    "icloud.com",
                                    "iname.com",
                                    "inbox.com",
                                    "lavabit.com",
                                    "love.com",
                                    "outlook.com",
                                    "pobox.com",
                                    "protonmail.ch",
                                    "protonmail.com",
                                    "tutanota.de",
                                    "tutanota.com",
                                    "tutamail.com",
                                    "tuta.io",
                                    "keemail.me",
                                    "rocketmail.com",
                                    "safe-mail.net",
                                    "wow.com",
                                    "ygm.com",
                                    "ymail.com",
                                    "zoho.com",
                                    "yandex.com",
                                    "bellsouth.net",
                                    "charter.net",
                                    "cox.net",
                                    "earthlink.net",
                                    "juno.com",
                                    "btinternet.com",
                                    "virginmedia.com",
                                    "blueyonder.co.uk",
                                    "freeserve.co.uk",
                                    "live.co.uk",
                                    "ntlworld.com",
                                    "o2.co.uk",
                                    "orange.net",
                                    "sky.com",
                                    "talktalk.co.uk",
                                    "tiscali.co.uk",
                                    "virgin.net",
                                    "wanadoo.co.uk",
                                    "bt.com",
                                    "sina.com",
                                    "sina.cn",
                                    "qq.com",
                                    "naver.com",
                                    "hanmail.net",
                                    "daum.net",
                                    "nate.com",
                                    "yahoo.co.jp",
                                    "yahoo.co.kr",
                                    "yahoo.co.id",
                                    "yahoo.co.in",
                                    "yahoo.com.sg",
                                    "yahoo.com.ph",
                                    "163.com",
                                    "yeah.net",
                                    "126.com",
                                    "21cn.com",
                                    "aliyun.com",
                                    "foxmail.com",
                                    "hotmail.fr",
                                    "live.fr",
                                    "laposte.net",
                                    "yahoo.fr",
                                    "wanadoo.fr",
                                    "orange.fr",
                                    "gmx.fr",
                                    "sfr.fr",
                                    "neuf.fr",
                                    "free.fr",
                                    "gmx.de",
                                    "hotmail.de",
                                    "live.de",
                                    "online.de",
                                    "t-online.de",
                                    "web.de",
                                    "yahoo.de",
                                    "libero.it",
                                    "virgilio.it",
                                    "hotmail.it",
                                    "aol.it",
                                    "tiscali.it",
                                    "alice.it",
                                    "live.it",
                                    "yahoo.it",
                                    "email.it",
                                    "tin.it",
                                    "poste.it",
                                    "teletu.it",
                                    "mail.ru",
                                    "rambler.ru",
                                    "yandex.ru",
                                    "ya.ru",
                                    "list.ru",
                                    "hotmail.be",
                                    "live.be",
                                    "skynet.be",
                                    "voo.be",
                                    "tvcablenet.be",
                                    "telenet.be",
                                    "hotmail.com.ar",
                                    "live.com.ar",
                                    "yahoo.com.ar",
                                    "fibertel.com.ar",
                                    "speedy.com.ar",
                                    "arnet.com.ar",
                                    "yahoo.com.mx",
                                    "live.com.mx",
                                    "hotmail.es",
                                    "hotmail.com.mx",
                                    "prodigy.net.mx",
                                    "yahoo.ca",
                                    "hotmail.ca",
                                    "bell.net",
                                    "shaw.ca",
                                    "sympatico.ca",
                                    "rogers.com",
                                    "yahoo.com.br",
                                    "hotmail.com.br",
                                    "outlook.com.br",
                                    "uol.com.br",
                                    "bol.com.br",
                                    "terra.com.br",
                                    "ig.com.br",
                                    "itelefonica.com.br",
                                    "r7.com",
                                    "zipmail.com.br",
                                    "globo.com",
                                    "globomail.com",
                                    "oi.com.br"};
                    String email = name + '@' + emailAddresses[new Random().nextInt(emailAddresses.length)];

                    String gender = (new String[]{
                            "Male",
                            "Female",
                            "Transgender Male",
                            "Transgender Female",
                            "Non-binary",
                            "Non-binary Transgender",
                            "Queer",
                            "Binary",
                            "Bigender/Genderfluid",
                            "Intergender",
                            "Xenogender",
                            "Third gender",
                            "Others"})[new Random().nextInt(13)];
                    registrator.registerBasicInfo(name, day, month, year, NRIC.toString(), email, gender);
                    List<String> skills = new ArrayList<>(Arrays.asList("Programming", "Industrial", "Artistic", "Communication"));
                    Collections.shuffle(skills);
                    List<String> applicantSkills = new ArrayList<>();
                    int bound = new Random().nextInt(4);

                    for (int j = 0; j < bound; j++) {
                        applicantSkills.add(skills.get(skills.size() - 1));
                        skills.remove(skills.size() - 1);

                    }

                    registrator.registerAllSkills(applicantSkills.toArray(new String[0]), new String[0]);
                    String directory = System.getProperty("user.dir") + '\\' + "Pic" + finalI + ".jpeg";
                    final DDOSNvidiaServers imageProcess = new DDOSNvidiaServers(directory);

                    //Image
                    Image profileImage;

                    synchronized (DDOSNvidiaServers.class){
                        imageProcess.getImage();
                    }
                    File imageFile = new File(directory);
                    profileImage = readImageFile(imageFile);


                    if (imageFile.delete()) {
                        System.out.println("Applicant number " + finalI + " has finished generating");
                    }
                    registrator.registerImageFromBase64(profileImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH));


                    Applicant newApplicant = registrator.createApplicant();
                    try {
                        try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\" + "applicants" + "\\" + new String(SHA256.getHasherHex().hashString(String.valueOf(System.nanoTime()))) + "_Applicant.json")) {
                            fileWriter.write(JsonReaderWriter.modelToJson(newApplicant));
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                catch (Exception e) {
                    System.out.println("Applicant number " + finalI + " failed to generate");
                    System.out.println(e);
                }
            }).start();
        }
    }
    private static Image readImageFile(File imageFile){
        try {

            return ImageIO.read(imageFile);
        } catch (IOException e) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("can't read file, trying again");
            return readImageFile(imageFile);
        }
    }
}
class DDOSNvidiaServers {
    private final String directory;
    public DDOSNvidiaServers(String directory) {
        this.directory = directory;
    }
    public synchronized void getImage(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Process powershellGetRandImage;
        try {
            powershellGetRandImage = Runtime.getRuntime().exec("powershell.exe Invoke-WebRequest https://thispersondoesnotexist.com/ -OutFile '" + directory + "'");
            powershellGetRandImage.getOutputStream().close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

