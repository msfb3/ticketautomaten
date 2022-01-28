package de.breitling;




public class Automat {
    public static void main(String[] args) {
        View view = new View();
        Ticket ticket = new Ticket();
        Controller controller = new Controller(ticket, view);
    }
}
