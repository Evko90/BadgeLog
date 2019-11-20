package Server.DB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StudentDB {

    public boolean addStudent(String name) {
        Connection connection = null;
        try {
            connection = BadgeDataBase.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO student (NAME) VALUES (?);");
            statement.setString(1, name);
            statement.executeUpdate();
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

    public boolean updateStudent(String name, int id) {
        Connection connection = null;
        try {
            connection = BadgeDataBase.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement("UPDATE student SET NAME = ? WHERE id = ?;");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            return false;
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Connection connection = null;
        try {
            connection = BadgeDataBase.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement("DELETE from student WHERE id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            return false;
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public Map<Integer, String> read() {
        Map<Integer, String> map = new HashMap<>();
        Connection connection = null;
        try {
            connection =BadgeDataBase.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM student ORDER BY NAME");
            while (result.next()) {
                map.put(result.getInt("id"), result.getString("NAME"));
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
    }finally {
        if(connection!=null) {
            try {
                connection.close();
                return map;
            } catch (SQLException e) {
            }
        }
        }
        return null;
    }
}
