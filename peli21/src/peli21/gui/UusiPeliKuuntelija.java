/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class UusiPeliKuuntelija implements ActionListener {

    private Peli peli;
    private Kayttoliittyma kali;
    private Component frame;
    private String nimi;
    private String width;
    private String height;

    public UusiPeliKuuntelija(Peli peli, Kayttoliittyma kali) {
        this.peli = peli;
        this.kali = kali;
        this.nimi = "";
        this.width = "20";
        this.height = "10";
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      JTextField xField = new JTextField(5);
      xField.setText(width);
      JTextField yField = new JTextField(5);
      yField.setText(height);
      JTextField nameField = new JTextField(10);
      nameField.setText(nimi);
      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Width:"));
      myPanel.add(xField);
      myPanel.add(new JLabel("Height:"));
      myPanel.add(yField);
      myPanel.add(new JLabel("Player name:"));
      myPanel.add(nameField);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "New Game", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         try {
             String name;
        if (nameField.getText().isEmpty()) {
            name = "Anonymous";
        } else {
            name = nameField.getText();
        }
        peli.uusiPeli(name, Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
        kali.paivitaKoko();
        nimi = name;
        width = xField.getText();
        height = yField.getText();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Wtf?");
         }
      }
    }
}
