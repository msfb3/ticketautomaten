package de.breitling;
import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class SQLHandler {
    private String url;
    private String user;
    private String password;
    public SQLHandler(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection openConnection() throws SQLException {
        String jdbcConnectionString = "jdbc:mariadb://" + url + "/tickets";
        return (Connection)DriverManager.getConnection(jdbcConnectionString,
                user,
                password);
    }
    public Ticket[] getTickets() {
        Ticket[] tickets = null;
        Connection con = null;
        try {
            con = openConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT count(*) FROM ticket");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            tickets = new Ticket[count];
            stmt = con.prepareStatement("SELECT * FROM ticket");
            rs = stmt.executeQuery();
            for (int i = 0; i < count; i++) {
                rs.next();
                String destination = rs.getString("ziel");
                int price = rs.getInt("preis");
                String type = null;
                if (rs.getInt("typ") == 1) {
                    type = "Einzelfahrt";
                } else {
                    type = "Hin- und Rück-Ticket";
                }
                Ticket ticket = new Ticket(type, price, destination);
                tickets[i] = ticket;
            }
        } catch (SQLException sqlEx) {
            System.out.print("Error in SQL! ");
            System.out.println(sqlEx.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqlEx) {
                    // nein.
                }
            }
        }
        return tickets;
    }

    public void createArchiveTicket(Ticket ticket, double moneyGiven, double moneyReturned) {
        try {
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO archive (price, is_valid, destination, money_given, money_returned) " +
                    "VALUES (?,?,?,?,?)");
            stmt.setDouble(1,ticket.getPreis());
            stmt.setBoolean(2, ticket.isGültigkeit());
            stmt.setString(3,ticket.getFahrziel());
            stmt.setDouble(4,moneyGiven);
            stmt.setDouble(5,moneyReturned);
            stmt.executeQuery();
        } catch (SQLException sqlEX) {
            System.out.println("Something went wrong with saving the Ticket" + sqlEX.getMessage());
        }
    }
    public void deleteArchiveTicket() {
        try {
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM archive");
            stmt.executeQuery();
        } catch (SQLException sqlEx) {
            System.out.println("Something went wrong with deleting the Tickets. " +sqlEx.getMessage());
        }
    }
}