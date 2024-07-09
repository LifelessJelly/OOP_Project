package GUI.registration;

import GUI.SlidingPanel;

import javax.swing.*;
import java.awt.*;

public class Welcome extends SlidingPanel {
    private int loopCycles = 0;
    private float alpha = 0;
    private JLabel welcomeLabel;
    private JLabel getYouStartedLabel;
    private JButton kaiShiButton;
    private GridBagConstraints welcomeConstraints;
    private GridBagConstraints getYouStartedConstraints;
    private GridBagConstraints kaiShiConstraints;
    private GridBagLayout layout;

    public Welcome(RegistrationMainframe registrationMainframe) {
        this.registrationMainframe = registrationMainframe;
        initComponents();
    }

    private void initComponents() {
        movingInsets = new Insets(0, 0, 5, 0);
        layout = new GridBagLayout();
        welcomeConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                movingInsets, 0, 0);
        getYouStartedConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                movingInsets, 0, 0);
        kaiShiConstraints = new GridBagConstraints(0, 2, 3, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                movingInsets, 0, 0);
        this.setLayout(layout);
        this.setVisible(true);


        welcomeLabel = new JLabel("Welcome to Guangdong Skibidi CeSuo Technology Co. Ltd. Application Page");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("KaiTi", Font.PLAIN, 22));
        this.add(welcomeLabel, welcomeConstraints);


        getYouStartedLabel = new JLabel("Let's get you started");
        getYouStartedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getYouStartedLabel.setFont(new Font("KaiTi", Font.PLAIN, 18));
        this.add(getYouStartedLabel, getYouStartedConstraints);

        kaiShiButton = new JButton("Let's go!");
        kaiShiButton.setHorizontalAlignment(SwingConstants.CENTER);
        kaiShiButton.setFont(new Font("KaiTi", Font.PLAIN, 20));
        this.add(kaiShiButton, kaiShiConstraints);
        kaiShiButton.addActionListener(e -> {
            setButtonsActivated(false);
            registrationMainframe.panelOutroRight();});


        new Timer(10, e -> {
            if (alpha+0.01f <= 1){
                setAlpha(alpha += 0.01f);
//                System.out.println("painting");
            }
            else {
//                System.out.println("timer has stopped");
                ((Timer)e.getSource()).stop();
            }
        }).start();

    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paintComponent(g);
    }

    @Override
    protected void updateAnimation() {
        layout.setConstraints(welcomeLabel, welcomeConstraints);
        layout.setConstraints(getYouStartedLabel, getYouStartedConstraints);
        layout.setConstraints(kaiShiButton, kaiShiConstraints);
        this.revalidate();
        this.repaint();
    }

}
