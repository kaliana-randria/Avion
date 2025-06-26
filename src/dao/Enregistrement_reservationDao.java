package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Maconnexion;

public class Enregistrement_reservationDao {

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

}
