package com.example;

import com.example.model.Actor;
import com.example.repository.ActorRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ActorRepository repository = new ActorRepository();

            System.out.println("==== TODOS LOS ACTORES ====");
            List<Actor> allActors = repository.findAll();
            for (Actor actor : allActors) {
                System.out.println(actor.getActorID() + " - " + actor.getFirstName() + " " + actor.getLastName());
            }

            System.out.println("\n==== ACTOR POR ID ====");
            Actor actorById = repository.getByID(200); // Puedes cambiar el número para probar otro ID
            if (actorById != null) {
                System.out.println(actorById.getActorID() + " - " + actorById.getFirstName() + " " + actorById.getLastName());
            } else {
                System.out.println("Actor no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión o consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
