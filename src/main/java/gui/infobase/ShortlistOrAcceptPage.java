package gui.infobase;

import controller.InfobaseMainframe;
import gui.ImageEmbedded;
import gui.JPanelImageButton;

import javax.swing.*;
import java.awt.*;

public class ShortlistOrAcceptPage extends JPanel {
    private final InfobaseMainframe main;
    private ShortlistPage shortlistPage;
    private AcceptPage acceptPage;
    private JPanelImageButton shortlistButton;
    private JPanelImageButton acceptButton;

    public ShortlistOrAcceptPage(InfobaseMainframe main) {
        this.main = main;
        setLayout(new GridBagLayout());

        initComponents();
        initListeners();
    }

    private void initListeners() {
        shortlistButton.addActionListener(e -> main.showShortlistPage());
        acceptButton.addActionListener(e -> main.showApplicantListPage());
    }

    private void initComponents() {
        JLabel chooseShortlistOrAccept = new JLabel("Do you want to shortlist or accept applicants?");
        chooseShortlistOrAccept.setHorizontalAlignment(SwingConstants.CENTER);
        chooseShortlistOrAccept.setFont(chooseShortlistOrAccept.getFont().deriveFont(24f));
        GridBagConstraints shortlistOrAcceptConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        this.add(chooseShortlistOrAccept, shortlistOrAcceptConstraints);

        JPanel shortlistOrAcceptPanel = new JPanel();
        shortlistOrAcceptPanel.setLayout(new GridBagLayout());

        shortlistButton = new JPanelImageButton("Shortlist", ImageEmbedded.ADD_IMAGE, ImageEmbedded.ADD_IMAGE, 120, 120, JPanelImageButton.BOTTOM);
        GridBagConstraints shortlistButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        shortlistOrAcceptPanel.add(shortlistButton, shortlistButtonConstraints);

        acceptButton = new JPanelImageButton("Accept",  ImageEmbedded.ADD_IMAGE, ImageEmbedded.ADD_IMAGE, 120, 120, JPanelImageButton.BOTTOM);
        GridBagConstraints acceptButtonConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        shortlistOrAcceptPanel.add(acceptButton, acceptButtonConstraints);

        GridBagConstraints panelConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        this.add(shortlistOrAcceptPanel, panelConstraints);
    }
}
