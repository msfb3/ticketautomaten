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
    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        Connection con = null;
        try {
            String jdbcConnectionString = "jdbc:mariadb://" + url + "/tickets";
            con = (Connection)DriverManager.getConnection(jdbcConnectionString,
                    user,
                    password);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ticket");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String destination = rs.getString("ziel");
                int price = rs.getInt("preis");
                String type = null;
                if (rs.getInt("typ") == 1) {
                    type = "Einzelfahrt";
                } else {
                    type = "Hin- und Rück-Ticket";
                }
                Ticket ticket = new Ticket(type, price, destination);
                tickets.add(ticket);
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