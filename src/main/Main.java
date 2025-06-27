package main;

import dao.CheckReservationTask;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new CheckReservationTask(), 0, 60000); // chaque 60 secondes
        System.out.println("🚀 Planificateur lancé !");
    }
}
