package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.VolDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Vol;

public class AffaireVolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        try {
            VolDao volDao = new VolDao();
            List<Vol> vols = volDao.findAll();
            req.setAttribute("listes", vols);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/numVol.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Erreur : " + e.getMessage());
            out.println("error" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/numVol.jsp").forward(req, res);
        }
    }
}
