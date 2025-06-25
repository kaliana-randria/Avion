package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.VolDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Vols;

public class AjoutVolServlet extends HttpServlet {
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        String numero_volString = req.getParameter("numero_vol");
        String compagnieString = req.getParameter("compagnie");
        String ville_departString = req.getParameter("ville_depart");
        String ville_arriveeString = req.getParameter("ville_arrivee");
        String date_departString = req.getParameter("date_depart");
        String date_arriveeString = req.getParameter("date_arrivee");
        String id_statut_volString = req.getParameter("id_statut_vol");
        String id_avionString = req.getParameter("id_avion");

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime date_depart = LocalDateTime.parse(date_departString, formatter);
            LocalDateTime date_arrivee = LocalDateTime.parse(date_arriveeString, formatter);

            int id_statut_vol = Integer.parseInt(id_statut_volString);
            int id_avion = Integer.parseInt(id_avionString);

            Vols vols = new Vols(numero_volString, compagnieString, ville_departString, ville_arriveeString,
            date_depart, date_arrivee, id_statut_vol, id_avion);
            VolDAO VolDAO = new VolDAO();
            VolDAO.setVols(vols);  

            VolDAO.save();

            // asiana condition hoe erreur ou non
            

        } catch (NumberFormatException e) {
            e.printStackTrace();
            out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e.getMessage());
        }
    }

        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idString = req.getParameter("idVol");

        try {

            if ("delete".equals(action) && idString != null) {
                int id = Integer.parseInt(idString);
                Vols vols = new Vols(id);
                VolDAO volDAO = new VolDAO();
                volDAO.setVols(vols);

                volDAO.delete();
                res.sendRedirect(req.getContextPath() + "/volAdmin");
                return;
            }
            
            // if ("modify".equals(action) && idString != null) {
            //     int id = Integer.parseInt(idString);
            //     Film film = new Film(id);
            //     FilmDao filmDao = new FilmDao();
            //     filmDao.setFilm(film);

                // filmDao.update(film);
            // }

            VolDAO volDAO = new VolDAO();
            List<Vols> listVols = volDAO.findAll();
            req.setAttribute("listes", listVols);
            RequestDispatcher dispat = req.getRequestDispatcher("WEB-INF/views/homeAdmin.jsp");
            dispat.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
