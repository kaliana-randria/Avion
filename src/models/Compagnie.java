package models;

public class Compagnie {
    private int id_compagnie;
    private String nom_compagnie;

    public Compagnie() {
    }
    
    public int getId_compagnie() {
        return id_compagnie;
    }
    public void setId_compagnie(int id_compagnie) {
        this.id_compagnie = id_compagnie;
    }
    public String getNom_compagnie() {
        return nom_compagnie;
    }
    public void setNom_compagnie(String nom_compagnie) {
        this.nom_compagnie = nom_compagnie;
    }
}
