package de.breitling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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


                File file = new File("Ticket.txt");

                FileWriter writeFile = null;
                try {
                    writeFile = new FileWriter(file);
                    writeFile.write(view.getTxtTyp().getText() + "\n");
                    writeFile.write(view.getTxtPreis().getText() + "\n");
                    writeFile.write(view.getTxtGültigkeit().getText() + "\n");
                    writeFile.write(view.getTxtFahrziel().getText() + "\n");
                    writeFile.write(view.getTxtBargeld().getText() + "\n");
                    writeFile.write(view.getTxtRückgeld().getText() + "\n");


                    writeFile.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }





            }
        });
    }
}
