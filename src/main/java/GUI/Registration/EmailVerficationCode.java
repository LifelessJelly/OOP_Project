package GUI.Registration;

import Controller.ApplicantRegistrator;
import Controller.EmailSender;
import Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class EmailVerficationCode extends SlidingPanel{
    Mainframe mainframe;
    ApplicantRegistrator registrator;
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


    public EmailVerficationCode(Mainframe mainframe, ApplicantRegistrator registrator) {
        this.mainframe = mainframe;
        this.registrator = registrator;
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


        JTextField OTPDigit1 = new JTextField();
        JTextField OTPDigit2 = new JTextField();
        JTextField OTPDigit3 = new JTextField();
        JTextField OTPDigit4 = new JTextField();
        JTextField OTPDigit5 = new JTextField();
        JTextField OTPDigit6 = new JTextField();
        addOTPDigit(OTPDigit1, OTPDigit1Constraints);
        addOTPDigit(OTPDigit2, OTPDigit2Constraints);
        addOTPDigit(OTPDigit3, OTPDigit3Constraints);
        addOTPDigit(OTPDigit4, OTPDigit4Constraints);
        addOTPDigit(OTPDigit5, OTPDigit5Constraints);
        addOTPDigit(OTPDigit6, OTPDigit6Constraints);
        this.add(OTPPanel, otpPanelConstraints);

        confirmInfoStuffPanel = new JPanel();

        confirmInfoStuffPanel.setLayout(new GridBagLayout());
        GridBagConstraints errorMessageConstraints = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0,0);
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0);


        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(errorMessageLabel.getBackground());
        errorMessageLabel.setPreferredSize(new Dimension(400, 20));
        errorMessageLabel.setMinimumSize(new Dimension(400, 20));
        errorMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        errorMessageLabel.setFont(errorMessageLabel.getFont().deriveFont(18f));
        confirmInfoStuffPanel.add(errorMessageLabel, errorMessageConstraints);

        JButton nextButton = new JButton("Next");
        nextButton.setHorizontalAlignment(SwingConstants.LEFT);
        nextButton.setFont(nextButton.getFont().deriveFont(18f));
        confirmInfoStuffPanel.add(nextButton, nextButtonConstraints);

        this.add(confirmInfoStuffPanel, confirmInfoStuffConstraints);

        this.setVisible(true);

        nextButton.addActionListener(e -> {
            int[] userInputOTP = new int[]{
                    Integer.parseInt(OTPDigit1.getText()),
                    Integer.parseInt(OTPDigit2.getText()),
                    Integer.parseInt(OTPDigit3.getText()),
                    Integer.parseInt(OTPDigit4.getText()),
                    Integer.parseInt(OTPDigit5.getText()),
                    Integer.parseInt(OTPDigit6.getText())
            };
            if (Arrays.equals(userInputOTP, OTP)){
                errorMessageLabel.setText("Your OTP is correct!");
                errorMessageLabel.setForeground(new Color(58, 227, 24));
            }
            else {
                if (--triesLeft == 0){
                    mainframe.dispose();
                }
                errorMessageLabel.setText("Invalid OTP Number. You have " + triesLeft + " tries left.");
                errorMessageLabel.setForeground(new Color(173, 74, 59));
            }
        });

    }

    private void addOTPDigit(JTextField OTPDigit, GridBagConstraints OTPConstraints){
        OTPDigit.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
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
        String email = registrator.getEmail();
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
                OTP = generateOTPNumber();
                sendOTPToEmail(OTP);
            }
        }).start();
    }

    private void updateAnimation() {
        layout.setConstraints(enterOTPLabel, enterOTPConstraints);
        layout.setConstraints(OTPSubLabel, otpSubConstraints);
        layout.setConstraints(OTPPanel, otpPanelConstraints);
        layout.setConstraints(confirmInfoStuffPanel, confirmInfoStuffConstraints);
        System.out.println(movingInsets.right);
        this.revalidate();
        this.repaint();
    }

}
