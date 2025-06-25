package models;

public class Passagers {
    private int id_passager;
    private String nom_passager;
    private char genre_passager;
    private int id_categorie_age;
    public Passagers() {
    }
    public int getId_passager() {
        return id_passager;
    }
    public void setId_passager(int id_passager) {
        this.id_passager = id_passager;
    }
    public String getNom_passager() {
        return nom_passager;
    }
    public void setNom_passager(String nom_passager) {
        this.nom_passager = nom_passager;
    }
    public char getGenre_passager() {
        return genre_passager;
    }
    public void setGenre_passager(char genre_passager) {
        this.genre_passager = genre_passager;
    }
    public int getId_categorie_age() {
        return id_categorie_age;
    }
    public void setId_categorie_age(int id_categorie_age) {
        this.id_categorie_age = id_categorie_age;
    }

}
