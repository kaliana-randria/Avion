package models;

public class Admin {
    private int id_admin;
    private String nom;
    private String email;
    private String mdp;
    public Admin(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }
    public Admin() {
    }
    public int getId_admin() {
        return id_admin;
    }
    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
