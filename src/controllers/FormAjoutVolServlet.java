package controllers;

import java.io.IOException;
import java.util.List;

import dao.AvionDAO;
import dao.Statut_volDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Avions;
import models.Statuts_vol;

public class FormAjoutVolServlet extends HttpServlet {  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Statut_volDAO statut_volDAO = new Statut_volDAO();
            AvionDAO avionDAO = new AvionDAO();
            List<Statuts_vol> statutVols = statut_volDAO.findAll();
            List<Avions> avionList = avionDAO.findAll();
            request.setAttribute("satutsVol", statutVols);
            request.setAttribute("avions", avionList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/formVol.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
