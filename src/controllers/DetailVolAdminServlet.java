package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

public class DetailVolAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            int idVol = -1;
            String idVolStr = req.getParameter("idVol");
            if (idVolStr != null && !idVolStr.isEmpty()) {
                idVol = Integer.parseInt(idVolStr);
            }

            VolDao volDao = new VolDao();
            Statut_volDao statutDao = new Statut_volDao();
            AvionDao avionDao = new AvionDao();
            CompagnieDao compagnieDao = new CompagnieDao();
            Param_volDao paramVolDao = new Param_volDao();
            Classe_volDao classeVolDao = new Classe_volDao();
            ClasseDao classeDao = new ClasseDao();
            Enregistrement_reservationDao enregDao = new Enregistrement_reservationDao();

            if ("detail".equals(action)) {
                Vol vol = volDao.findById(idVol);
                Avion avion = avionDao.findById(vol.getId_avion());
                Compagnie compagnie = compagnieDao.findById(avion.getId_compagnie());
                Statut_vol statutVol = statutDao.findById(vol.getId_statut_vol());

                req.setAttribute("volDetail", vol);
                req.setAttribute("avion", avion);
                req.setAttribute("compagnie", compagnie);
                req.setAttribute("statutVol", statutVol);

                List<Classe_vol> classeVols = classeVolDao.findByVolId(idVol);
                List<ClasseTarifDispo> tarifsDispo = new ArrayList<>();

                for (Classe_vol cv : classeVols) {
                    Classe classe = classeDao.findById(cv.getId_classe());
                    
                    Param_vol param = paramVolDao.findEnCoursByClasseVol(cv.getId_classe_vol());
                    if (param != null) {
                        int dejaReserve = enregDao.countConfirmedByParamVolId(param.getId_param_vol());
                        int restePlace = param.getQuantite() - dejaReserve;

                        if (restePlace > 0) {
                            ClasseTarifDispo ctd = new ClasseTarifDispo();
                            ctd.setClasse(classe);
                            ctd.setParamVol(param);
                            ctd.setRestePlace(restePlace);
                            tarifsDispo.add(ctd);
                        }
                    }
                }

                req.setAttribute("tarifsDispo", tarifsDispo);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/detailVol.jsp");
                dispatcher.forward(req, res);

            } else {
                List<Vol> vols = volDao.findAll();
                req.setAttribute("listes", vols);
                req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
            }

        } catch (Exception e) {
            req.setAttribute("error", "Erreur : " + e.getMessage());
            e.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
        }
    }
}
