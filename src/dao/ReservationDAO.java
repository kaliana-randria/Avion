package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import models.Reservations;
import models.Vols;

public class ReservationDAO {
    
    public void save(Connection connection, Reservations reservations, Vols vol) throws Exception {
        String sql = "INSERT INTO reservations (id_vol, id_classe, id_client, quantite, id_statut_reservation, date_reservation) " +
                    "VALUES (?, ?, ?, ?, ?, NOW())";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        //     pstmt.setString(1, vol.getNumero_vol());
        //     pstmt.setString(2, vol.getCompagnie());
        //     pstmt.setString(3, vol.getVille_depart());
        //     pstmt.setString(4, vol.getVille_arrivee());

        //     pstmt.setTimestamp(5, Timestamp.valueOf(vol.getDate_depart()));
        //     pstmt.setTimestamp(6, Timestamp.valueOf(vol.getDate_arrivee()));

        //     Duration duration = Duration.between(vol.getDate_depart(), vol.getDate_arrivee());
        //     int dureeEnMinutes = (int) duration.toMinutes();

        //     pstmt.setInt(7, dureeEnMinutes);
        //     pstmt.setInt(8, vol.getId_statut_vol());
        //     pstmt.setInt(9, vol.getId_avion());

        //     pstmt.executeUpdate();
        //     connection.setAutoCommit(false);
        //     connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw e;
        }
    }


    // public int getId_vol() {
    //     return id_vol;
    // }
    // public void setId_vol(int id_vol) {
    //     this.id_vol = id_vol;
    // }
    // public int getId_classe() {
    //     return id_classe;
    // }
    // public void setId_classe(int id_classe) {
    //     this.id_classe = id_classe;
    // }
    // public int getId_client() {
    //     return id_client;
    // }
    // public void setId_client(int id_client) {
    //     this.id_client = id_client;
    // }
    // public int getQuantite() {
    //     return quantite;
    // }
    // public void setQuantite(int quantite) {
    //     this.quantite = quantite;
    // }
    // public int getId_statut_reservation() {
    //     return id_statut_reservation;
    // }
    // public void setId_statut_reservation(int id_statut_reservation) {
    //     this.id_statut_reservation = id_statut_reservation;
    // }
    // public LocalDateTime getDate_reservation() {
    //     return date_reservation;
    // }
    // public void setDate_reservation(LocalDateTime date_reservation) {
    //     this.date_reservation = date_reservation;
    // }

}
