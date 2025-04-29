package com.example.repository;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = " + id);

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
    public void save(Actor t) {
        // No se pide implementación en esta tarea
    }

    @Override
    public void delete(Integer id) {
        // No se pide implementación en esta tarea
    }
}
