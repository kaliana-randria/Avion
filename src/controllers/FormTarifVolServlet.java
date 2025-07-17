package controllers;

import dao.Classe_volDao;
import dao.Param_volDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.Classe_vol;
import models.Param_vol;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FormTarifVolServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                PrintWriter out = response.getWriter();

        try {
            int idVol = Integer.parseInt(request.getParameter("idVol"));

            Classe_volDao classeVolDao = new Classe_volDao();
            Param_volDao paramVolDao = new Param_volDao();

            List<Classe_vol> classes = classeVolDao.findByVolId(idVol);
            request.setAttribute("classes", classes);

            String idParamStr = request.getParameter("id_param_vol");
            if (idParamStr != null) {
                int idParam = Integer.parseInt(idParamStr);
                Param_vol tarif = paramVolDao.findById(idParam);
                request.setAttribute("tarifToUpdate", tarif);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/formTarif.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            out.println("error" + e.getMessage());
            throw new ServletException(e.getMessage());
        }
    }
}
