package dao;

import java.util.*;
import java.time.LocalDateTime;

import models.*;

public class CheckReservationTask extends TimerTask {
    @Override
    public void run() {
        try {
            Enregistrement_reservationDao enregDao = new Enregistrement_reservationDao();
            ReservationDao reservationDao = new ReservationDao();
            Param_volDao paramVolDao = new Param_volDao();
            Classe_volDao classeVolDao = new Classe_volDao();

            List<Enregistrement_reservation> enregistrements = enregDao.findAll();

            for (Enregistrement_reservation enr : enregistrements) {
                if (enr.isEst_annule()) continue;

                Reservation resv = reservationDao.findById(enr.getId_reservation());
                if (resv.isEst_payer()) continue;

                Param_vol param = paramVolDao.findById(resv.getId_param_vol());

                if (param.getDate_limite_paiement().isBefore(LocalDateTime.now())) {
                    System.out.println("Annulation automatique : REF " + enr.getNum_reference());

                    enr.setEst_annule(true);
                    enregDao.update(enr);

                    Classe_vol classeVol = classeVolDao.findById(param.getId_classe_vol());
                    Param_vol enCours = paramVolDao.findEnCoursByClasseVol(classeVol.getId_classe_vol());

                    if (enCours != null) {
                        enCours.setQuantite(enCours.getQuantite() + 1);
                        paramVolDao.updateQuantite(enCours);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
