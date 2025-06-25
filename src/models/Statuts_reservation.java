package models;

public class Statuts_reservation {

    private int id_statuts_reservation;
    private String statut;
    
    public Statuts_reservation() {
    }
    public int getId_statuts_reservation() {
        return id_statuts_reservation;
    }
    public void setId_statuts_reservation(int id_statuts_reservation) {
        this.id_statuts_reservation = id_statuts_reservation;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

}
