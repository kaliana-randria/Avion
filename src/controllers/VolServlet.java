package controllers;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import dao.AvionDao;
import dao.CompagnieDao;
import dao.Statut_volDao;
import dao.VolDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

public class VolServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            VolDao volDao = new VolDao();
            List<Vol> listVols = volDao.findAll();
            
            List<Avion> listAvions = new ArrayList<>();
            List<Compagnie> listCompagnies = new ArrayList<>();
            List<Statut_vol> listStatuts = new ArrayList<>();
            List<String> listHeures = new ArrayList<>();
            
            AvionDao avionDao = new AvionDao();
            CompagnieDao compagnieDao = new CompagnieDao();
            Statut_volDao statutDao = new Statut_volDao();
            
            for (Vol vol : listVols) {
                Avion avion = avionDao.findById(vol.getId_avion());
                Compagnie compagnie = compagnieDao.findById(avion.getId_compagnie());
                volDao.updateStatutVol(vol);
                Statut_vol statut = statutDao.findById(vol.getId_statut_vol());
                String heure = VolDao.convertionMinEnHeure(vol.getDuree());
                
                listAvions.add(avion);
                listCompagnies.add(compagnie);
                listStatuts.add(statut);
                listHeures.add(heure);
            }
            
            req.setAttribute("listes", listVols);
            req.setAttribute("listAvions", listAvions);
            req.setAttribute("listCompagnies", listCompagnies);
            req.setAttribute("listStatuts", listStatuts);
            req.setAttribute("listHeures", listHeures);
            
            RequestDispatcher dispat = req.getRequestDispatcher("WEB-INF/views/accueil.jsp");
            dispat.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}