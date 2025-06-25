package models;

public class Clients {
    private int id_client;
    private String nom_client;
    private String email_client;
    private String mdp;
    
    public Clients() {
    }
    public int getId_client() {
        return id_client;
    }
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    public String getNom_client() {
        return nom_client;
    }
    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }
    public String getEmail_client() {
        return email_client;
    }
    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
