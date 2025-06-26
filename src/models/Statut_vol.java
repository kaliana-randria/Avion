package models;

public class Statut_vol {
    private int id_statut_vol;
    private String statut;
    public Statut_vol(int id_statut_vol, String statut) {
        this.id_statut_vol = id_statut_vol;
        this.statut = statut;
    }
    public Statut_vol() {
    }
    public int getId_statut_vol() {
        return id_statut_vol;
    }
    public void setId_statut_vol(int id_statut_vol) {
        this.id_statut_vol = id_statut_vol;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
}
