package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import dao.Param_volDao;
import dao.ReservationDao;
import dao.VolDao;
import models.*;

public class ChiffreAffaireVolServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try {
            String idVolStr = request.getParameter("idVol");

            if (idVolStr == null || idVolStr.isEmpty()) {
                request.setAttribute("message", "Veuillez choisir un vol.");
            } else {
                int idVol = Integer.parseInt(idVolStr);
                ReservationDao reservationDao = new ReservationDao();
                Param_volDao param_volDao = new Param_volDao();

                double totalBrut = param_volDao.getTotalBrutParVol(idVol);
                double chiffreAffaire = reservationDao.getChiffreAffaireParVol(idVol);
                double resteNonPaye = totalBrut - chiffreAffaire;

                request.setAttribute("message", "Total Brut du vol ID " + idVol + " : " + totalBrut + " euro " + "| Chiffre d affaires " + " : " + chiffreAffaire + " euro " + "| Reste " + " : " + resteNonPaye + " euro");
                // request.setAttribute("message", "Chiffre d affaires du vol ID " + idVol + " : " + chiffreAffaire + " euro");
                // request.setAttribute("message", "reste du vol ID " + idVol + " : " + resteNonPaye + " euro");

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Erreur lors du calcul du chiffre d affaires : " + e.getMessage());
        }

        try {
            VolDao volDao = new VolDao();
            List<Vol> vols = volDao.findAll();
            request.setAttribute("listes", vols);
        } catch (Exception e) {
            request.setAttribute("message", "Erreur de chargement des vols : " + e.getMessage());
        }

        request.getRequestDispatcher("WEB-INF/views/chiffreVol.jsp").forward(request, response);
    }
}
