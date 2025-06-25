package models;

public class Classes {
    private int id_classe;
    private String nom;
    
    public Classes(int id_classe, String nom) {
        this.id_classe = id_classe;
        this.nom = nom;
    }
    public Classes() {
    }
    public int getId_classe() {
        return id_classe;
    }
    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
