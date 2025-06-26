package controllers;

import dao.VolDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.Vol;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

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


            VolDao voldao = new VolDao();
            List<Vol> resultats = voldao.rechercheMulticritere(sqlDate, compagnie, depart, arrivee);

            req.setAttribute("listes", resultats);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/accueil.jsp");
            dispatcher.forward(req, res);

        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur lors de la recherche : " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
        }
    }
}
