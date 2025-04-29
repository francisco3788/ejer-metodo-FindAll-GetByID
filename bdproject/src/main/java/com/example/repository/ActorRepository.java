package com.example.repository;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository implements Repository<Actor> {

    @Override
    public List<Actor> findAll() {
        List<Actor> actors = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getInstance();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT actor_id, first_name, last_name FROM actor");

            while (rs.next()) {
                Actor actor = new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                actors.add(actor);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actors;
    }

    @Override
    public Actor getByID(Integer id) {
        Actor actor = null;
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?"
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                actor = new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actor;
    }

    @Override
    public void save(Actor actor) {
        try {
            Connection conn = DatabaseConnection.getInstance();

            // Verificar si ya existe el actor
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM actor WHERE actor_id = ?");
            checkStmt.setInt(1, actor.getActorID());
            ResultSet rs = checkStmt.executeQuery();

            boolean exists = false;
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

            rs.close();
            checkStmt.close();

            if (exists) {
                // UPDATE
                PreparedStatement updateStmt = conn.prepareStatement(
                    "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?"
                );
                updateStmt.setString(1, actor.getFirstName());
                updateStmt.setString(2, actor.getLastName());
                updateStmt.setInt(3, actor.getActorID());
                updateStmt.executeUpdate();
                updateStmt.close();
                System.out.println("Actor actualizado correctamente.");
            } else {
                // INSERT
                PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO actor (actor_id, first_name, last_name) VALUES (?, ?, ?)"
                );
                insertStmt.setInt(1, actor.getActorID());
                insertStmt.setString(2, actor.getFirstName());
                insertStmt.setString(3, actor.getLastName());
                insertStmt.executeUpdate();
                insertStmt.close();
                System.out.println("Actor insertado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM actor WHERE actor_id = ?");
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println("Actor eliminado correctamente.");
            } else {
                System.out.println("No se encontró el actor con ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
