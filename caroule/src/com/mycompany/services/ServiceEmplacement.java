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
import com.mycompany.myapp.EmplacementForm;
import com.mycompany.utils.PageWeb;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServiceEmplacement {
                   


    public static ServiceEmplacement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEmplacement() {
        req = new ConnectionRequest();
    }

    public static ServiceEmplacement getInstance() {
        if (instance == null) {
            instance = new ServiceEmplacement();
        }
        return instance;
    }
     //affichage des Emplacement
    public ArrayList<Emplacement>affichageEmplacement(){
        ArrayList<Emplacement> result = new ArrayList<>();
        
        String url = PageWeb.BASE_URL+"displayEmplacement";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapEmplacement = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));   
                    List<Map<String,Object> > listOfMaps = (List<Map<String,Object> >) mapEmplacement.get("root");
                
                for(Map<String,Object> obj : listOfMaps) {
                    Emplacement e = new Emplacement();
                    
                    float id = Float.parseFloat(obj.get("id").toString());
                    e.setId((int) id);
                    float capacite = Float.parseFloat(obj.get("capacite").toString());
                    e.setId((int) id);

                e.setLieu(obj.get("lieu").toString());
                e.setCapacite((int) capacite);
                    
                //inserer les données dans une liste
                result.add(e);
                }                
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
     public void AjouterEmplacement(Emplacement e ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "AjouterEmplacement?lieu="+e.getLieu()+"&capacite="+e.getCapacite();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new EmplacementForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
        public void Update(Emplacement e ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "updateEmplacement?id="+e.getId()+ "&lieu="+e.getLieu()+"&capacite="+e.getCapacite();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new EmplacementForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
        
        public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "deleteEmplacement/"+id;
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
}
