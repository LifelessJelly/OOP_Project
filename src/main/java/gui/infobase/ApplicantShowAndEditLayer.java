package gui.infobase;

import controller.InfobaseMainframe;

import javax.swing.*;
import java.awt.*;

public class ApplicantShowAndEditLayer extends JLayeredPane {
    final ApplicantListPage applicantListPage;
    EditApplicant editApplicant;
    final InfobaseMainframe main;
    public ApplicantShowAndEditLayer(InfobaseMainframe main) {
        super();
        this.main = main;
        applicantListPage = new ApplicantListPage(main);
        applicantListPage.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(new GridBagLayout());
        applicantListPage.setBounds(0, 0, 100, 100);
        this.add(applicantListPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    public void switchToApplicantListPage() {
        this.add(applicantListPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        new Timer(1, e -> {
            if (editApplicant.getZoomFactor() < 1.2){
                editApplicant.decrementKeyframe(0.2/5, 1f/5);
                applicantListPage.incrementKeyframe(0.2/5, 1f/5);

            }
            else {
                this.remove(editApplicant);
                main.getContentPane().validate();
                main.getContentPane().repaint();
                ((Timer)e.getSource()).stop();

            }
        }).start();
    }

    public void switchToEditApplicantPage(int index) {
        editApplicant = new EditApplicant(index, main);
        editApplicant.setBackground(new Color(0, 0, 0, 0));
            this.add(editApplicant, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        new Timer(1, e -> {
            if (applicantListPage.getZoomFactor() > 0.8) {
                applicantListPage.decrementKeyframe(0.2 / 5, 1f / 5);
                editApplicant.incrementKeyframe(0.2 / 5, 1f / 5);

            } else {
                this.remove(applicantListPage);
                main.getContentPane().validate();
                main.getContentPane().repaint();
                ((Timer) e.getSource()).stop();
            }
        }).start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
    }


}
