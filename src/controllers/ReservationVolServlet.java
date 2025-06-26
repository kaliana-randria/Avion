package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

public class ReservationVolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        VolDao volDao = new VolDao();
        ClasseDao classeDao = new ClasseDao();

        try {
            String action = req.getParameter("action");
            String idVolStr = req.getParameter("idVol");
            int idVol = -1;
            if (idVolStr != null && !idVolStr.isEmpty()) {
                idVol = Integer.parseInt(idVolStr);
            }

            if (action != null && idVol != -1) {
                Vol vol = volDao.findById(idVol);
                Classe classe = classeDao.findByName(action); 

                if (classe != null && vol != null) {
                    req.setAttribute("volDetail", vol);
                    req.setAttribute("classe", action); 
                    req.setAttribute("classeDonnees", classe);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation.jsp");
                    dispatcher.forward(req, res);
                    return;
                } else {
                    req.setAttribute("error", "Classe ou vol introuvable.");
                }
            }

            List<Vol> vols = volDao.findAll();
            req.setAttribute("listes", vols);
            req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();

            try {
                List<Vol> vols = volDao.findAll(); 
                req.setAttribute("listes", vols);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            req.setAttribute("error", "Erreur lors de la réservation : " + e.getMessage());
            out.println("error" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
        }
    }
}
