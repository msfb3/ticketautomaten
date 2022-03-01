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
    public Ticket[] getTickets() {
        Ticket[] tickets = null;
        Connection con = null;
        try {
            String jdbcConnectionString = "jdbc:mariadb://" + url + "/tickets";
            con = (Connection)DriverManager.getConnection(jdbcConnectionString,
                    user,
                    password);
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
}