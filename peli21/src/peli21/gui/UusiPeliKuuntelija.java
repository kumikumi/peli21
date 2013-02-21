/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class UusiPeliKuuntelija implements ActionListener {

    private Peli peli;
    private Kayttoliittyma kali;
    private Component frame;
    private String nameText;
    private String widthText;
    private String heightText;
    private String timeText;

    public UusiPeliKuuntelija(Peli peli, Kayttoliittyma kali) {
        this.peli = peli;
        this.kali = kali;
        this.nameText = "";
        this.widthText = "10";
        this.heightText = "6";
        this.timeText = "20";
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JTextField xField = new JTextField(5);
        xField.setText(widthText);
        JTextField yField = new JTextField(5);
        yField.setText(heightText);
        JTextField nameField = new JTextField(10);
        nameField.setText(nameText);
        JTextField timeField = new JTextField(5);
        timeField.setText(timeText);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Width:"));
        myPanel.add(xField);
        myPanel.add(new JLabel("Height:"));
        myPanel.add(yField);
        myPanel.add(new JLabel("Player name:"));
        myPanel.add(nameField);
        myPanel.add(new JLabel("Time: "));
        myPanel.add(timeField);



        int result = JOptionPane.showConfirmDialog(null, myPanel, "New Game", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (nameField.getText().contains(":")) {
                    JOptionPane.showMessageDialog(frame, "Illegal character: :");
                    return;
                }
                String name;
                if (nameField.getText().isEmpty()) {
                    name = "Anonymous";
                } else {
                    name = nameField.getText();
                }

                if (Integer.parseInt(timeField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(frame, "How about no");
                    return;
                }
                
                if (Integer.parseInt(xField.getText()) < 10) {
                      JOptionPane.showMessageDialog(frame, "Width must be at least 10");
                      return;
                }
                
                if (Integer.parseInt(yField.getText()) < 5) {
                      JOptionPane.showMessageDialog(frame, "Height must be at least 5");
                      return;
                }

                
                // Tehdään uusi peli
                peli.uusiPeli(name, Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()), Integer.parseInt(timeField.getText()));
                kali.paivitaKoko();
                // Muistetaan kenttien sisällöt
                nameText = nameField.getText();
                widthText = xField.getText();
                heightText = yField.getText();
                timeText = timeField.getText();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Wtf?");
            }
        }
    }
}
