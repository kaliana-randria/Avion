package models;

import java.time.LocalDateTime;

public class Param_vol {
    private int id_param_vol;
    private int id_classe_vol;
    private double prix;
    private int quantite;
    private LocalDateTime date_limite_paiement;
    private boolean en_cours;
    public Param_vol(int id_classe_vol) {
        this.id_classe_vol = id_classe_vol;
    }
    public Param_vol() {
    }
    public int getId_param_vol() {
        return id_param_vol;
    }
    public void setId_param_vol(int id_param_vol) {
        this.id_param_vol = id_param_vol;
    }
    public int getId_classe_vol() {
        return id_classe_vol;
    }
    public void setId_classe_vol(int id_classe_vol) {
        this.id_classe_vol = id_classe_vol;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public LocalDateTime getDate_limite_paiement() {
        return date_limite_paiement;
    }
    public void setDate_limite_paiement(LocalDateTime date_limite_paiement) {
        this.date_limite_paiement = date_limite_paiement;
    }
    public boolean isEn_cours() {
        return en_cours;
    }
    public void setEn_cours(boolean en_cours) {
        this.en_cours = en_cours;
    }
}
