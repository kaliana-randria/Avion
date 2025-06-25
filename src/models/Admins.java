package models;

public class Admins {
    private int id_admin;
    private String nom_admin;
    private String email;
    private String mdp;
    public Admins(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }
    public Admins() {
    }
    public int getId_admin() {
        return id_admin;
    }
    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
    public String getNom_admin() {
        return nom_admin;
    }
    public void setNom_admin(String nom_admin) {
        this.nom_admin = nom_admin;
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
