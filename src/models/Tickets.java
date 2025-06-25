package models;

public class Tickets {
    private int id;
    private int id_reservation;
    private int id_passager;
    private String numero_ticket;
    public Tickets() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_reservation() {
        return id_reservation;
    }
    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }
    public int getId_passager() {
        return id_passager;
    }
    public void setId_passager(int id_passager) {
        this.id_passager = id_passager;
    }
    public String getNumero_ticket() {
        return numero_ticket;
    }
    public void setNumero_ticket(String numero_ticket) {
        this.numero_ticket = numero_ticket;
    }
    
}
