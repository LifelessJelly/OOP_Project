package other_GUItester.GUI_temp;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import controller.MainFrame;
import java.awt.Color;

public class option1test extends JPanel{

    private MainFrame main;

    public option1test(MainFrame main) {
        setBackground(Color.RED);
        setForeground(Color.RED);
        this.main=main;
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        // TODO Auto-generated constructor stub

        //===COMPONENT LISTENER (WINDOW)===//
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                int adjustedFontSizeTitle=(int)(getWidth()/25);
                int adjustedFontSizeBody=(int)(getWidth()/35);
                Font bodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)adjustedFontSizeBody);
                Font smallBodyFont=new Font("Comic Sans MS", Font.PLAIN, (int)(Math.round(adjustedFontSizeBody*0.8)));
                //lblOption.setFont(bodyFont);
                //panelPanel.setSize((int)(Math.round(main.getWidth()*0.75)),(int)(Math.round(main.getHeight()*0.75)));

            }
        });
    }


}
