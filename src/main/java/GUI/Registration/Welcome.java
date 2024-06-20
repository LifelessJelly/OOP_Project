package GUI.Registration;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.pow;

public class Welcome extends SlidingPanel {
    private int loopCycles = 0;
    private float alpha = 0;
    GUI.Registration.Mainframe mainframe;
    Timer timer;
    Timer textTimer;
    private JLabel welcomeLabel;
    private JLabel getYouStartedLabel;
    private JButton kaiShiButton;
    private GridBagConstraints welcomeConstraints;
    private GridBagConstraints getYouStartedConstraints;
    private GridBagConstraints kaiShiConstraints;
    private GridBagLayout layout;
    private Insets movingInsetsButton;
    private Insets movingInsetsInstance;

    public Welcome(Mainframe mainframe) {
        this.mainframe = mainframe;
        initComponents();
    }

    private void initComponents() {
        movingInsetsInstance = new Insets(0, 0, 5, 0);
        movingInsetsButton = new Insets(50, 0, 5, 0);
        layout = new GridBagLayout();
        welcomeConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                movingInsetsInstance, 0, 0);
        getYouStartedConstraints = new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                movingInsetsInstance, 0, 0);
        kaiShiConstraints = new GridBagConstraints(0, 2, 3, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                movingInsetsButton, 0, 0);
        this.setLayout(layout);


        welcomeLabel = new JLabel("Welcome to Guangdong Skibidi CeSuo Technology Co. Ltd. Application Page");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("KaiTi", Font.PLAIN, 22));
        this.add(welcomeLabel, welcomeConstraints);


        getYouStartedLabel = new JLabel("让我们帮您入门");
        getYouStartedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getYouStartedLabel.setFont(new Font("KaiTi", Font.PLAIN, 18));
        this.add(getYouStartedLabel, getYouStartedConstraints);

        kaiShiButton = new JButton(">开始<");
        kaiShiButton.setHorizontalAlignment(SwingConstants.CENTER);
        kaiShiButton.setFont(new Font("KaiTi", Font.PLAIN, 18));
        this.add(kaiShiButton, kaiShiConstraints);
        kaiShiButton.addActionListener(e -> mainframe.panelOutroRight());

        this.setVisible(true);

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
        Composite composite = g2d.getComposite();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    @Override
    public void slideOutRight(){
        new Timer(10, f -> {
            if (loopCycles < 40){
                movingInsetsInstance.left = (int) pow(movingInsetsInstance.left + 1.5, 1.05);
                movingInsetsButton.left = (int) pow(movingInsetsButton.left + 1.5, 1.05);
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)f.getSource()).stop();
                this.setVisible(false);
                mainframe.panelIntroLeft();
            }
        }).start();
    }

    private void updateAnimation() {
        layout.setConstraints(welcomeLabel, welcomeConstraints);
        layout.setConstraints(getYouStartedLabel, getYouStartedConstraints);
        layout.setConstraints(kaiShiButton, kaiShiConstraints);
        this.revalidate();
        this.repaint();
        System.out.println(movingInsetsInstance.left);
    }
}
