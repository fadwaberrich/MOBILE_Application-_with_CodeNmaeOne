/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;


import com.mycompany.entity.Activite;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ActiviteForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map; 
import java.util.List; 
 
/**
 *
 * @author Lenovo
 */
public class ServiceActivite {
   public ArrayList<Activite> Activites;

    public static ServiceActivite instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceActivite() {
        req = new ConnectionRequest();
    }

    public static ServiceActivite getInstance() {
        if (instance == null) {
            instance = new ServiceActivite();
        }
        return instance;
    }
public ArrayList<Activite> parseActivites(String jsonText) {
        try {
            Activites = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ActiviteListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ActiviteListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Activite h = new Activite();
              /*  float nb_participants = Float.parseFloat(obj.get("nb_participants").toString());
                float nb_places = Float.parseFloat(obj.get("nb_places").toString());*/
                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);

                h.setNom(obj.get("nom").toString());
                h.setDescription(obj.get("description").toString());
                h.setImage(obj.get("image").toString());
               // float IdEvenement = Float.parseFloat(obj.get("IdEvenement").toString());
              //  h.setIdEvenement(IdEvenement);

               

                Activites.add(h);
            }

        } catch (IOException ex) {

        }
        return Activites;
    } 
 public ArrayList<Activite> getAllActivites() {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"activite/afficherActt";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Activites = parseActivites(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Activites;
    }
  public ArrayList<Activite> getallActivites(int event) {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"activite/afficherAct?event=" + event;
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Activites = parseActivites(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Activites;
    }
 public void Add(Activite a ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "activite/addAct?nom="+a.getNom()+"&description="+a.getDescription()+"&image="+a.getImage()+"&idEvenement="+a.getIdEvenement();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new ActiviteForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
   public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "activite/deleteAct/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }
      public void Update(Activite a ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "activite/updateAct?id="+a.getId()+"&nom="+a.getNom()+"&description="+a.getDescription()+"&image="+a.getImage();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new ActiviteForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
}
