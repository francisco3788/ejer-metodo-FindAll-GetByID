package com.example.utils;



import com.example.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestActorQuery {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getInstance();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT actor_id, first_name, last_name FROM actor LIMIT 5");

            while (rs.next()) {
                int id = rs.getInt("actor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                System.out.println(id + " - " + firstName + " " + lastName);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
