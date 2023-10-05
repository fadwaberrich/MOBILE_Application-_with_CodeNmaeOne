/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author user
 */
public class Message {
    private int id;
    private int idUser;
    private String Date;
    private String contenu;
    private int idSujet;

    public Message() {}
    
    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getDate() {
        return Date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(int idSujet) {
        this.idSujet = idSujet;
    }

    public Message(int id, int idUser, String Date, String contenu, int idSujet) {
        this.id = id;
        this.idUser = idUser;
        this.Date = Date;
        this.contenu = contenu;
        this.idSujet = idSujet;
    }
    
    
}
