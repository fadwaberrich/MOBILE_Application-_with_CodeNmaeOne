/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.Collection;

/**
 *
 * @author PC
 */
public class Produit {
    
    // declaration
    
    private int id;
    private String libelle;
    private String image;
    private String description;
    private float prix;
    private String type;
    private Favoris favoris ;
    private Achat achats;
    private Commande commandes;
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Achat getAchats() {
        return achats;
    }

    public void setAchats(Achat achats) {
        this.achats = achats;
    }

    public Commande getCommandes() {
        return commandes;
    }

    public void setCommandes(Commande commandes) {
        this.commandes = commandes;
    }

    public Favoris getFavoris() {
        return favoris;
    }

    public void setFavoris(Favoris favoris) {
        this.favoris = favoris;
    }
   
    
    
 //getters & setters

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
   //constructeurs 

        public Produit(int id, String libelle, String image, String description, float prix, String type) {
        this.id = id;
        this.libelle = libelle;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }
        
        
    public Produit(String libelle, String image, String description, float prix, String type) {
        this.libelle = libelle;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

    public Produit(String libelle, String image, String description, float prix, String type, Favoris favoris) {
        this.libelle = libelle;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
        this.favoris = favoris;
    }

    public Produit(String libelle, String description, String type) {
        this.libelle = libelle;
        this.description = description;
        
        this.type = type;
    }

    public Produit() {
    }
    

    
    
    
}
