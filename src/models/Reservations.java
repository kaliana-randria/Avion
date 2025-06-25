package models;

import java.time.LocalDateTime;

public class Reservations {
    private int id_reservation;
    private int id_vol;
    private int id_classe;
    private int id_client;
    private int quantite;
    private int id_statut_reservation;
    private LocalDateTime date_reservation;
    public Reservations() {
    }
    public int getId_reservation() {
        return id_reservation;
    }
    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }
    public int getId_vol() {
        return id_vol;
    }
    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }
    public int getId_classe() {
        return id_classe;
    }
    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }
    public int getId_client() {
        return id_client;
    }
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public int getId_statut_reservation() {
        return id_statut_reservation;
    }
    public void setId_statut_reservation(int id_statut_reservation) {
        this.id_statut_reservation = id_statut_reservation;
    }
    public LocalDateTime getDate_reservation() {
        return date_reservation;
    }
    public void setDate_reservation(LocalDateTime date_reservation) {
        this.date_reservation = date_reservation;
    }

}
