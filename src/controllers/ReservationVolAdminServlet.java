package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.ClasseDao;
import dao.Classe_volDao;
import dao.Enregistrement_reservationDao;
import dao.Param_volDao;
import dao.VolDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Classe;
import models.ClasseTarifDispo;
import models.Classe_vol;
import models.Param_vol;
import models.Vol;

public class ReservationVolAdminServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        VolDao volDao = new VolDao();
        ClasseDao classeDao = new ClasseDao();
        Param_volDao paramVolDao = new Param_volDao();
        Classe_volDao classeVolDao = new Classe_volDao();
        Enregistrement_reservationDao enregDao = new Enregistrement_reservationDao();

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

                if (vol != null && classe != null) {
                    Classe_vol classeVol = classeVolDao.findByVolAndClasse(idVol, classe.getId_classe());
                    if (classeVol == null) {
                        req.setAttribute("error", "Aucune classe-vol trouvee pour ce vol et classe.");
                    } else {
                        Param_vol param = paramVolDao.findEnCoursByClasseVol(classeVol.getId_classe_vol());

                        if (param == null) {
                            req.setAttribute("error", "Aucun tarif disponible actuellement pour cette classe.");
                        } else {
                            int dejaReserve = enregDao.countConfirmedByParamVolId(param.getId_param_vol());
                            int restePlace = param.getQuantite() - dejaReserve;

                            if (restePlace <= 0) {
                                req.setAttribute("error", "Plus de places disponibles pour cette classe.");
                            } else {
                                ClasseTarifDispo tarifDispo = new ClasseTarifDispo();
                                tarifDispo.setClasse(classe);
                                tarifDispo.setParamVol(param);
                                tarifDispo.setRestePlace(restePlace);

                                req.setAttribute("volDetail", vol);
                                req.setAttribute("classe", action);
                                req.setAttribute("classeDonnees", classe);
                                req.setAttribute("tarifDispo", tarifDispo);

                                RequestDispatcher dispatcher = req
                                        .getRequestDispatcher("/WEB-INF/views/reservationAdmin.jsp");
                                dispatcher.forward(req, res);
                                return; 
                            }
                        }
                    }
                } else {
                    req.setAttribute("error", "Vol ou classe introuvable.");
                }
            }

            List<Vol> vols = volDao.findAll();
            req.setAttribute("listes", vols);
            req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors de la reservation : " + e.getMessage());
            out.println("error" + e.getMessage());
            List<Vol> vols;
            try {
                vols = volDao.findAll();
                req.setAttribute("listes", vols);
                req.getRequestDispatcher("/WEB-INF/views/homeAdmin.jsp").forward(req, res);
            } catch (Exception e1) {
                req.setAttribute("error", "Erreur be" + e.getMessage());
                e1.printStackTrace();
            }
        }
    }
}
