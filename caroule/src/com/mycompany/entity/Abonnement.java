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
public class Abonnement {
    private int id;
    private String type;
    private String dateD;
    private String dateF;
    private int prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Abonnement(int id, String type, String dateD, String dateF, int prix) {
        this.id = id;
        this.type = type;
        this.dateD = dateD;
        this.dateF = dateF;
        this.prix = prix;
    }

    public Abonnement(String type, String dateD, String dateF, int prix) {
        this.type = type;
        this.dateD = dateD;
        this.dateF = dateF;
        this.prix = prix;
    }

    public Abonnement() {
    }
    
}
