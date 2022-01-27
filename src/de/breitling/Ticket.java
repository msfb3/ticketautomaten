package de.breitling;

public class Ticket {
    private String typ;
    private double preis;
    private boolean gültigkeit;
    private String fahrziel;

    public Ticket(String typ, double preis, String fahrziel) {
        this.typ = typ;
        this.preis = preis;
        this.gültigkeit = true;
        this.fahrziel = fahrziel;
    }

    public String getTyp() {
        return typ;
    }

    public double getPreis() {
        return preis;
    }

    public boolean isGültigkeit() {
        return gültigkeit;
    }

    public String getFahrziel() {
        return fahrziel;
    }

    public void fahren() {
        System.out.println("Danke, das Sie mit uns gefahren sind.");
        gültigkeit = false;
    }

    @Override
    public String toString() {
        return this.fahrziel;
    }
}
