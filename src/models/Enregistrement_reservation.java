package models;

public class Enregistrement_reservation {
    private int id_enregistrement;
    private int id_reservation;
    private String num_reference;
    private boolean est_annule;
    public Enregistrement_reservation() {
    }
    public int getId_enregistrement() {
        return id_enregistrement;
    }
    public void setId_enregistrement(int id_enregistrement) {
        this.id_enregistrement = id_enregistrement;
    }
    public int getId_reservation() {
        return id_reservation;
    }
    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }
    public String getNum_reference() {
        return num_reference;
    }
    public void setNum_reference(String num_reference) {
        this.num_reference = num_reference;
    }
    public boolean isEst_annule() {
        return est_annule;
    }
    public void setEst_annule(boolean est_annule) {
        this.est_annule = est_annule;
    }

}
