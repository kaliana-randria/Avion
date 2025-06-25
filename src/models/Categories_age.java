package models;

public class Categories_age {
    private int id_categorie;
    private String type_personne;
    private int age_min;
    private int age_max;
    public Categories_age() {
    }
    public int getId_categorie() {
        return id_categorie;
    }
    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
    public String getType_personne() {
        return type_personne;
    }
    public void setType_personne(String type_personne) {
        this.type_personne = type_personne;
    }
    public int getAge_min() {
        return age_min;
    }
    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }
    public int getAge_max() {
        return age_max;
    }
    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }
}
