package models;

public class Avion {
    private int id_avion;
    private int id_compagnie;
    private String nom_avion;
    private String modele;
    public Avion(int id_avion, int id_compagnie, String nom_avion, String modele) {
        this.id_avion = id_avion;
        this.id_compagnie = id_compagnie;
        this.nom_avion = nom_avion;
        this.modele = modele;
    }
    public Avion() {
    }
    public int getId_avion() {
        return id_avion;
    }
    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }
    public int getId_compagnie() {
        return id_compagnie;
    }
    public void setId_compagnie(int id_compagnie) {
        this.id_compagnie = id_compagnie;
    }
    public String getNom_avion() {
        return nom_avion;
    }
    public void setNom_avion(String nom_avion) {
        this.nom_avion = nom_avion;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
}
