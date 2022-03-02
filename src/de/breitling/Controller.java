package de.breitling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;

public class Controller {
    private View view;
    private Ticket ticket;

    public Controller(Ticket ticket, View view) {
        this.ticket = ticket;
        this.view = view;
        initiAction();




    }
    public void initiAction() {
        view.getDkpTicket() .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket selectedTicket = (Ticket) view.getDkpTicket().getSelectedItem();
              view.getTxtTyp().setText(selectedTicket.getTyp());
                view.getTxtPreis().setText(Double.toString(selectedTicket.getPreis()));
                view.getTxtGültigkeit().setText(Boolean.toString(selectedTicket.isGültigkeit()));
                view.getTxtFahrziel().setText(selectedTicket.getFahrziel());
            }
        });
        view.getCmdDrucken().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket selectedTicket = (Ticket) view.getDkpTicket().getSelectedItem();
                double bargeldeingabe;
                bargeldeingabe = Double.parseDouble(view.getTxtBargeld().getText());
                double rückgeld = bargeldeingabe - selectedTicket.getPreis();
                view.getTxtRückgeld().setText(Double.toString(rückgeld));


                SQLHandler sqlH = new SQLHandler(SQLConfig.SQL_URL,SQLConfig.SQL_USER, SQLConfig.SQL_PASSWORD);
                sqlH.createArchiveTicket(selectedTicket, bargeldeingabe, rückgeld);







            }
        });
    }
}
