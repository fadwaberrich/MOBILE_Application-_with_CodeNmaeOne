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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.Favoris;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServicesFavoris {
    
  public ArrayList<Favoris> Favoris;
    private static int id ;
    public static ServicesFavoris instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicesFavoris() {
        req = new ConnectionRequest();
    }

    public static ServicesFavoris getInstance() {
        if (instance == null) {
            instance = new ServicesFavoris();
        }
        return instance;
    } 
    
    //affichage front 
    //parseproduit
    public ArrayList<Favoris> parseFavoris(String jsonText) {
                try {
            Favoris = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Favoris f = new Favoris();
                
                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                Favoris.add(f);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Favoris;
    }
    
    //affichage des produits :
    public ArrayList<Favoris> getAllFavoris() {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"displayFavoris/";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override   
            public void actionPerformed(NetworkEvent evt) {
                Favoris = parseFavoris(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Favoris;
    }
    
    
    
        //affichage des favoris
    
        public ArrayList<Favoris>affichageFavoris(){
        
        String url = PageWeb.BASE_URL+"displayFavoris";
        
                req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Favoris = parseFavoris(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Favoris;
    }
    
}
