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
            Actor actorById = repository.getByID(100); // Cambia el ID si quieres probar otro
            if (actorById != null) {
                System.out.println(actorById.getActorID() + " - " + actorById.getFirstName() + " " + actorById.getLastName());
            } else {
                System.out.println("Actor no encontrado.");
            }

            // ===== PRUEBA INSERTAR NUEVO ACTOR =====
            System.out.println("\n==== INSERTANDO NUEVO ACTOR ====");
            Actor nuevo = new Actor(204, "ERIKA", "PEREZ");
            repository.save(nuevo);

            // ===== PRUEBA ACTUALIZAR ACTOR EXISTENTE =====
            System.out.println("\n==== ACTUALIZANDO ACTOR EXISTENTE ====");
            Actor actualizado = new Actor(200, "DANILO", "CARLOSAMA");
            repository.save(actualizado);

            // ===== PRUEBA ELIMINAR ACTOR POR ID =====
            System.out.println("\n==== ELIMINANDO ACTOR ====");
            repository.delete(300);

        } catch (Exception e) {
            System.out.println("Error de conexión o consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
//RECOMEDACION CARGAR O EJECUTAR 2 VECES EL CODIGO PARA VER LOS CAMBIOS AGREGADSO EN LOS METODOS..