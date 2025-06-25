package controllers;

import java.io.IOException;
import java.util.List;

import dao.VolDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Vols;

public class RechercheVolsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String numero_vol = req.getParameter("numero_vol");
            String compagnie = req.getParameter("compagnie");
            String ville_depart = req.getParameter("ville_depart");
            String ville_arrivee = req.getParameter("ville_arrivee");

            VolDAO volDAO = new VolDAO();
            List<Vols> resultats = volDAO.rechercheMulticritere(numero_vol, compagnie, ville_depart, ville_arrivee);
            req.setAttribute("listes", resultats);

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/accueil.jsp");
            dispatcher.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
