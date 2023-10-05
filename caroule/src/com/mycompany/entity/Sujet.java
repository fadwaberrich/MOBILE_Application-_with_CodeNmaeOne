/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.ArrayList;

/**x
 *
 * @author user
 */
public class Sujet {
    
    private int id;
    private int nbReponses;
    private int nbVues;
    private String Date;
    private String titre;
    private String contenu;
    private User idUser;

    private ArrayList<Message> messages;

    public int getId() {
        return id;
    }

    public int getNbReponses() {
        return nbReponses;
    }

    public int getNbVues() {
        return nbVues;
    }

    public String getDate() {
        return Date;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNbReponses(int nbReponses) {
        this.nbReponses = nbReponses;
    }

    public void setNbVues(int nbVues) {
        this.nbVues = nbVues;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Sujet(int id, String Date, String titre, String contenu) {
        this.id = id;
        this.nbReponses = 0;
        this.nbVues = 0;
        this.Date = Date;
        this.titre = titre;
        this.contenu = contenu;
        this.messages = new ArrayList();
    }
    
    public Sujet(){}
    
    
}
