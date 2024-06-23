package GUI.Registration;

import Controller.EmailSender;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class EmailVerficationCode extends SlidingPanel{
    Mainframe mainframe;
    JLabel enterOTPLabel;
    JLabel OTPSubLabel;
    JPanel OTPPanel;
    private JPanel confirmInfoStuffPanel;
    private JLabel errorMessageLabel;
    private int triesLeft = 4;
    private int[] OTP;
    private Insets movingInsets;
    private int loopCycles = 0;
    private GridBagLayout layout;
    private GridBagConstraints enterOTPConstraints;
    private GridBagConstraints otpSubConstraints;
    private GridBagConstraints otpPanelConstraints;
    private GridBagConstraints confirmInfoStuffConstraints;
    private JTextField otpDigit1;
    private JTextField otpDigit2;
    private JTextField otpDigit3;
    private JTextField otpDigit4;
    private JTextField otpDigit5;
    private JTextField otpDigit6;
    private JButton nextButton;
    private JButton backButton;


    public EmailVerficationCode(Mainframe mainframe) {
        this.mainframe = mainframe;
        initComponents();
    }

    private void initComponents() {
        movingInsets = new Insets(0, 0, 5, 0);
        layout = new GridBagLayout();
        this.setLayout(layout);

        enterOTPConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        otpSubConstraints = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        otpPanelConstraints = new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);
        confirmInfoStuffConstraints = new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                movingInsets, 0, 0);

        enterOTPLabel = new JLabel("Enter the OTP sent to your email");
        enterOTPLabel.setHorizontalAlignment(SwingConstants.LEFT);
        enterOTPLabel.setFont(enterOTPLabel.getFont().deriveFont(22f));
        this.add(enterOTPLabel, enterOTPConstraints);


        OTPSubLabel = new JLabel("To verify this email is yours");
        OTPSubLabel.setHorizontalAlignment(SwingConstants.LEFT);
        OTPSubLabel.setFont(OTPSubLabel.getFont().deriveFont(16f));
        this.add(OTPSubLabel, otpSubConstraints);

        OTPPanel = new JPanel();
        OTPPanel.setLayout(new GridBagLayout());

        GridBagConstraints OTPDigit1Constraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints OTPDigit2Constraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints OTPDigit3Constraints = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints OTPDigit4Constraints = new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints OTPDigit5Constraints = new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints OTPDigit6Constraints = new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 10), 0, 0);


        otpDigit1 = new JTextField();
        otpDigit2 = new JTextField();
        otpDigit3 = new JTextField();
        otpDigit4 = new JTextField();
        otpDigit5 = new JTextField();
        otpDigit6 = new JTextField();
        addOTPDigit(otpDigit1, OTPDigit1Constraints);
        addOTPDigit(otpDigit2, OTPDigit2Constraints);
        addOTPDigit(otpDigit3, OTPDigit3Constraints);
        addOTPDigit(otpDigit4, OTPDigit4Constraints);
        addOTPDigit(otpDigit5, OTPDigit5Constraints);
        addOTPDigit(otpDigit6, OTPDigit6Constraints);
        initOTPBoxSettings(otpDigit1);
        initOTPBoxSettings(otpDigit2);
        initOTPBoxSettings(otpDigit3);
        initOTPBoxSettings(otpDigit4);
        initOTPBoxSettings(otpDigit5);
        initOTPBoxSettings(otpDigit6);
        this.add(OTPPanel, otpPanelConstraints);

        confirmInfoStuffPanel = new JPanel();

        confirmInfoStuffPanel.setLayout(new GridBagLayout());
        GridBagConstraints errorMessageConstraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0,0);
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0);
        GridBagConstraints backButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);


        errorMessageLabel = new JLabel("");
        errorMessageLabel.setPreferredSize(new Dimension(400, 20));
        errorMessageLabel.setMinimumSize(new Dimension(400, 20));
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessageLabel.setFont(errorMessageLabel.getFont().deriveFont(18f));
        confirmInfoStuffPanel.add(errorMessageLabel, errorMessageConstraints);

        nextButton = new JButton("Next");
        nextButton.setEnabled(false);
        nextButton.setHorizontalAlignment(SwingConstants.CENTER);
        nextButton.setFont(nextButton.getFont().deriveFont(18f));
        confirmInfoStuffPanel.add(nextButton, nextButtonConstraints);

        backButton = new JButton("Back");
        backButton.setEnabled(false);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setFont(backButton.getFont().deriveFont(18f));
        confirmInfoStuffPanel.add(backButton, backButtonConstraints);

        this.add(confirmInfoStuffPanel, confirmInfoStuffConstraints);

        this.setVisible(true);

        nextButton.addActionListener(e -> {

            if (
                    otpDigit1.getText().isEmpty() || otpDigit2.getText().isEmpty() ||
                    otpDigit3.getText().isEmpty() || otpDigit4.getText().isEmpty() ||
                    otpDigit5.getText().isEmpty() || otpDigit6.getText().isEmpty())
            {
                errorMessageLabel.setText("Please fill in all missing digits");
            }

            int[] userInputOTP = new int[]{
                    Integer.parseInt(otpDigit1.getText()),
                    Integer.parseInt(otpDigit2.getText()),
                    Integer.parseInt(otpDigit3.getText()),
                    Integer.parseInt(otpDigit4.getText()),
                    Integer.parseInt(otpDigit5.getText()),
                    Integer.parseInt(otpDigit6.getText())
            };
            if (Arrays.equals(userInputOTP, OTP)){
                errorMessageLabel.setText("");
                System.out.println("Correct OTP");
                mainframe.panelOutroRight();
            }
            else {
                if (--triesLeft == 0){
                    mainframe.dispose();
                }
                errorMessageLabel.setText("Invalid OTP Number. You have " + triesLeft + " tries left.");
                errorMessageLabel.setForeground(new Color(173, 74, 59));
            }
        });

        backButton.addActionListener(e ->{
            nextButton.setEnabled(false);
            backButton.setEnabled(false);
            mainframe.panelOutroLeft();
        });
        otpDigit1.getDocument().addDocumentListener(new TextSequenceListener(null, otpDigit2));
        otpDigit2.getDocument().addDocumentListener(new TextSequenceListener(otpDigit1, otpDigit3));
        otpDigit3.getDocument().addDocumentListener(new TextSequenceListener(otpDigit2, otpDigit4));
        otpDigit4.getDocument().addDocumentListener(new TextSequenceListener(otpDigit3, otpDigit5));
        otpDigit5.getDocument().addDocumentListener(new TextSequenceListener(otpDigit4, otpDigit6));
        otpDigit6.getDocument().addDocumentListener(new TextSequenceListener(otpDigit5, null));

    }

    private void initOTPBoxSettings(JTextField OTPField){
        PlainDocument doc = (PlainDocument) OTPField.getDocument();
        doc.setDocumentFilter(new OTPDigitFilter());
    }

    private void addOTPDigit(JTextField OTPDigit, GridBagConstraints OTPConstraints){

        OTPDigit.setBackground(OTPPanel.getBackground());
        OTPDigit.setHorizontalAlignment(SwingConstants.CENTER);
        OTPDigit.setPreferredSize(new Dimension(30, 40));
        OTPDigit.setMaximumSize(new Dimension(30, 40));
        OTPDigit.setFont(OTPDigit.getFont().deriveFont(18f));
        OTPPanel.add(OTPDigit, OTPConstraints);
    }

    private int[] generateOTPNumber(){
        Random rand = new Random();
        int[] secretOTP = new int[6];
        for (int i = 0; i < secretOTP.length; ++i){
            secretOTP[i] = rand.nextInt(10);
        }
        return secretOTP;
    }
    private void sendOTPToEmail(int[] OTP){
        String email = mainframe.getRegistrator().getEmail();
        String header = "Verification Code";
        String body = "Your OTP is " + Arrays.toString(OTP) + ". If you did not request this, please ignore this email. Do not share your OTP number with anyone.";
        new EmailSender(email, header, body);
    }

    @Override
    public void slideInLeft() {
        int startPos = 0;
        for (int i = 0; i < 40; ++i) {
            startPos = (int) pow(startPos + 1.5, 1.05);
        }
        otpDigit1.setText("");
        otpDigit2.setText("");
        otpDigit3.setText("");
        otpDigit4.setText("");
        otpDigit5.setText("");
        otpDigit6.setText("");
        errorMessageLabel.setText("");

        movingInsets.right = startPos;
        this.setVisible(true);
        updateAnimation();
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40) {
                movingInsets.right = (int) round(pow(movingInsets.right, (1 / 1.05)) - 1.5);
                if (movingInsets.right < 0) {
                    movingInsets.right = 0;
                }
                updateAnimation();
                ++loopCycles;
            } else {
                ((Timer) e.getSource()).stop();

                Color borderColor = null;
                if (UIManager.getLookAndFeel() instanceof FlatDarkLaf){
                    borderColor = Color.white;
                }
                else if (UIManager.getLookAndFeel() instanceof FlatLightLaf){
                    borderColor = Color.black;
                }
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                OTP = generateOTPNumber();
//                sendOTPToEmail(OTP);
                System.out.println(Arrays.toString(OTP));
            }
        }).start();
    }

    @Override
    public void slideOutLeft() {
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40){
                movingInsets.right = (int) pow(movingInsets.right + 1.5, 1.05);
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)e.getSource()).stop();
                setVisible(false);
                mainframe.panelIntroRight();
            }
        }).start();
    }

    @Override
    public void slideOutRight() {
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40){
                movingInsets.left = (int) pow(movingInsets.left + 1.5, 1.05);
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)e.getSource()).stop();
                setVisible(false);
                mainframe.panelIntroLeft();
            }
        }).start();
    }

    private void updateAnimation() {
        layout.setConstraints(enterOTPLabel, enterOTPConstraints);
        layout.setConstraints(OTPSubLabel, otpSubConstraints);
        layout.setConstraints(OTPPanel, otpPanelConstraints);
        layout.setConstraints(confirmInfoStuffPanel, confirmInfoStuffConstraints);
        this.revalidate();
        this.repaint();
    }

}
