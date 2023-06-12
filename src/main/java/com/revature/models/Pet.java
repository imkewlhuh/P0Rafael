package com.revature.models;

public class Pet {

    private int pet_id;

    private String name;

    private String species;

    private User user;

    private int user_id_fk;

    public Pet() {
    };

    public Pet(int pet_id, String name, String species, User user, int user_id_fk) {
        this.pet_id = pet_id;
        this.name = name;
        this.species = species;
        this.user = user;
        this.user_id_fk = user_id_fk;
    }

    public Pet(int pet_id, String name, String species, User user) {
        this.pet_id = pet_id;
        this.name = name;
        this.species = species;
        this.user = user;
    };

    public Pet(String name, String species, User user) {
        this.name = name;
        this.species = species;
        this.user = user;
    };

    public Pet(String name, String species, int user_id_fk) {
        this.name = name;
        this.species = species;
        this.user_id_fk = user_id_fk;
    };

    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public int getPet_id() {
        return pet_id;
    };

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    };

    public String getName() {
        return name;
    };

    public void setName(String name) {
        this.name = name;
    };

    public String getSpecies() {
        return species;
    };

    public void setSpecies(String species) {
        this.species = species;
    };

    public User getUser() {
        return user;
    };

    public void setUser(User user) {
        this.user = user;
    };

    public int getUser_id_fk() {
        return user_id_fk;
    };

    public void setUser_id_fk(int user_id_fk) {
        this.user_id_fk = user_id_fk;
    };

    @Override
    public String toString() {
        return "\n Pet{" +
                "pet_id=" + pet_id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", user=" + user +
                ", user_id_fk=" + user_id_fk +
                '}';
    };

};
