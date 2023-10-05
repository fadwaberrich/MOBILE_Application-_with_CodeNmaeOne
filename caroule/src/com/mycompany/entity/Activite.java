/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;



/**
 *
 * @author Lenovo
 */
public class Activite {
    //declaration 
     private int id;
    private String nom;
    private String description;
    private String image;
    private int idEvenement; 
    //les getters and setters 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

   
    //les constructeurs 

    public Activite(int id, String nom, String description, String image, int idEvenement) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.idEvenement = idEvenement;
    }

    public Activite(String nom, String description, String image, int idEvenement) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.idEvenement = idEvenement;
    }

    public Activite() {
    }
    
}
