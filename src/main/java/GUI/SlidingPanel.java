package GUI;

import GUI.Registration.Mainframe;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public abstract class SlidingPanel extends JPanel {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final float IN = 1f;
    public static final float OUT = 2f;
    protected Mainframe mainframe;
    protected Insets movingInsets;
    protected int loopCycles = 0;


    //TODO Clean up template the sliding animation codes into each of these functions, additionally, group left and right sliding in/out functions together, or even all four functions together if possible
    public void slideInLeft() {
        slideAnimation(LEFT, IN);
    }

    public void slideInRight() {
        slideAnimation(RIGHT, IN);
    }

    public void slideOutLeft() {
        slideAnimation(LEFT, OUT);
    }

    public void slideOutRight() {
        slideAnimation(RIGHT, OUT);
    }

    void slideAnimation(int direction, float going){
        assert (going == IN || going == OUT) && (direction == LEFT || direction == RIGHT);
        final int[] positionalOffset = new int[1];
        if (going == IN) {
            positionalOffset[0] = getStartPos();
            if (direction == LEFT) {
                movingInsets.right = positionalOffset[0];
            }
            else {
                movingInsets.left = positionalOffset[0];
            }
            updateAnimation();
        }
        setButtonsActivated(false);
        this.setVisible(true);
        loopCycles = 0;
        new Timer(10, e -> {
            if (loopCycles < 40){
                // I hate this language so much WHERE ARE MY POINTERS
                if (going == IN) {
                    positionalOffset[0] = (int) round(pow(positionalOffset[0], (1 / 1.05)) - 1.5);
                    if (positionalOffset[0] < 0) {
                        positionalOffset[0] = 0;
                    }
                }
                else {
                    positionalOffset[0] = (int) pow(positionalOffset[0] + 1.5, 1.05);
                }
                if (direction == LEFT){
                    movingInsets.right = positionalOffset[0];
                }
                else {
                    movingInsets.left = positionalOffset[0];
                }
                updateAnimation();
                ++loopCycles;
            }
            else {
                ((Timer)e.getSource()).stop();
                setButtonsActivated(true);
                if (going == OUT) {
                    this.setVisible(false);
                    if (direction == RIGHT) {
                        mainframe.panelIntroLeft();
                    }
                    else {
                        mainframe.panelIntroRight();
                    }
                }
            }
        }).start();
    }

    protected void updateAnimation(){

    }
    protected void setButtonsActivated(boolean activated){
        for (Component component : this.getComponents()){
            if (component instanceof JButton){
                component.setEnabled(activated);
            }
        }
    }
    private int getStartPos(){
        int startPos = 0;
        for (int i = 0; i < 40; ++i ){
            startPos = (int) pow(startPos + 1.5, 1.05);
        }
        return startPos;
    }
}
