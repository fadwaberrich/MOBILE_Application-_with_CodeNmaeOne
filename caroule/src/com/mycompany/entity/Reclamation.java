/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author PC
 */
public class Reclamation {
    
    private int id;
    private int idUser;
    private String description ;
    private String date ;
    private String objet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Reclamation(int id, int idUser, String description, String date, String objet) {
        this.id = id;
        this.idUser = idUser;
        this.description = description;
        this.date = date;
        this.objet = objet;
    }
    
    public Reclamation (){
        
    }
}