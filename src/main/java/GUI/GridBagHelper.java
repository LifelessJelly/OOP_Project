package GUI;

import javax.swing.*;
import java.awt.*;

public class GridBagHelper {
    public static void initGridBag(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        ((GridBagLayout)panel.getLayout()).columnWidths = new int[]{0, 0};
        ((GridBagLayout)panel.getLayout()).rowHeights = new int[]{0, 0};
        ((GridBagLayout)panel.getLayout()).columnWeights = new double[]{0.0, 0.0, 1.0E-4};
        ((GridBagLayout)panel.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};
    }
}
