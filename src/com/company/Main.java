package com.company;
import org.gnome.gtk.Gtk;
import org.gnome.notify.Notification;
import org.gnome.notify.Notify;
import java.util.Scanner;

public class Main {

    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the time in minutes for the timer (numbers only): ");
        int number = scan.nextInt();
        System.out.println("===========================================================");
        Gtk.init(args);
        Notify.init("Timer");
        progressBar(number);

    }

    public static void progressBar(int time){
        long now = System.currentTimeMillis();
        int time_seconds = time * 60;
        int target_time = (((int) now)/1000) + time_seconds;
        int barSize = 50;
        System.out.println(ANSI_YELLOW + "Progress for " + time + " minutes, or " + time_seconds + " seconds." + ANSI_RESET);
        for (int i = (int) now /1000; i <= target_time; i++){
            double remainingPercent = ((i - (int)now/1000))*barSize/(target_time - (int)now/1000);
            String bar = "█".repeat((int)remainingPercent);
            String emptyBar = " ".repeat(barSize - (int)remainingPercent);
            System.out.print("[" + ANSI_BLUE + bar + emptyBar + ANSI_RESET + "]" + "\r");
            // System.out.println(remainingPercent);
            // System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Notification myNotification = new Notification("Hey there!", "The timer is up.", "dialog-information");
        myNotification.show();

    }
}
