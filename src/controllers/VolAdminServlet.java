package controllers;

import dao.Statut_volDAO;
import dao.TarifDAO;
import dao.VolDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.Statuts_vol;
import models.Tarifs;
import models.Vols;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class VolAdminServlet extends HttpServlet {
    @Override
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
        Vols volModel = new Vols(idVol);
        dao.setVols(volModel);

        TarifDAO tarifDao = new TarifDAO();
        Tarifs tarifModel = new Tarifs(idVol);
        tarifDao.setTarifs(tarifModel);

        Statut_volDAO statDao = new Statut_volDAO();

        try {
            if ("delete".equals(action)) {
                dao.delete();
                res.sendRedirect("volAdmin");
            } 
            else if ("update".equals(action)) {
                Vols vol = dao.findById(idVol);
                req.setAttribute("volToUpdate", vol);
                List<Statuts_vol> statutVols = statDao.findAll();
                req.setAttribute("statutsVol", statutVols);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/formVol.jsp");
                dispatcher.forward(req, res);
            } 
            else if ("tarif".equals(action)) {
                req.setAttribute("idVol", idVol);
                List<Tarifs> tarif = tarifDao.findByVolId();
                req.setAttribute("TarifList", tarif);
                Vols vol = dao.findById(idVol);
                req.setAttribute("volDetail", vol);
                RequestDispatcher tarifDispatcher = req.getRequestDispatcher("/WEB-INF/views/listTarif.jsp");
                tarifDispatcher.forward(req, res);
            } 
            else {
                List<Vols> vols = dao.findAll();
                req.setAttribute("listes", vols);
                req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp").forward(req, res);
            }
        } catch (Exception e) {
            out.println(e.getMessage());
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        try {

            String idVolStr = req.getParameter("id_vol");
            String numeroVol = req.getParameter("numero_vol");
            String compagnie = req.getParameter("compagnie");
            String villeDepart = req.getParameter("ville_depart");
            String villeArrivee = req.getParameter("ville_arrivee");
            String dateDepartStr = req.getParameter("date_depart");
            String dateArriveeStr = req.getParameter("date_arrivee");
            String statutVolStr = req.getParameter("id_statut_vol");
            String avionStr = req.getParameter("id_avion");

            LocalDateTime dateDepart = LocalDateTime.parse(dateDepartStr);
            LocalDateTime dateArrivee = LocalDateTime.parse(dateArriveeStr);
            int idStatutVol = Integer.parseInt(statutVolStr);
            int idAvion = Integer.parseInt(avionStr);

            Vols vol = new Vols(
                numeroVol, compagnie, villeDepart, villeArrivee,
                dateDepart, dateArrivee, idStatutVol, idAvion
            );

            VolDAO dao = new VolDAO();

            // Si id_vol est présent => UPDATE
            if (idVolStr != null && !idVolStr.trim().isEmpty()) {
                int idVol = Integer.parseInt(idVolStr);
                vol.setId_vol(idVol);
                dao.update(vol);
            } else {
                dao.setVols(vol);
                dao.save();
            }

            res.sendRedirect("volAdmin");

        } catch (Exception e) {
            out.println(e.getMessage());
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
    }

}
