package models;

public class Classe {
    private int id_classe;
    private String nom_classe;
    public Classe(int id_classe, String nom_classe) {
        this.id_classe = id_classe;
        this.nom_classe = nom_classe;
    }
    public Classe() {
    }
    public int getId_classe() {
        return id_classe;
    }
    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }
    public String getNom_classe() {
        return nom_classe;
    }
    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }
}
