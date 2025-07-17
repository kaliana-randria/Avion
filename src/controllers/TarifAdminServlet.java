// package controllers;

// import dao.Param_volDao;
// import jakarta.servlet.*;
// import jakarta.servlet.http.*;
// import models.Param_vol;

// import java.io.IOException;
// import java.sql.Timestamp;
// import java.time.LocalDateTime;

// public class TarifAdminServlet extends HttpServlet {

//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {

//         try {
//             String idParamStr = request.getParameter("id_param_vol");
//             int idClasseVol = Integer.parseInt(request.getParameter("id_classe_vol"));
//             double prix = Double.parseDouble(request.getParameter("prix"));
//             int quantite = Integer.parseInt(request.getParameter("quantite"));
//             LocalDateTime dateLimite = LocalDateTime.parse(request.getParameter("date_limite_paiement"));

//             Param_volDao dao = new Param_volDao();
//             Param_vol tarif = new Param_vol(idClasseVol, prix, quantite, dateLimite);

//             if (idParamStr != null && !idParamStr.trim().isEmpty()) {
//                 tarif.setId_param_vol(Integer.parseInt(idParamStr));
//                 dao.update(tarif);
//             } else {
//                 dao.save(tarif);
//             }

//             response.sendRedirect("volAdmin?action=tarif&idVol=" + request.getParameter("idVol"));

//         } catch (Exception e) {
//             e.printStackTrace();
//             request.setAttribute("error", "Erreur : " + e.getMessage());
//             request.getRequestDispatcher("/WEB-INF/views/formTarif.jsp").forward(request, response);
//         }
//     }
// }
