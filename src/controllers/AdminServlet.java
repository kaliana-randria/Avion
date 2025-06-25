package controllers;

import java.io.IOException;
import java.util.List;

import dao.AdminDAO;
import dao.VolDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Admins;
import models.Vols;

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
            AdminDAO adminDAO = new AdminDAO();
            boolean isValid = adminDAO.login(new Admins(email, mdp));

            if (isValid) {
                VolDAO volDao = new VolDAO();
                List<Vols> listVols = volDao.findAll();
                req.setAttribute("listes", listVols);
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
