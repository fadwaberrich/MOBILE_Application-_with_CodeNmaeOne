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
public class Emplacement {

    private int id;
    private String lieu;
    private int capacite;
    private Stock Stock;

    public Stock getStock() {
        return Stock;
    }

    public void setStock(Stock Stock) {
        this.Stock = Stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    //constructeurs
    public Emplacement(int id, String lieu, int capacite) {
        this.id = id;
        this.lieu = lieu;
        this.capacite = capacite;

    }

    public Emplacement(String lieu, int capacite) {
        this.lieu = lieu;
        this.capacite = capacite;

    }

    public Emplacement(String lieu, int capacite, Stock Stock) {
        this.lieu = lieu;
        this.capacite = capacite;
        this.Stock = Stock;
    }

    public Emplacement() {
    }

}
