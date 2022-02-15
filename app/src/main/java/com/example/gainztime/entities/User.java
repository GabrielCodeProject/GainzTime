package com.example.gainztime.entities;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    int id, idPhotoAssembler;
    String age;
    // le user optient des point de niveau apres chaque plan terminer
    // ceci augmenteras la difficulter des prochaine entrainement
    //peut etre changer par lutilisateur si trop difficile;
    int level;
    String weight;

    String firstName;
    String lastName;
    String level_fitness;
    String gender;
    String height;

    ArrayList list_workouts;
    ArrayList list_images_evolution;

    public User(int id, @Nullable int idPhotoAssembler, String age, String weight, String firstName, String lastName, String level_fitness, String gender, String height) {
        this.id = id;
        this.idPhotoAssembler = idPhotoAssembler;
        this.age = age;
        this.weight = (weight.length() != 0) ? weight : "Not set";
        this.firstName = firstName;
        this.lastName = lastName;
        this.level_fitness = (level_fitness.length() != 0) ? level_fitness : "Beginer";
        this.gender = (gender.length() != 0) ? gender : "Not set";
        this.height = height;
        this.list_workouts = new ArrayList();
        this.list_images_evolution = new ArrayList();
    }

    public String toJSON() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLevel_fitness() {
        return level_fitness;
    }

    public void setLevel_fitness(String level_fitness) {
        this.level_fitness = level_fitness;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public ArrayList getList_workouts() {
        return list_workouts;
    }

    public void setList_workouts(ArrayList list_workouts) {
        this.list_workouts = list_workouts;
    }

    public ArrayList getList_images_evolution() {
        return list_images_evolution;
    }

    public void setList_images_evolution(ArrayList list_images_evolution) {
        this.list_images_evolution = list_images_evolution;
    }
}
