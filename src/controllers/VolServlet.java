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

public class VolServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            VolDAO volDao = new VolDAO();
            List<Vols> listVols = volDao.findAll();
            req.setAttribute("listes", listVols);
            RequestDispatcher dispat = req.getRequestDispatcher("WEB-INF/views/accueil.jsp");
            dispat.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
