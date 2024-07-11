package gui.registration;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextSequenceListener implements DocumentListener {
    JTextField previousTextBox;
    JTextField nextTextBox;

    public TextSequenceListener(JTextField previousTextBox, JTextField nextTextBox) {
        this.previousTextBox = previousTextBox;
        this.nextTextBox = nextTextBox;

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        textInput(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        textRemoved(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    private void textInput(DocumentEvent e){
        if (e.getDocument().getLength() != 0 && nextTextBox != null) {
            nextTextBox.requestFocusInWindow();
        }
    }
    private void textRemoved(DocumentEvent e){
        if (e.getDocument().getLength() == 0 && previousTextBox != null) {
            previousTextBox.requestFocusInWindow();
        }
    }
}
