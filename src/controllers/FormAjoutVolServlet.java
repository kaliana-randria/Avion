package controllers;

import java.io.IOException;
import java.util.List;

import dao.AvionDao;
import dao.Statut_volDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Avion;
import models.Statut_vol;

public class FormAjoutVolServlet extends HttpServlet {  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Statut_volDao statut_volDao = new Statut_volDao();
            AvionDao avionDao = new AvionDao();
            List<Statut_vol> statutVols = statut_volDao.findAll();
            List<Avion> avionList = avionDao.findAll();
            request.setAttribute("satutsVol", statutVols);
            request.setAttribute("avions", avionList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/formVol.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
