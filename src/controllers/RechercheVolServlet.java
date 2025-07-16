package controllers;

import dao.VolDao;
import dao.AvionDao;
import dao.CompagnieDao;
import dao.Statut_volDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.Vol;
import models.Avion;
import models.Compagnie;
import models.Statut_vol;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class RechercheVolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String dateStr = req.getParameter("date_depart");
            String compagnie = req.getParameter("compagnie");
            String depart = req.getParameter("ville_depart");
            String arrivee = req.getParameter("ville_arrivee");

            java.sql.Date sqlDate = null;
            if (dateStr != null && !dateStr.isEmpty()) {
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                sqlDate = new java.sql.Date(utilDate.getTime());
            }

            VolDao volDao = new VolDao();
            List<Vol> resultats = volDao.rechercheMulticritere(sqlDate, compagnie, depart, arrivee);
            
            List<Avion> listAvions = new ArrayList<>();
            List<Compagnie> listCompagnies = new ArrayList<>();
            List<Statut_vol> listStatuts = new ArrayList<>();
            List<String> listHeures = new ArrayList<>();
            
            AvionDao avionDao = new AvionDao();
            CompagnieDao compagnieDao = new CompagnieDao();
            Statut_volDao statutDao = new Statut_volDao();
            
            for (Vol vol : resultats) {
                Avion avion = avionDao.findById(vol.getId_avion());
                Compagnie comp = compagnieDao.findById(avion.getId_compagnie());
                volDao.updateStatutVol(vol);
                Statut_vol statut = statutDao.findById(vol.getId_statut_vol());
                String heure = VolDao.convertionMinEnHeure(vol.getDuree());
                
                listAvions.add(avion);
                listCompagnies.add(comp);
                listStatuts.add(statut);
                listHeures.add(heure);
            }
            
            req.setAttribute("listes", resultats);
            req.setAttribute("listAvions", listAvions);
            req.setAttribute("listCompagnies", listCompagnies);
            req.setAttribute("listStatuts", listStatuts);
            req.setAttribute("listHeures", listHeures);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/accueil.jsp");
            dispatcher.forward(req, res);

        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur lors de la recherche : " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
        }
    }

}