package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.AvionDAO;
import dao.Statut_volDAO;
import dao.VolDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Avions;
import models.Statuts_vol;
import models.Vols;

public class DetailVolServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String action = req.getParameter("action");
        // int idVol = req.getParameter("idVol") != null ? Integer.parseInt(req.getParameter("idVol")) : -1;
        int idVol = -1;
        String idVolStr = req.getParameter("idVol");
        if (idVolStr != null) {
            idVol = Integer.parseInt(idVolStr);
        }
        VolDAO dao = new VolDAO();
        Statut_volDAO statDao = new Statut_volDAO();
        AvionDAO avionDao = new AvionDAO();
        Vols volModel = new Vols(idVol);
        dao.setVols(volModel);

        try {
            if ("detail".equals(action)) {
                Vols vol = dao.findById(idVol);
                req.setAttribute("volDetail", vol);
                Statuts_vol statut = statDao.findById(vol.getId_statut_vol());
                Avions avion = avionDao.findById(vol.getId_avion());
                req.setAttribute("statutVol", statut);
                req.setAttribute("avion", avion);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/detailVol.jsp");
                dispatcher.forward(req, res);
            }
            else {
                List<Vols> vols = dao.findAll();
                req.setAttribute("listes", vols);
                req.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(req, res);
            }
        } catch (Exception e) {
            out.println(e.getMessage());
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
    }
}
