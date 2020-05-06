package com.cosmin.gym;

import com.cosmin.gym.model.Aliment;
import com.cosmin.gym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBHelper {
// FACUT CAMELCASE SI SCANNER

    static String host = "jdbc:mysql://localhost:3306/gym2";
    static String uName = "cosmin";
    static String uPass = "";
    static Connection con;
    static Scanner sc= new Scanner(System.in);

    static {
        try {
            con = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> createUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement stat = con.createStatement();
            String sql = "select * from users";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("name_user"), rs.getInt("weight"), rs.getInt("goal_weight"), null, null);
                users.add(user);
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return users;
    }

    public static int returnIdExercise(String name_exercise) throws SQLException {
        String sql = "select idexercise from exercise where name_exercise=?";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, name_exercise);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getInt("idexercise");
        }
        return -1;
    }

    public static int returnIdWorkout(String name_workout) throws SQLException {
        String sql = "select idworkouts from workouts where name_workout=?";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, name_workout);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getInt("idworkouts");
        }
        return -1;
    }


    public static void insertExerciseInWorkout(int id_exercise, int id_workout) throws SQLException {
        String sql = "insert into workouts_excercise (workouts_idworkouts,excercise_idexcercise) values (?,?)";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, id_workout);
        stat.setInt(2, id_exercise);
        int rs = stat.executeUpdate();
    }

    public static void insertAlimentInDiet(int id_aliment, int id_diet) throws SQLException {
        String sql = "insert into aliments_diets (aliments_idaliments,Diets_idDiets) values (?,?)";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, id_aliment);
        stat.setInt(2, id_diet);
        int rs = stat.executeUpdate();
    }

    public static int returnIdAliment(String name_aliment) throws SQLException {
        String sql = "select idaliments from aliments where name_aliments=?";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, name_aliment);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getInt("idaliments");
        }
        return -1;
    }

    public static void insertUser() throws SQLException {
        System.out.println("Please enter the username");
        String username = sc.next();
        System.out.println("Please enter the password");
        String password = sc.next();
        System.out.println("Please enter the name");
        String name = sc.next();
        System.out.println("Please enter the weight");
        int weight = sc.nextInt();
        String sql = "insert into users (username,password,name_user,weight) values (?,?,?,?)";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, username);
        stat.setString(2, password);
        stat.setString(3, name);
        stat.setInt(4, weight);
        int rs = stat.executeUpdate();
    }

    public static void insertExercise() throws SQLException {
        System.out.println("Please enter the exercise name");
        String name = sc.next();
        System.out.println("Please enter the group of the exercise");
        String type = sc.next();
        String sql = "INSERT INTO `gym2`.`exercise` (`name_exercise`,`group_exercise`) VALUES (?,?)";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, name);
        stat.setString(2, type);
        int rs = stat.executeUpdate();

    }
    public static void deleteUser(int id) throws SQLException {
        String sql = "delete from users where idusers=?";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, id);
        int rs = stat.executeUpdate();
        String sql2 = "delete from users_diets where idusers=?";
        stat = con.prepareStatement(sql2);
        stat.setInt(1, id);
        rs = stat.executeUpdate();
        String sql3="delete from users_workouts where idusers=?";
        stat=con.prepareStatement(sql3);
        stat.setInt(1, id);
        rs=stat.executeUpdate();
    }


    public static ArrayList<String> returnNamesAliment() throws SQLException {
        ArrayList<String> names = null;
        String name = null;
        String sql = "select name_aliments from aliemnts";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            name = rs.getString("name_aliments");
            names.add(name);

        }
        return names;
    }


    public static ArrayList<String> returnNamesDiet() throws SQLException {
        ArrayList<String> names = null;
        String name;
        String sql = "select name_diet from diets";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            name = rs.getString("name_diet");
            names.add(name);

        }
        return names;
    }



    public static int returnIdDiet(String name_diet) throws SQLException {
        String sql = "select idDiets from diets where name_diet=?";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, name_diet);
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            return rs.getInt("idDiets");
        }
        return -1;
    }
    public static ArrayList<Aliment> createAliments() {
        ArrayList<Aliment> aliments = new ArrayList<>();
        try {
            Statement stat = con.createStatement();
            String sql = "select * from aliments";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name_aliments");
                int grams = rs.getInt("grams_aliments");
                int kcal = rs.getInt("kcal_aliment");
                int proteins = rs.getInt("protein_aliment");
                int fat = rs.getInt("fat_aliemnt");
                int carbs = rs.getInt("carb_aliments");
                Aliment aliment = new Aliment(name, grams, kcal, carbs, fat, proteins);
                aliments.add(aliment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aliments;
    }

}
