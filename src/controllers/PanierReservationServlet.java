package controllers;

import dao.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PanierReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        try {
            Enregistrement_reservationDao enregDao = new Enregistrement_reservationDao();
            ReservationDao reservationDao = new ReservationDao();
            Param_volDao paramDao = new Param_volDao();
            Classe_volDao classeVolDao = new Classe_volDao();
            VolDao volDao = new VolDao();
            AvionDao avionDao = new AvionDao();
            CompagnieDao compagnieDao = new CompagnieDao();

            List<Enregistrement_reservation> enregistrements = enregDao.findAll();
            List<PanierDetail> panier = new ArrayList<>();

            for (Enregistrement_reservation enr : enregistrements) {
                if (enr.isEst_annule()) {
                    continue;
                }
                Reservation resv = reservationDao.findById(enr.getId_reservation());
                Param_vol param = paramDao.findById(resv.getId_param_vol());
                Classe_vol classeVol = classeVolDao.findById(param.getId_classe_vol());
                Vol vol = volDao.findById(classeVol.getId_vol());
                Avion avion = avionDao.findById(vol.getId_avion());
                Compagnie compagnie = compagnieDao.findById(avion.getId_compagnie());

                // for (int i = 0; i < resv.getQuantite(); i++) {
                    PanierDetail detail = new PanierDetail();
                    detail.setIdReservation(resv.getId_reservation());
                    detail.setReference(enr.getNum_reference());
                    detail.setVol(vol);
                    detail.setAvion(avion);
                    detail.setCompagnie(compagnie);
                    detail.setDateDepart(vol.getDate_depart());
                    detail.setDateArrivee(vol.getDate_arrivee());
                    detail.setEstPaye(resv.isEst_payer());
                    detail.setIdEnregistrement(enr.getId_enregistrement());
                    detail.setAnnule(enr.isEst_annule());

                    panier.add(detail);
                // }
            }

            req.setAttribute("panier", panier);
            req.getRequestDispatcher("/WEB-INF/views/panierReservation.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors de l affichage du panier : " + e.getMessage());
            out.println("error" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
        }
    }
}
