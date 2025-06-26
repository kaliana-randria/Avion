package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Enregistrement_reservation;

public class Enregistrement_reservationDao {
    private Enregistrement_reservation enregReserve;

    public Enregistrement_reservation getEnregReserve() {
        return enregReserve;
    }

    public void setEnregReserve(Enregistrement_reservation enregReserve) {
        this.enregReserve = enregReserve;
    }

    public int countConfirmedByParamVolId(int idParamVol) throws Exception {
        Connection con = Maconnexion.getConnexion();

        String sql = """
                    SELECT COALESCE(SUM(r.quantite), 0) AS total
                    FROM reservation r
                    JOIN enregistrement_reservation er ON r.id_reservation = er.id_reservation
                    WHERE r.id_param_vol = ? AND er.est_annule = false
                """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idParamVol);
        ResultSet rs = ps.executeQuery();

        int total = 0;
        if (rs.next()) {
            total = rs.getInt("total");
        }

        rs.close();
        ps.close();
        con.close();
        return total;
    }

    public String generateNextReference() throws Exception {
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT MAX(num_reference) AS last_ref FROM enregistrement_reservation";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int nextNum = 1;
        if (rs.next()) {
            String lastRef = rs.getString("last_ref");
            if (lastRef != null && lastRef.startsWith("REF")) {
                String numPart = lastRef.substring(3);
                nextNum = Integer.parseInt(numPart) + 1;
            }
        }

        rs.close();
        ps.close();
        con.close();

        return String.format("REF%04d", nextNum);
    }

    public void save(Connection connection) throws Exception {
        String sql = "INSERT INTO enregistrement_reservation (id_reservation, num_reference, est_annule) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, enregReserve.getId_reservation());
            pstmt.setString(2, enregReserve.getNum_reference());
            pstmt.setBoolean(3, enregReserve.isEst_annule());

            pstmt.executeUpdate();

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

    public int findReservationIdByReference(String reference) throws Exception {
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT id_reservation FROM enregistrement_reservation WHERE num_reference = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, reference);
        ResultSet rs = ps.executeQuery();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("id_reservation");
        }
        rs.close();
        ps.close();
        con.close();
        if (id == -1) {
            throw new Exception("Aucune réservation trouvée pour la référence : " + reference);
        }
        return id;
    }

    public List<Enregistrement_reservation> findAll() throws Exception {
        List<Enregistrement_reservation> liste = new ArrayList<>();
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT * FROM enregistrement_reservation";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Enregistrement_reservation e = new Enregistrement_reservation();
            e.setId_enregistrement(rs.getInt("id_enregistrement"));
            e.setId_reservation(rs.getInt("id_reservation"));
            e.setNum_reference(rs.getString("num_reference"));
            e.setEst_annule(rs.getBoolean("est_annule"));
            liste.add(e);
        }
        rs.close();
        ps.close();
        con.close();
        return liste;
    }

    public Enregistrement_reservation findById(int idEnregistrement) throws Exception {
        Enregistrement_reservation enreg = null;
        String sql = "SELECT * FROM enregistrement_reservation WHERE id_enregistrement = ?";

        try (Connection connection = Maconnexion.getConnexion();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, idEnregistrement);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    enreg = new Enregistrement_reservation();
                    enreg.setId_enregistrement(rs.getInt("id_enregistrement"));
                    enreg.setId_reservation(rs.getInt("id_reservation"));
                    enreg.setNum_reference(rs.getString("num_reference"));
                    enreg.setEst_annule(rs.getBoolean("est_annule"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return enreg;
    }

    public void update(Enregistrement_reservation enreg) throws Exception {
        String sql = "UPDATE enregistrement_reservation SET id_reservation = ?, num_reference = ?, est_annule = ? WHERE id_enregistrement = ?";

        try (Connection connection = Maconnexion.getConnexion();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, enreg.getId_reservation());
            pstmt.setString(2, enreg.getNum_reference());
            pstmt.setBoolean(3, enreg.isEst_annule());
            pstmt.setInt(4, enreg.getId_enregistrement());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new Exception(
                        "Mise à jour échouée, aucun enregistrement trouvé avec id : " + enreg.getId_enregistrement());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
