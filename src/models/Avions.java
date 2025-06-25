package models;

public class Avions {

    private int id_avion;
    private String nom;
    private String code;
    private String modele;
    private int business_seats;
    private int economy_seats;
    
    public Avions(int id_avion, String nom, String code, String modele, int business_seats, int economy_seats) {
        this.id_avion = id_avion;
        this.nom = nom;
        this.code = code;
        this.modele = modele;
        this.business_seats = business_seats;
        this.economy_seats = economy_seats;
    }
    public Avions() {
    }
    public int getId_avion() {
        return id_avion;
    }
    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public int getBusiness_seats() {
        return business_seats;
    }
    public void setBusiness_seats(int business_seats) {
        this.business_seats = business_seats;
    }
    public int getEconomy_seats() {
        return economy_seats;
    }
    public void setEconomy_seats(int economy_seats) {
        this.economy_seats = economy_seats;
    }

}
