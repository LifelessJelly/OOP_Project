package GUI;

import javax.imageio.ImageIO;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

public class LoginPage extends JFrame{
    public LoginPage() {
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new RoundedBorder());
        try {
            this.setContentPane(new ImagePanel(ImageIO.read(new File("src/main/java/WhatsApp Image 2024-05-31 at 23.27.42_a7d7c681.jpg"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagHelper.initGridBag(mainPanel);
        contentPane.add(mainPanel, new GridBagConstraints(9, 1, 10, 10, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());

    }
}
