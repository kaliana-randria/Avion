package models;

import java.time.LocalDateTime;

public class Vols {
    private int id_vol;
    private String numero_vol;
    private String compagnie;
    private String ville_depart;
    private String ville_arrivee;
    private LocalDateTime date_depart;
    private LocalDateTime date_arrivee;
    private int duree;
    private int id_statut_vol;
    private int id_avion;
    
    public Vols(String numero_vol, String compagnie, String ville_depart, String ville_arrivee,
            LocalDateTime date_depart, LocalDateTime date_arrivee, int duree, int id_statut_vol, int id_avion) {
        this.numero_vol = numero_vol;
        this.compagnie = compagnie;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.duree = duree;
        this.id_statut_vol = id_statut_vol;
        this.id_avion = id_avion;
    }
    public Vols(int id_vol, String numero_vol, String compagnie, String ville_depart, String ville_arrivee,
            LocalDateTime date_depart, LocalDateTime date_arrivee, int duree, int id_statut_vol, int id_avion) {
        this.id_vol = id_vol;
        this.numero_vol = numero_vol;
        this.compagnie = compagnie;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.duree = duree;
        this.id_statut_vol = id_statut_vol;
        this.id_avion = id_avion;
    }
    public Vols(int id_vol) {
        this.id_vol = id_vol;
    }
    public Vols(String numero_vol, String compagnie, String ville_depart, String ville_arrivee,
            LocalDateTime date_depart, LocalDateTime date_arrivee, int id_statut_vol, int id_avion) {
        this.numero_vol = numero_vol;
        this.compagnie = compagnie;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.id_statut_vol = id_statut_vol;
        this.id_avion = id_avion;
    }
    public Vols() {
    }
    public int getId_vol() {
        return id_vol;
    }
    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }
    public String getNumero_vol() {
        return numero_vol;
    }
    public void setNumero_vol(String numero_vol) {
        this.numero_vol = numero_vol;
    }
    public String getCompagnie() {
        return compagnie;
    }
    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }
    public String getVille_depart() {
        return ville_depart;
    }
    public void setVille_depart(String ville_depart) {
        this.ville_depart = ville_depart;
    }
    public String getVille_arrivee() {
        return ville_arrivee;
    }
    public void setVille_arrivee(String ville_arrivee) {
        this.ville_arrivee = ville_arrivee;
    }
    public LocalDateTime getDate_depart() {
        return date_depart;
    }
    public void setDate_depart(LocalDateTime date_depart) {
        this.date_depart = date_depart;
    }
    public LocalDateTime getDate_arrivee() {
        return date_arrivee;
    }
    public void setDate_arrivee(LocalDateTime date_arrivee) {
        this.date_arrivee = date_arrivee;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public int getId_statut_vol() {
        return id_statut_vol;
    }
    public void setId_statut_vol(int id_statut_vol) {
        this.id_statut_vol = id_statut_vol;
    }
    public int getId_avion() {
        return id_avion;
    }
    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

}
