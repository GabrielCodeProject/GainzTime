package com.example.gainztime.entities;


public class WorkoutAssembler {
    int id, idWorkout, idExercice;

    public WorkoutAssembler() {
    }

    public WorkoutAssembler(int id, int idWorkout, int idExercice) {
        this.id = id;
        this.idWorkout = idWorkout;
        this.idExercice = idExercice;
    }

    public int getId() {
        return id;
    }

    public int getIdWorkout() {
        return idWorkout;
    }

    public int getIdExercice() {
        return idExercice;
    }
}
