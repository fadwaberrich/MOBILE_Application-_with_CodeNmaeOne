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
import com.mycompany.entity.Achat;
import com.mycompany.entity.Produit;
import com.mycompany.myapp.AchatForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServiceAchat {
                   

     public ArrayList<Achat> Achats;

    public static ServiceAchat instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAchat() {
        req = new ConnectionRequest();
    }

    public static ServiceAchat getInstance() {
        if (instance == null) {
            instance = new ServiceAchat();
        }
        return instance;
    }
     //affichage des Emplacement
    public ArrayList<Achat> parseAchats(String jsonText) {
        try {
            Achats = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> AchatListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) AchatListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Achat h = new Achat();
            
                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);
    
                h.setNomClient(obj.get("nomClient").toString());
                h.setDate(obj.get("date").toString());
                
         
               // float nb_participants = Float.parseFloat(obj.get("nb_participants").toString());
                //h.setNb_participants((int) obj.get("nb_participants"));
                //float nb_places = Float.parseFloat(obj.get("nb_places").toString());
                //h.setNb_places((int)obj.get("nb_participants"));

            
               

                Achats.add(h);
            }

        } catch (IOException ex) {

        }
        return Achats;
    } 
    public ArrayList<Achat> getAllAchats() {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"achat/afficherAchat";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Achats = parseAchats(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Achats;
    }
     public void add(Achat s ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "achat/ajoutmobileachat?idUser="+s.getIdUser()+"&idProduit="+s.getIdProduit()+"&nomClient="+s.getNomClient()+"&numeroClient="+s.getNumeroClient()+"&date="+s.getDate();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new AchatForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
     
      public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "achat/deletemobileachat?id=" +id;
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
     
     public void Update(Achat s ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "achat/modifiermobileachat?id="+s.getId()+"&nomClient="+s.getNomClient()+"&numeroClient="+s.getNumeroClient();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new AchatForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
        
       
}
