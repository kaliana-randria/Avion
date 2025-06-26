package controllers;

import dao.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.*;

import java.io.IOException;
import java.time.LocalDateTime;

public class ReserverServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // Récupération des données du formulaire
            int idVol = Integer.parseInt(req.getParameter("idVol"));
            int idClasse = Integer.parseInt(req.getParameter("idClasse"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));

            // DAO
            VolDao volDao = new VolDao();
            ClasseDao classeDao = new ClasseDao();
            Classe_volDao classeVolDao = new Classe_volDao();
            Param_volDao paramVolDao = new Param_volDao();
            ReservationDao reservationDao = new ReservationDao();
            Enregistrement_reservationDao enregDao = new Enregistrement_reservationDao();

            Vol vol = volDao.findById(idVol);
            Classe classe = classeDao.findById(idClasse);
            Classe_vol classeVol = classeVolDao.findByVolAndClasse(idVol, idClasse);
            Param_vol param = paramVolDao.findEnCoursByClasseVol(classeVol.getId_classe_vol());

            Reservation reservation = new Reservation();
            reservation.setDate_reservation(LocalDateTime.now());
            reservation.setId_param_vol(param.getId_param_vol());
            reservation.setQuantite(quantite);
            reservation.setEst_payer(false); 

            reservationDao.setReserve(reservation);
            reservationDao.save();  


            String numReference = enregDao.generateNextReference();

            Enregistrement_reservation enregistrement = new Enregistrement_reservation();
            enregistrement.setId_reservation(reservation.getId_reservation());
            enregistrement.setNum_reference(numReference);
            enregistrement.setEst_annule(false);

            enregDao.setEnregReserve(enregistrement);
            enregDao.save();

            req.setAttribute("vol", vol);
            req.setAttribute("classe", classe);
            req.setAttribute("quantite", quantite);
            req.setAttribute("reference", numReference);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/recap.jsp");
            dispatcher.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors de la réservation : " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
        }
    }
}
