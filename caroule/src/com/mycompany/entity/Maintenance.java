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
public class Maintenance {
    
    private int id;
    private int id_produit_id;
    private int relation_id;
    private int reclamation_id;
    private String date_debut ;
    private String date_fin ;  
    private String adresse;
    private String etat;
    private String description;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit_id() {
        return id_produit_id;
    }

    public void setId_produit_id(int id_produit_id) {
        this.id_produit_id = id_produit_id;
    }

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Maintenance(int id, int id_produit_id, int relation_id, int reclamation_id, String date_debut, String date_fin, String adresse, String etat, String description) {
        this.id = id;
        this.id_produit_id = id_produit_id;
        this.relation_id = relation_id;
        this.reclamation_id = reclamation_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.adresse = adresse;
        this.etat = etat;
        this.description = description;
    }
    public Maintenance(){
        
    }
}
