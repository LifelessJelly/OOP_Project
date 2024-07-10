package gui;

import controller.RegistrationMainframe;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public abstract class SlidingPanel extends JPanel {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final float IN = 1f;
    public static final float OUT = 2f;
    protected RegistrationMainframe registrationMainframe;
    protected Insets movingInsets;
    protected int loopCycles = 0;


    /**
     * Animates the sliding of a panel from the left side into view.
     * This method triggers the slideAnimation method with the direction set to LEFT and action set to IN.
     */

    public void slideInLeft() {
        slideAnimation(LEFT, IN);
    }

    /**
     * Animates the sliding of a panel from the right side into view.
     * This method triggers the slideAnimation method with the direction set to RIGHT and action set to IN.
     */

    public void slideInRight() {
        slideAnimation(RIGHT, IN);
    }

    /**
     * Animates the sliding of a panel towards the left side to hide it.
     * This method triggers the slideAnimation method with the direction set to LEFT and action set to OUT.
     */
    public void slideOutLeft() {
        slideAnimation(LEFT, OUT);
    }

    /**
     * Animates the sliding of a panel towards the right side to hide it.
     * This method triggers the slideAnimation method with the direction set to RIGHT and action set to OUT.
     */
    public void slideOutRight() {
        slideAnimation(RIGHT, OUT);
    }

    /**
     * Performs a slide animation for the panel based on the specified direction and action.
     *
     * @param direction The direction of the slide animation (LEFT or RIGHT).
     * @param going The action of the slide animation (IN or OUT).
     */
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
                        registrationMainframe.panelIntroLeft();
                    }
                    else {
                        registrationMainframe.panelIntroRight();
                    }
                }
            }
        }).start();
    }
    /**
     * Updates the animation of the panel during the sliding process.
     */
    protected void updateAnimation(){

    }
    /**
     * Sets the activation status of buttons within the panel.
     *
     * @param activated A boolean value indicating whether the buttons should be activated or deactivated.
     */
    // rather useless function if your buttons are not in the main sliding panel, might rewrite it with recursion
    protected void setButtonsActivated(boolean activated){
        for (Component component : this.getComponents()){
            if (component instanceof JButton){
                component.setEnabled(activated);
            }
        }
    }
    /**
     * Calculates the starting position for the slide animation.
     *
     * @return The calculated starting position for the slide animation.
     */
    private int getStartPos(){
        int startPos = 0;
        for (int i = 0; i < 40; ++i ){
            startPos = (int) pow(startPos + 1.5, 1.05);
        }
        return startPos;
    }
}
