package controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import dao.ClasseDao;
import dao.Classe_volDao;
import dao.Enregistrement_reservationDao;
import dao.Param_volDao;
import dao.ReservationDao;
import dao.VolDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Classe;
import models.Classe_vol;
import models.Enregistrement_reservation;
import models.Param_vol;
import models.Reservation;
import models.Vol;

public class ReserverAdmin2Servlet extends HttpServlet {
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int idVol = Integer.parseInt(req.getParameter("idVol"));
            int idClasse = Integer.parseInt(req.getParameter("idClasse"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));
            boolean estPaye = req.getParameter("paiement") != null;

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

            int reste = quantite;

            if (reste > 0 && param != null && param.getQuantite() > 0) {
                int aPrendre = Math.min(reste, param.getQuantite());
                Reservation reservation = new Reservation();
                reservation.setDate_reservation(LocalDateTime.now());
                reservation.setId_param_vol(param.getId_param_vol());
                reservation.setQuantite(aPrendre);
                reservation.setEst_payer(estPaye);

                reservationDao.setReserve(reservation);
                reservationDao.save();

                String numReference = enregDao.generateNextReference();
                for (int i = 0; i < reservation.getQuantite(); i++) {
                    Enregistrement_reservation enregistrement = new Enregistrement_reservation();
                    enregistrement.setId_reservation(reservation.getId_reservation());
                    enregistrement.setNum_reference(numReference);
                    enregistrement.setEst_annule(false);
                    enregDao.setEnregReserve(enregistrement);
                    enregDao.save();
                }

                reste -= aPrendre;
            }

            if (reste > 0) {
                Param_vol param2 = paramVolDao.findApresEnCoursByClasseVol(classeVol.getId_classe_vol());
                if (param2 == null) {
                    throw new Exception("Pas assez de places disponibles.");
                }

                Reservation reservation2 = new Reservation();
                reservation2.setDate_reservation(LocalDateTime.now());
                reservation2.setId_param_vol(param2.getId_param_vol());
                reservation2.setQuantite(reste);
                reservation2.setEst_payer(estPaye);

                reservationDao.setReserve(reservation2);
                reservationDao.save();

                String numReference2 = enregDao.generateNextReference(); 

                for (int i = 0; i < reservation2.getQuantite(); i++) {
                    Enregistrement_reservation enregistrement = new Enregistrement_reservation();
                    enregistrement.setId_reservation(reservation2.getId_reservation());
                    enregistrement.setNum_reference(numReference2);
                    enregistrement.setEst_annule(false);
                    enregDao.setEnregReserve(enregistrement);
                    enregDao.save();
                }

            }

            req.setAttribute("vol", vol);
            req.setAttribute("classe", classe);
            req.setAttribute("quantite", quantite);
            req.setAttribute("estPaye", estPaye);
            req.setAttribute("reference", "Réservation effectuée");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/recap.jsp");
            dispatcher.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors de la reservation : " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp").forward(req, res);
        }
    }
}
