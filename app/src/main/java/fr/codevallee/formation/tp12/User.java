package fr.codevallee.formation.tp12;

/**
 * Created by guillaumelazaro on 16/10/2017.
 */

public class User {
    private int id;
    private String prenom;
    private String nom;
    private int age;
    private String metier;

    public User(int id, String nom, String prenom, int age, String metier) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.metier = metier;
    }

    public User(String nom, String prenom, int age, String metier) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.metier = metier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }
}
