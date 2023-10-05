/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Emplacement;
import com.mycompany.entity.Produit;
import com.mycompany.entity.Stock;
import com.mycompany.myapp.StockForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServiceStock {
    public ArrayList<Stock> Stock;

    public static ServiceStock instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceStock() {
        req = new ConnectionRequest();
    }

    public static ServiceStock getInstance() {
        if (instance == null) {
            instance = new ServiceStock();
        }
        return instance;
    }
    //afficahge
          //parseproduit
    public ArrayList<Stock> parseStock(String jsonText) {
        try {
            Stock = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> EquipeListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) EquipeListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Stock s = new Stock();

                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
               float quantite = Float.parseFloat(obj.get("quantite").toString());


                s.setLibelle(obj.get("libelle").toString());
                s.setDisponibilite(obj.get("disponibilite").toString());
                
                s.setPrix((int)prix);
                s.setId((int)id);
                s.setQuantite((int) quantite);
               

              //  h.setDateCreation((Date) Date.parseDate(obj.get("DateCreation").toString()));
               

                Stock.add(s);
            }

        } catch (IOException ex) {

        }
        return Stock;
    }
    //ajout
    
         public void AjouterStock(Stock s ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "AjouterStock?libelle="+s.getLibelle()+"&quantite="+s.getQuantite()+"&prix="+s.getPrix()
                +"&disponibilite="+s.getDisponibilite() + "&idProduit=" + s.getIdProduit() +"&idEmplacement=" + s.getEmplacement() ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new StockForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }   
         
         //delete
         
        public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "deleteStock/"+id;
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }
           //update
        public void Update(Stock s ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "updateStock?id="+s.getId()+ "&libelle="+s.getLibelle()+"&quantite="+s.getQuantite()+"&prix="+s.getPrix()+"&disponibilite="+s.getDisponibilite();
        req.setUrl(url);
        System.out.println("===>" + url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new StockForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
        
    
    //affichage des produits :
    public ArrayList<Stock> getAllStocks() {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"displayStock";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Stock = parseStock(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Stock;
    }
        
        
    
}
