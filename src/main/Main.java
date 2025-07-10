package main;

import dao.CheckReservationTask;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new CheckReservationTask(), 0, 30000); // repeter isaky ny 30s
        System.out.println("🚀 Planificateur lance !");
    }
}
