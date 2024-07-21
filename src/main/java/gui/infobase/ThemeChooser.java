package gui.infobase;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class ThemeChooser {
    public static final LookAndFeel darkMode = new FlatDarkLaf();
    public static final LookAndFeel lightMode = new FlatLightLaf();
    public static final int BOREALIS = 1;
    public static final int PORNHUB = 2; //rename this in the future, leave it here for fun for now
    public static final int NOSTALGIA = 3;
    public static final int NIGHT = 4;

    public static final int MINT = 5;
    public static final int TIDE = 6;
    public static final int UNICORN = 7;
    public static final int INK = 8;

    private void setDark(JFrame frame) {
        try {
            UIManager.setLookAndFeel(darkMode);
            UIManager.put("Button.background", new Color(255, 255, 255));
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
    private static void setLight() {
        try {
            UIManager.setLookAndFeel(lightMode);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDarkBorealis(JFrame frame){
        setDark(frame);
    }

    public static void setDefaultComponentProperties(){
        UIManager.put( "ScrollBar.thumbArc", 999 );
        UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
    }


}
