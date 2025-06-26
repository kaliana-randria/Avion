package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import connection.Maconnexion;
import models.Reservation;

public class ReservationDao {
    private Reservation reserve;

    public Reservation getReserve() {
        return reserve;
    }

    public void setReserve(Reservation reserve) {
        this.reserve = reserve;
    }

    public void save(Connection connection) throws Exception {
        String sql = "INSERT INTO reservation (date_reservation, id_param_vol, quantite, est_payer) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(reserve.getDate_reservation()));
            pstmt.setInt(2, reserve.getId_param_vol());
            pstmt.setInt(3, reserve.getQuantite());
            pstmt.setBoolean(4, reserve.isEst_payer());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    reserve.setId_reservation(rs.getInt(1));
                }
            }

            connection.setAutoCommit(false);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    public void save() throws Exception {
        try (Connection connection = Maconnexion.getConnexion()) {
            save(connection);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updatePaiement(int idReservation) throws Exception {
        Connection con = Maconnexion.getConnexion();
        String sql = "UPDATE reservation SET est_payer = TRUE WHERE id_reservation = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idReservation);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public Reservation findById(int id) throws Exception {
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT * FROM reservation WHERE id_reservation = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Reservation r = null;
        if (rs.next()) {
            r = new Reservation();
            r.setId_reservation(rs.getInt("id_reservation"));
            r.setId_param_vol(rs.getInt("id_param_vol"));
            r.setQuantite(rs.getInt("quantite"));
            r.setDate_reservation(rs.getTimestamp("date_reservation").toLocalDateTime());
            r.setEst_payer(rs.getBoolean("est_payer"));
        }
        rs.close();
        ps.close();
        con.close();
        return r;
    }

}
