/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;


public class Achat {
            
    // declaration
    
    private int id;
    private String date;
    private String nomClient;
    private int numeroClient;
    private int idUser;
    private int idProduit;
   
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this. nomClient =  nomClient;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }


    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
    

      //constructeurs 

        public Achat(int id, String date, String nomClient, int  numeroClient , int idProduit, int idUser) 
        {
        this.id = id;
        this.date = date;
        this.nomClient = nomClient;
        this.numeroClient = numeroClient;
        this.idUser = idUser;
        this.idProduit = idProduit;
    }
        
        public Achat() {
    }
        
    
   

    
}
