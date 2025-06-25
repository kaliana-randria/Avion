package models;

import java.util.Date;

public class Tarifs {
    private int id_tarif;
    private int id_vol;
    private int id_classe;
    private int quantite;
    private double prix;
    private int pourcentage;
    private Date date_limite_paiement;
    public Tarifs(int id_vol) {
        this.id_vol = id_vol;
    }
    public Tarifs() {
    }
    public int getId_tarif() {
        return id_tarif;
    }
    public void setId_tarif(int id_tarif) {
        this.id_tarif = id_tarif;
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
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }
    public Date getDate_limite_paiement() {
        return date_limite_paiement;
    }
    public void setDate_limite_paiement(Date date_limite_paiement) {
        this.date_limite_paiement = date_limite_paiement;
    }

}
