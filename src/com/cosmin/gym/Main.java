package com.cosmin.gym;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {



    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, SQLException {


        Gym gym = new Gym(DBHelper.createUsers());
          gym.printLog();
        while (true) {
            doMenu(gym, gym.returnUserPosition());
        }
    }



    public static void showMenu() {
        System.out.println("1.Goal");
        System.out.println("2.Calculate your statistisc");
        System.out.println("3.Check profile");
        System.out.println("4.Log out");
        System.out.println("5.Exit");
    }

    public static void doMenu(Gym gym, int position) throws SQLException {
        showMenu();
        int option = sc.nextInt();
        switch (option) {
            case 1:
                gym.goal();
                break;
            case 2:
                gym.calculateThings(position);
                break;
            case 3:
                gym.checkProfile(position);
                break;
            case 4:
                gym.printLog();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

}
