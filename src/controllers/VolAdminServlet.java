package controllers;

import dao.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class VolAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        String action = req.getParameter("action");
        int idVol = -1;

        if (req.getParameter("idVol") != null) {
            try {
                idVol = Integer.parseInt(req.getParameter("idVol"));
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID invalide");
                req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp").forward(req, res);
                return;
            }
        }

        VolDao volDao = new VolDao();
        Statut_volDao statutDao = new Statut_volDao();
        Param_volDao paramDao = new Param_volDao();
        AvionDao avionDao = new AvionDao();

        try {
            if ("delete".equals(action)) {
                volDao.setVol(new Vol(idVol));
                volDao.delete();
                res.sendRedirect("volAdmin");

            } else if ("update".equals(action)) {
                Vol volToUpdate = volDao.findById(idVol);
                req.setAttribute("volToUpdate", volToUpdate);
                req.setAttribute("statutVol", statutDao.findAll());
                req.setAttribute("avions", avionDao.findAll());
                req.setAttribute("listes", volDao.findAll());
                req.getRequestDispatcher("/WEB-INF/views/formVol.jsp").forward(req, res);

            } else if ("tarif".equals(action)) {
                req.setAttribute("idVol", idVol);
                req.setAttribute("TarifList", paramDao.findByVolId(idVol));
                req.setAttribute("volDetail", volDao.findById(idVol));
                req.getRequestDispatcher("/WEB-INF/views/listTarif.jsp").forward(req, res);

            } else {
                List<Vol> vols = volDao.findAll();
                req.setAttribute("listes", vols);
                req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp").forward(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            out.println("error" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp").forward(req, res);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        PrintWriter out = res.getWriter();

        try {
            String idVolStr = req.getParameter("id_vol");
            String avionStr = req.getParameter("id_avion");
            String villeDepart = req.getParameter("ville_depart");
            String villeArrivee = req.getParameter("ville_arrivee");
            String dateDepartStr = req.getParameter("date_depart");
            String dateArriveeStr = req.getParameter("date_arrivee");
            String statutVolStr = req.getParameter("id_statut_vol");

            LocalDateTime dateDepart = LocalDateTime.parse(dateDepartStr);
            LocalDateTime dateArrivee = LocalDateTime.parse(dateArriveeStr);
            int idAvion = Integer.parseInt(avionStr);
            int idStatutVol = Integer.parseInt(statutVolStr);

            Vol vol = new Vol(idAvion, villeDepart, villeArrivee,
                    dateDepart, dateArrivee, idStatutVol);

            VolDao dao = new VolDao();

            if (idVolStr != null && !idVolStr.trim().isEmpty()) {
                vol.setId_vol(Integer.parseInt(idVolStr));
                dao.update(vol);
            } else {
                dao.setVol(vol);
                dao.save();
            }

            res.sendRedirect("volAdmin");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur : " + e.getMessage());
            out.println("error" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/formVol.jsp").forward(req, res);
        }
    }
}
