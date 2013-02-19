/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import peli21.highscore.Highscorelista;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class HighScoreIkkuna implements ActionListener {

    private Component frame;
    private Highscorelista highscore;

    public HighScoreIkkuna(Peli peli) {
        this.highscore = peli.getHighscore();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //Custom button text
        Object[] options = {"Clear Highscore ...",
            "Dismiss"};
        int n = JOptionPane.showOptionDialog(frame,
                highscore.toString(),
                "High score",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);


        if (n == 0) {

            if (highscore.getTuloslista().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "What is your problem?");
                return;
            }

            int m = JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear the highscore?", "Clear highscore?", JOptionPane.OK_CANCEL_OPTION);
            System.out.println(m);
            if (m == 0) {
                highscore.tyhjenna();
            }


        }

    }
}
