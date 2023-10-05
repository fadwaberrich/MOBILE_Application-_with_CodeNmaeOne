/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author ASUS
 */
public class Location {

    public static void setUIID(String selectBar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    int id;
    String Date;
    String Heure;
    float duree;
    int idAbonnement;
    int idUser;
    public Location(){}
    public Location(String Date, String Heure, float Duree, int idAbonnement, int idUser){
        this.Date = Date;
        this.Heure= Heure;
        this.idAbonnement= idAbonnement;
        this.idUser= idUser;
    }
       public Location(String Date, String heure, float duree){
        this.Date = Date;
        this.Heure= Heure;
      
    }
    
     public Location(int id,String Date, String heure, float duree, int idAbonnement, int idUser){
        this.id= id;
        this.Date = Date;
        this.Heure= Heure;
        this.idAbonnement= idAbonnement;
        this.idUser= idUser;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return Date;
    }

    public String getHeure() {
        return Heure;
    }

    public float getDuree() {
        return duree;
    }

    public int getIdAbonnement() {
        return idAbonnement;
    }
   public int getIdUser() {
        return idUser;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setHeure(String Heure) {
        this.Heure = Heure;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

 

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public void setIdAbonnement(int idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", Date=" + Date + ", Heure=" + Heure + ", duree=" + duree + ", idAbonnement=" + idAbonnement + ", idUser=" + idUser + '}';
    }

 

    
    
}
