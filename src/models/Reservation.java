package models;

import java.time.LocalDateTime;

public class Reservation {
    private int id_reservation;
    private LocalDateTime date_reservation;
    private int id_param_vol;
    private int quantite;
    private boolean est_payer;
    public Reservation() {
    }
    public int getId_reservation() {
        return id_reservation;
    }
    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }
    public LocalDateTime getDate_reservation() {
        return date_reservation;
    }
    public void setDate_reservation(LocalDateTime date_reservation) {
        this.date_reservation = date_reservation;
    }
    public int getId_param_vol() {
        return id_param_vol;
    }
    public void setId_param_vol(int id_param_vol) {
        this.id_param_vol = id_param_vol;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public boolean isEst_payer() {
        return est_payer;
    }
    public void setEst_payer(boolean est_payer) {
        this.est_payer = est_payer;
    }

}
