package controllers;

import dao.*;
import models.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class AnnulerReservation2Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idEnregistrement = Integer.parseInt(req.getParameter("idEnregistrement"));

            Enregistrement_reservationDao enregDao = new Enregistrement_reservationDao();
            ReservationDao reservationDao = new ReservationDao();
            Param_volDao paramVolDao = new Param_volDao();
            Classe_volDao classeVolDao = new Classe_volDao();

            Enregistrement_reservation enreg = enregDao.findById(idEnregistrement);

            if (enreg == null) {
                req.setAttribute("error", "Reservation introuvable.");
            } else if (enreg.isEst_annule()) {
                req.setAttribute("message", "Reservation deja annulee.");
            } else {
                enreg.setEst_annule(true);
                enregDao.update(enreg);

                Reservation resv = reservationDao.findById(enreg.getId_reservation());

                if (resv == null) {
                    req.setAttribute("error", "Reservation liee introuvable.");
                } else {
                    Param_vol paramAnnule = paramVolDao.findById(resv.getId_param_vol());

                    if (paramAnnule == null) {
                        req.setAttribute("error", "Tarif lie introuvable.");
                    } else {
                        if (paramAnnule.getDate_limite_paiement().isAfter(LocalDateTime.now())) {
                            paramAnnule.setQuantite(paramAnnule.getQuantite() + 1);
                            paramVolDao.updateQuantite(paramAnnule);
                        } else {
                            Classe_vol classeVol = classeVolDao.findById(paramAnnule.getId_classe_vol());

                            if (classeVol != null) {
                                Param_vol paramEnCours = paramVolDao.findEnCoursByClasseVol(classeVol.getId_classe_vol());

                                if (paramEnCours != null) {
                                    paramEnCours.setQuantite(paramEnCours.getQuantite() + 1);
                                    paramVolDao.updateQuantite(paramEnCours);
                                }
                            }
                        }

                        req.setAttribute("message", "Reservation annulee avec succes.");
                    }
                }
            }

            resp.sendRedirect(req.getContextPath() + "/panier");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors de l annulation : " + e.getMessage());
            req.getRequestDispatcher("/panier").forward(req, resp);
        }
    }
}
