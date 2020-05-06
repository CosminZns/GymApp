package com.cosmin.gym;

import com.cosmin.gym.model.User;

import java.io.Serializable;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Gym implements Serializable {
    Scanner sc = new Scanner(System.in);
    private final String host = "jdbc:mysql://localhost:3306/gym2";
    private final String uName = "cosmin";
    private final String uPass = "";
    Connection con = DriverManager.getConnection(host, uName, uPass);


    private ArrayList<User> users;
    private User loggedIn;


    public Gym(ArrayList<User> users) throws SQLException {
        this.users = users;
    }

    public User checkLog() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Put in the username");
        String user = sc.next();
        System.out.println("Put in the pin");
        String pin = sc.next();
        for (int i = 0; i < users.size(); i++) {
            if (user.equals(users.get(i).getUserName()) && pin.equals(users.get(i).getPassword())) {
                return loggedIn = users.get(i);
            }
        }
        return loggedIn = null;
    }

    public void printLog() {
        checkLog();
        int count = 0;
        do {
            if (loggedIn != null) {
                System.out.println(loggedIn.getName() + " logged in ");
            } else {
                count++;
                System.out.println("Wrong username or password, please try again");
                checkLog();
            }
        } while (loggedIn == null && count < 2);
    }

    public void showGoalMenu() {
        System.out.println("What is your goal?");
        System.out.println("1.Lose weight");
        System.out.println("2.Maintain weight");
        System.out.println("3.Gain weight");
    }

    public void loseWeight() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("How much do you want to lose?");
        double loseWeight = sc.nextInt();
        System.out.println("In how many days?");
        int days = sc.nextInt();
        double lostWeightPerDay = loseWeight / days;
        if (lostWeightPerDay > 0.5) {
            System.out.println("Sorry the amount of weight you want to lose is to high");
        } else if (lostWeightPerDay >= 0.1 && lostWeightPerDay <= 0.3) {
            loggedIn.setGoalWeight((int) (loggedIn.getWeight() - loseWeight));
            if (!checkUserDiet(users.indexOf(loggedIn) + 1)) {
                insertUserDiet(returnUserPosition(), 1);
            }
            if (!checkUserWorkout(returnUserPosition())) {
                setWorkouts("easy");
            }
            if (!checkGoalWeight(users.indexOf(loggedIn) + 1)) {
                updateGoalWeight(loggedIn.getGoalWeight(), users.indexOf(loggedIn) + 1);
                System.out.println("You have been given the lose diet and an  easy set of workouts ");
            }


        } else if (lostWeightPerDay >= 0.3 && lostWeightPerDay <= 0.5) {
            loggedIn.setGoalWeight((int) (loggedIn.getWeight() - loseWeight));
            if (!checkUserDiet(users.indexOf(loggedIn) + 1)) {
                insertUserDiet(returnUserPosition(), 1);
            }
            if (!checkUserWorkout(returnUserPosition())) {
                setWorkouts("hard");
            } else if (!checkGoalWeight(users.indexOf(loggedIn) + 1)) {
                updateGoalWeight(loggedIn.getGoalWeight(), users.indexOf(loggedIn) + 1);
                System.out.println("You have been given the lose diet and a hard set of workouts ");
            }
        }
    }

    public void gainWeight() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("How much do you want to gain?(kg)");
        double gainWeight = sc.nextInt();
        System.out.println("In how many days?");
        int days = sc.nextInt();
        double gainWeightPerDay = gainWeight / days;
        if (gainWeightPerDay > 0.3) {
            System.out.println("You can't gain this amount of weight in this amount of days");
        } else if (gainWeightPerDay >= 0.05 && gainWeightPerDay <= 0.2) {
            if (!checkGoalWeight(users.indexOf(loggedIn) + 1)) {
                updateGoalWeight(loggedIn.getGoalWeight(), users.indexOf(loggedIn) + 1);
            }
            if (!checkUserWorkout(returnUserPosition())) {
                setWorkouts("easy");
            }
            if (!checkUserDiet(users.indexOf(loggedIn) + 1)) {
                insertUserDiet(returnUserPosition(), 2);
                System.out.println("You have been given the gain diet and an easy set of workouts ");
            }


        } else if (gainWeightPerDay >= 0.2 && gainWeightPerDay <= 0.3) {
            if (!checkGoalWeight(users.indexOf(loggedIn) + 1)) {
                updateGoalWeight(loggedIn.getGoalWeight(), users.indexOf(loggedIn) + 1);
            }
            if (!checkUserWorkout(returnUserPosition())) {
                setWorkouts("hard");
            }
            if (!checkUserDiet(users.indexOf(loggedIn) + 1)) {
                insertUserDiet(returnUserPosition(), 2);
                System.out.println("You have been given the gain diet and a hard set of workouts ");
            }

        }
    }

    public void maintainWeight() throws SQLException {
        if (!checkGoalWeight(users.indexOf(loggedIn) + 1)) {
            updateGoalWeight(loggedIn.getGoalWeight(), users.indexOf(loggedIn) + 1);
        }
        if (!checkUserDiet(users.indexOf(loggedIn) + 1)) {
            insertUserDiet(returnUserPosition(), 3);
        }
        if (!checkUserWorkout(returnUserPosition())) {
            setWorkouts("easy");
            System.out.println("You have been given the maintain diet and an easy set of workouts ");
        }

    }

    public void goal() throws SQLException {
        if (checkGoalWeight(users.indexOf(loggedIn) + 1)) {
            System.out.println("You already have a given set of workouts and a diet, if u want to see them go to check profile");
        } else {
            showGoalMenu();
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    loseWeight();
                    break;
                case 2:
                    maintainWeight();
                    break;
                case 3:
                    gainWeight();
                    break;
            }
        }

    }

    public void checkProfile(int id) throws SQLException {
        String name = "";
        int weight = 0;
        int goalWeight = 0;
        String sql = "select * from users where idusers=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            name = rs.getString("name_user");
            weight = rs.getInt("weight");
            goalWeight = rs.getInt("goal_weight");
        }
        System.out.println("Hello " + name);
        System.out.println("Your weight is " + weight);
        System.out.println("Your weight goal is " + goalWeight);
        String sql2 = "select idusers,name_diet from users inner join users_diets on users.idusers=users_idusers inner join diets on users_diets.Diets_idDiets=diets.idDiets where idusers=?";
        PreparedStatement stat = con.prepareStatement(sql2);
        stat.setInt(1, id);
        ResultSet result = stat.executeQuery();
        if (result.next()) {
            System.out.println("Your diet is " + result.getString("name_diet"));
        }
        String sql3 = "select idusers,name_workout from users inner join users_workouts on users.idusers=users_idusers inner join workouts on users_workouts.workouts_idworkouts=workouts.idworkouts where idusers=?";
        PreparedStatement st = con.prepareStatement(sql3);
        st.setInt(1, id);
        ResultSet resultSet = st.executeQuery();
        System.out.println("Your workouts are:");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name_workout"));
        }

    }

    public int returnUserPosition() {
        return users.indexOf(loggedIn) + 1;
    }

    public int returnWorkoutPosition(String nameWorkouts) throws SQLException {
        String sql = "select idworkouts from workouts where name_workout=?";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, nameWorkouts);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getInt("idworkouts");
        }
        return -1;
    }

    public void calculateThings(int iud) {
        int weight = 0;
        try {
            String sql = "select weight from users where idusers=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, iud);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                weight = rs.getInt("weight");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DecimalFormat df2 = new DecimalFormat("#.##");
        System.out.println("Put in your height(m)");
        double height = sc.nextDouble();
        System.out.println("Put in your age");
        int age = sc.nextInt();
        System.out.println("Put in your gender(M/W)");
        String gender = sc.next();
        double BMI = weight / (height * height);
        System.out.println("Your BMI is " + df2.format(BMI));
        if (BMI < 18.5) {
            System.out.println("Your are underweight");
        } else if (BMI >= 18.5 && BMI <= 24.9) {
            System.out.println("Your weight is normal");
        } else if (BMI >= 25 && BMI <= 29.9) {
            System.out.println("You are overweight");
        } else if (BMI > 30) {
            System.out.println("You are obese");
        }
        double bodyFat;
        if (gender.equals("m")) {
            bodyFat = (1.20 * BMI) + (0.23 * age) - 16.2;
            System.out.println("Your body fat is " + df2.format(bodyFat) + "%");
        }
        if (gender.equals("w")) {
            bodyFat = (1.20 * BMI) + (0.23 * age) - 5.4;
            System.out.println("Your body fat is " + df2.format(bodyFat) + "%");
        }

    }

    public void updateGoalWeight(int goalWeight, int iud) {
        try {
            String sql = "update users set goal_Weight=? where idusers=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, goalWeight);
            stmt.setInt(2, iud);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkGoalWeight(int iud) throws SQLException {
        int result = 0;
        String sql = "select goal_weight from users " + " where idusers=? ";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, iud);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            result = rs.getInt("goal_weight");
        }

        return result > 0;
    }

    public boolean checkUserWorkout(int id_user) {
        try {
            String sql = "select workouts_idworkouts from users_workouts where users_idusers=? ";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id_user);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getInt("workouts_idworkouts") != -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertUserWorkout(int id_user, int id_workout) {
        try {
            String sql = "insert into users_workouts (users_idusers,workouts_idworkouts) values(?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id_user);
            stat.setInt(2, id_workout);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> returnWorkoutsNames(String type) throws SQLException {
        ArrayList<String> names = new ArrayList<>();
        String sql = "select name_workout from workouts where type_workout=?";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, type);
        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            names.add(rs.getString("name_workout"));
        }
        return names;
    }

    public void setWorkouts(String type) throws SQLException {
        ArrayList<String> names = returnWorkoutsNames(type);
        for (int i = 0; i < names.size(); i++) {
            insertUserWorkout(users.indexOf(loggedIn) + 1, returnWorkoutPosition(names.get(i)));
        }
    }

    public void insertUserDiet(int id_user, int id_diet) throws SQLException {
        String sql = "insert into users_diets (users_idusers,Diets_idDiets) values (?,?)";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, id_user);
        stat.setInt(2, id_diet);
        stat.executeUpdate();
    }

    public boolean checkUserDiet(int id_user) throws SQLException {
        String sql = "select Diets_idDiets from users_diets where users_idusers=? ";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, id_user);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getInt("Diets_idDiets") != -1;
        }
        return false;
    }


}
