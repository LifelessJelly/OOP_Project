package gui.infobase;

import controller.InfobaseMainframe;
import data.Applicant;

import javax.swing.*;
import java.awt.*;

public class ApplicantShowAndEditLayer extends JLayeredPane {
    ApplicantListPage applicantListPage;
    EditApplicant editApplicant;
    InfobaseMainframe main;

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

//        Timer looper = new Timer(16, e -> {
//            if (editApplicant.getZoomFactor() < 1.2){
//                editApplicant.decrementKeyframe(0.2/5, 1f/5);
//                applicantListPage.incrementKeyframe(0.2/5, 1f/5);
//
//            }
//            else {
//                this.remove(editApplicant);
//                ((Timer)e.getSource()).stop();
//
//            }
//        });
//        looper.start();
        Thread looperThread = new Thread(() -> {
            while (editApplicant.getZoomFactor() < 1.2){
                editApplicant.decrementKeyframe(0.2/5, 1f/5);
                applicantListPage.incrementKeyframe(0.2/5, 1f/5);
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.remove(editApplicant);
        });
        looperThread.start();

        //I can't fucking solve the performance issue it's already 1 am I was supposed to sleep 2 hours ago

        validate();
        repaint();
    }

    public void switchToEditApplicantPage(Applicant applicant, int index) {
        editApplicant = new EditApplicant(applicant, index, main);
        editApplicant.setBackground(new Color(0, 0, 0, 0));
        this.add(editApplicant, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

//        Timer looper = new Timer(16, e -> {
//            long currentTime = System.nanoTime();
//            if (applicantListPage.getZoomFactor() > 0.8) {
//                applicantListPage.decrementKeyframe(0.2 / 5, 1f / 5);
//                editApplicant.incrementKeyframe(0.2 / 5, 1f / 5);
//
//            } else {
//                this.remove(applicantListPage);
//
//                ((Timer) e.getSource()).stop();
//            }
//            System.out.println("Loop time: " + (System.nanoTime() - currentTime));
//        });
//        looper.start();
        Thread looperThread = new Thread(() -> {
            System.out.println("Thread started");
            while (applicantListPage.getZoomFactor() > 0.8){
                applicantListPage.decrementKeyframe(0.2/5, 1f/5);
                editApplicant.incrementKeyframe(0.2/5, 1f/5);
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.remove(applicantListPage);
        });
        looperThread.start();

        validate();
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
    }
}
