package controllers;

import dao.ReservationDao;
import dao.Enregistrement_reservationDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class PaiementReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String reference = req.getParameter("reference");

        try {
            Enregistrement_reservationDao enregDao = new Enregistrement_reservationDao();
            ReservationDao reservationDao = new ReservationDao();

            int idReservation = enregDao.findReservationIdByReference(reference);

            reservationDao.updatePaiement(idReservation);

            req.setAttribute("message", "Paiement confirmé pour la réservation : " + reference);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur : " + e.getMessage());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/paiement.jsp");
        dispatcher.forward(req, res);
    }
}
