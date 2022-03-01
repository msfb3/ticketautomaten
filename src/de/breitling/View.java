package de.breitling;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame window;
    private JLabel lblLeer;
    private JComboBox<Ticket> dkpTicket;
    private JLabel lblTyp;
    private JTextField txtTyp;
    private JLabel lblPreis;
    private JTextField txtPreis;
    private JLabel lblGültigkeit;
    private JTextField txtGültigkeit;
    private JLabel lblFahrziel;
    private JTextField txtFahrziel;
    private JLabel lblBargeld;
    private JTextField txtBargeld;
    private JLabel lblRückgeld;
    private JTextField txtRückgeld;
    private JButton cmdDrucken;



    public View() {
    window = new JFrame("Ticketautomat");
    window.setSize(500,500);
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setLayout(new GridLayout(0,2));


    SQLHandler sqlH = new SQLHandler("localhost:3306", "root", "");
    Ticket[] tickets = (Ticket[])sqlH.getTickets().toArray();

    lblLeer = new JLabel();
    window.add(lblLeer);

    dkpTicket = new JComboBox<>(tickets);
    window.add(dkpTicket);



    lblTyp = new JLabel("Typ:");
    txtTyp = new JTextField();
    window.add(lblTyp);
    window.add(txtTyp);
    lblPreis = new JLabel("Preis:");
    txtPreis = new JTextField();
    window.add(lblPreis);
    window.add(txtPreis);
    lblGültigkeit = new JLabel("Gültigkeit:");
    txtGültigkeit = new JTextField();
    window.add(lblGültigkeit);
    window.add(txtGültigkeit);
    lblFahrziel = new JLabel("Fahrziel");
    txtFahrziel = new JTextField();
    window.add(lblFahrziel);
    window.add(txtFahrziel);
    lblBargeld = new JLabel("Bargeld");
    window.add(lblBargeld);
    txtBargeld = new JTextField();

    window.add(txtBargeld);
    lblRückgeld = new JLabel("Rückgeld");
    window.add(lblRückgeld);

    txtRückgeld = new JTextField();
    window.add(txtRückgeld);

    cmdDrucken = new JButton("Drucken");
    window.add(cmdDrucken);


    window.setVisible(true);





}
    public JComboBox<Ticket> getDkpTicket() {
        return dkpTicket;
    }

    public JLabel getLblTyp() {
        return lblTyp;
    }

    public JTextField getTxtTyp() {
        return txtTyp;
    }

    public JLabel getLblPreis() {
        return lblPreis;
    }

    public JTextField getTxtPreis() {
        return txtPreis;
    }

    public JLabel getLblGültigkeit() {
        return lblGültigkeit;
    }

    public JTextField getTxtGültigkeit() {
        return txtGültigkeit;
    }

    public JLabel getLblFahrziel() {
        return lblFahrziel;
    }

    public JTextField getTxtFahrziel() {
        return txtFahrziel;
    }

    public JLabel getLblBargeld() {
        return lblBargeld;
    }

    public JTextField getTxtBargeld() {
        return txtBargeld;
    }

    public JLabel getLblRückgeld() {
        return lblRückgeld;
    }

    public JTextField getTxtRückgeld() {
        return txtRückgeld;
    }

    public JButton getCmdDrucken() {
        return cmdDrucken;
    }


    public void setTxtTyp(JTextField txtTyp) {
        this.txtTyp = txtTyp;
    }

    public void setTxtPreis(JTextField txtPreis) {
        this.txtPreis = txtPreis;
    }

    public void setTxtGültigkeit(JTextField txtGültigkeit) {
        this.txtGültigkeit = txtGültigkeit;
    }

    public void setTxtFahrziel(JTextField txtFahrziel) {
        this.txtFahrziel = txtFahrziel;
    }

    public void setDkpTicket(JComboBox<Ticket> dkpTicket) {
        this.dkpTicket = dkpTicket;
    }
}
