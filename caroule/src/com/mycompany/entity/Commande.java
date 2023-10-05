/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

public class Commande {

    private int id;
    private int idUser;
    private int idProduit;
    private int nbProduits;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    ;


    
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getNbProduits() {
        return nbProduits;
    }

    public void setNbProduits(int nbProduits) {
        this.nbProduits = nbProduits;
    }

    //constructeurs 
    public Commande(int id, int idUser, int idProduit, int nbProduits) {
        this.id = id;
        this.idProduit = idProduit;
        this.nbProduits = nbProduits;
    }

    public Commande() {
    }

}
