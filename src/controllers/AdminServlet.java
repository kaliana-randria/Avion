package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.AdminDao;
import dao.AvionDao;
import dao.CompagnieDao;
import dao.Statut_volDao;
import dao.VolDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Admin;
import models.Avion;
import models.Compagnie;
import models.Statut_vol;
import models.Vol;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/loginAdmin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String mdp = req.getParameter("mdp");

        try {
            AdminDao adminDao = new AdminDao();
            boolean isValid = adminDao.login(new Admin(email, mdp));

            if (isValid) {
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

                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp");
                dispatcher.forward(req, res);
            } else {
                req.setAttribute("erreur", "Identifiants incorrects !");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginAdmin.jsp");
                dispatcher.forward(req, res);
            }

        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur serveur : " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginAdmin.jsp");
            dispatcher.forward(req, res);
        }
    }

}
