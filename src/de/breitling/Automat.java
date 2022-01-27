package de.breitling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Automat {
    public static void main(String[] args) {

        JFrame window = new JFrame("Ticketautomat");
        window.setSize(500,500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(0,2));

        Ticket hamburg = new Ticket("Einzelfahrt",60,"Hamburg");
        Ticket berlin = new Ticket("Hin- und Rückfahrt", 100,"Berlin");
        Ticket[] tickets = {hamburg,berlin};

        JLabel lblLeer = new JLabel();
        window.add(lblLeer);

        JComboBox<Ticket> dkpTicket = new JComboBox<>(tickets);
        window.add(dkpTicket);



        JLabel lblTyp = new JLabel("Typ:");
        JTextField txtTyp = new JTextField();
        window.add(lblTyp);
        window.add(txtTyp);
        JLabel lblPreis = new JLabel("Preis:");
        JTextField txtPreis = new JTextField();
        window.add(lblPreis);
        window.add(txtPreis);
        JLabel lblGültigkeit = new JLabel("Gültigkeit:");
        JTextField txtGültigkeit = new JTextField();
        window.add(lblGültigkeit);
        window.add(txtGültigkeit);
        JLabel lblFahrziel = new JLabel("Fahrziel");
        JTextField txtFahrziel = new JTextField();
        window.add(lblFahrziel);
        window.add(txtFahrziel);
        JLabel lblBargeld = new JLabel("Bargeld");
        window.add(lblBargeld);
        JTextField txtBargeld = new JTextField();

        window.add(txtBargeld);
        JLabel lblRückgeld = new JLabel("Rückgeld");
        window.add(lblRückgeld);

        JTextField txtRückgeld = new JTextField();
        window.add(txtRückgeld);

        JButton cmdDrucken = new JButton("Drucken");
        window.add(cmdDrucken);


        window.setVisible(true);



        dkpTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket selectedTicket = (Ticket) dkpTicket.getSelectedItem();
                txtTyp.setText(selectedTicket.getTyp());
                txtPreis.setText(Double.toString(selectedTicket.getPreis()));
                txtGültigkeit.setText(Boolean.toString(selectedTicket.isGültigkeit()));
                txtFahrziel.setText(selectedTicket.getFahrziel());
            }
        });
        cmdDrucken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket selectedTicket = (Ticket) dkpTicket.getSelectedItem();
                double bargeldeingabe;
                bargeldeingabe = Double.parseDouble(txtBargeld.getText());
                double rückgeld = bargeldeingabe - selectedTicket.getPreis();
                txtRückgeld.setText(Double.toString(rückgeld));
                File file = new File("Ticket.txt");
                if (file.exists() == false) {
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                FileWriter writeFile = null;
                try {
                    writeFile = new FileWriter(file);
                    writeFile.write(txtTyp.getText() + "\n");
                    writeFile.write(txtPreis.getText() + "\n");
                    writeFile.write(txtGültigkeit.getText() + "\n");
                    writeFile.write(txtFahrziel.getText() + "\n");
                    writeFile.write(txtBargeld.getText() + "\n");
                    writeFile.write(txtRückgeld.getText() + "\n");


                    writeFile.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }





            }
        });

    }
}
