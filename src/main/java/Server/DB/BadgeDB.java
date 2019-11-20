package Server.DB;

import Server.Badge;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BadgeDB {
    private boolean response = false;

    public boolean foundDate(String date) {
        Connection connection = null;
        try {
            connection = BadgeDataBase.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT STUDENT_ID FROM badge where DATE = ? ;");
            statement.setString(1, date);
            ResultSet result = statement.executeQuery();
            response = result.next();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.out.println(e);
            return response;
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    return response;
                } catch (SQLException e) {
                    System.out.println(e);
                    return response;
                }
            }
        }
        return response;
    }

    public boolean addBadge(String date, ArrayList<Integer> id) {
        Connection connection = null;
        try {
            connection = BadgeDataBase.getInstance().getConnection();

            for (int stId:id) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO badge (STUDENT_ID, DATE, BADGE ) VALUES (?, ?, ?);");
                statement.setInt(1, stId);
                statement.setString(2, date);
                statement.setInt(3, 0);
                statement.executeUpdate();
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                System.out.println(e);
                return false;
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    return true;
                } catch (SQLException e) {
                    System.out.println(e);
                    return false;
                }
            }
        }
        return false;
    }

    public boolean updateBadge(String date, ArrayList<Badge> badgeList) {
        Connection connection = null;
        try {
            connection = BadgeDataBase.getInstance().getConnection();

            for (Badge badge:badgeList){
                PreparedStatement statement = connection.prepareStatement("UPDATE badge SET BADGE = ? WHERE DATE = ? AND STUDENT_ID = ? ;");
                statement.setInt(1, badge.getBadge());
                statement.setString(2, date);
                statement.setInt(3, badge.getStudentId());
                statement.executeUpdate();
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    return true;
                } catch (SQLException e) {
                    System.out.println(e);
                    return false;
                }
            }
        }
        return false;
    }

    public String read(String date) {
        String response = "";
        Connection connection = null;
        try {
            connection = BadgeDataBase.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT STUDENT_ID, BADGE, id FROM badge where DATE = ? ;");
            statement.setString(1, date);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Map<String, String> badgeList = new HashMap<>();
                badgeList.put("Student_id", Integer.toString(result.getInt("STUDENT_ID")));
                badgeList.put("id", Integer.toString(result.getInt("id")));
                badgeList.put("badge", Integer.toString(result.getInt("BADGE")));
                response+=badgeList.toString();
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.out.println(e);
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return response;
    }
}
