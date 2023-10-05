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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Abonnement;
import com.mycompany.myapp.AjoutAbonnementForm;
import com.mycompany.myapp.AbonnementForm;
import com.mycompany.utils.PageWeb;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map; 
import java.util.List; 
import com.mycompany.utils.PageWeb;
import java.io.IOException;
/**
 *
 * @author Lenovo
 */
public class ServiceAbonnement {
    public ArrayList<Abonnement> Abonnements;
  public static boolean resultOk;
    public static ServiceAbonnement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAbonnement() {
        req = new ConnectionRequest();
    }

    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }
public ArrayList<Abonnement> parseAbonnements(String jsonText) {
        try {
            Abonnements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> AbonnementListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) AbonnementListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Abonnement h = new Abonnement();
             
                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);
    
                h.setType(obj.get("type").toString());
                h.setDateD(obj.get("dated").toString());
                h.setDateF(obj.get("datef").toString());
               float prix = Float.parseFloat(obj.get("prix").toString());
                h.setPrix((int) prix);
                Abonnements.add(h);
            }

        } catch (IOException ex) {

        }
        return Abonnements;
    } 
 public ArrayList<Abonnement> getAllAbonnements() {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"abonnement/afficherA";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Abonnements = parseAbonnements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Abonnements;
    }
 public void Add(Abonnement p ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "abonnement/addA?type="+p.getType()+"&dated="+p.getDateD()+"&datef="+p.getDateF()+"&prix="+p.getPrix();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
       new AbonnementForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
  public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "abonnement/deleteA/"+id;
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
   public void Update(Abonnement p ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "abonnement/updateA?id="+p.getId()+ "&type="+p.getType()+"&dated="+p.getDateD()+"&datef="+p.getDateF()+"&prix="+p.getPrix();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new AbonnementForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
}
