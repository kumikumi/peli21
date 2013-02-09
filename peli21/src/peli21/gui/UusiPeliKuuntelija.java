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

    public UusiPeliKuuntelija(Peli peli, Kayttoliittyma kali) {
        this.peli = peli;
        this.kali = kali;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      JTextField xField = new JTextField(5);
      xField.setText("20");
      JTextField yField = new JTextField(5);
      yField.setText("10");
      JTextField nameField = new JTextField(10);
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
             String nimi;
        if (nameField.getText().isEmpty()) {
            nimi = "Anonymous";
        } else {
            nimi = nameField.getText();
        }
        peli.uusiPeli(nimi, Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
        kali.paivitaKoko();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(frame, "Wtf?");
         }
      }
    }
}
