/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Evenement {
     // declaration
    
    private int id;
    private String nom;
    private String dateD;
    private String dateF;
    private String lieu;
    private String type;  
    private int nb_participants;
    private int nb_places;
    private Collection<Activite> activite ;
    
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

    public String getDateD() {
        return dateD;
    }

    public void setDateD(String dateD) {
        this.dateD = dateD;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public Collection<Activite> getActivite() {
        return activite;
    }

    public void setActivite(List<Activite> activite) {
        this.activite = activite;
    }
    
    //les constructeurs 

   public Evenement(int id, String nom, String dateD, String dateF, String lieu, String type, int nb_participants, int nb_places, Collection<Activite> activite) {
        this.id = id;
        this.nom = nom;
        this.dateD = dateD;
        this.dateF = dateF;
        this.lieu = lieu;
        this.type = type;
        this.nb_participants = nb_participants;
        this.nb_places = nb_places;
        this.activite = activite;
    }

    public Evenement() {
    }

    public Evenement(String nom, String dateD, String dateF, String lieu, String type, int nb_participants, int nb_places, Collection<Activite> activite) {
        this.nom = nom;
        this.dateD = dateD;
        this.dateF = dateF;
        this.lieu = lieu;
        this.type = type;
        this.nb_participants = nb_participants;
        this.nb_places = nb_places;
        this.activite = activite;

    }

}
