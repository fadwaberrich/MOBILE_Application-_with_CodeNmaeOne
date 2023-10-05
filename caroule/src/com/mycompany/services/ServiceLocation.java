/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.mycompany.entity.Location;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.p;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.utils.PageWeb;
import java.io.IOException;

import com.mycompany.myapp.LocationForm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceLocation {
    
    public ArrayList<Location> Locations;
    public static ServiceLocation instance=null;
    public boolean resultOk; 
    private ConnectionRequest req;
    
    public ServiceLocation(){
        req= new ConnectionRequest();
        
    }
    
    public static ServiceLocation getInstance(){
        if(instance ==null){
            instance = new ServiceLocation();
        }
        return instance;
    }
    
    public boolean addLocation(Location l){
       // +"moyenne="+"null"+"&" +"net="+ "null"+
        String url = PageWeb.BASE_URL + "espritApi/newLocation"+"?"+"Date="+l.getDate()+ "&" +"Heure="+ l.getHeure()+"&"+"Duree="+ l.getDuree()+"&"+"IdUser="+ l.getIdUser()+"&"+"IdAbonnement="+ l.getIdAbonnement();
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
             resultOk= req.getResponseCode()== 200; 
              req.removeResponseListener(this);
              
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(req);
    
        return resultOk;
      
    }
         public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "espritApi/deleteLocation/"+id;
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
    }
     
  public boolean Update(Location l ) {
     String url = PageWeb.BASE_URL + "espritApi/updateLocation?id="+l.getId()+ "&Date="+l.getDate()+"&Heure="+l.getHeure()+"&Duree="+l.getDuree()+"&"+"IdUser="+ l.getIdUser()+"&"+"IdAbonnement="+ l.getIdAbonnement();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
           
            req.removeResponseListener(this);
        }
    });
    

    NetworkManager.getInstance().addToQueueAndWait(req);
return resultOk;
}
  
  
  
  
  public ArrayList<Location> parseLocations(String jsonText) {
        try {
            Locations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> LocationListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) LocationListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Location h = new Location();
             /*  float nb_participants = Float.parseFloat(obj.get("nb_participants").toString());
                float nb_places = Float.parseFloat(obj.get("nb_places").toString());*/
                float id = Float.parseFloat(obj.get("id").toString());
                h.setId((int) id);
    
                h.setDate(((String)(obj.get("Date").toString())));
              h.setHeure(((String)(obj.get("Heure").toString())));
               float Duree = Float.parseFloat(obj.get("Duree").toString());
               
              //  float idAbonnement = Float.parseFloat(obj.get("idAbonnement").toString());
                //   h.setIdAbonnement((int)idAbonnement);
               //  float idUser= Float.parseFloat(obj.get("idUser").toString());
               //  h.setIdUser((int)idUser);
               // h.setActivite((List<Activite>) obj.get("activite"));
               // h.setNb_participants((int) nb_participants);
              // h.setNb_places((int) nb_places);
                Locations.add(h);
            }

        } catch (IOException ex) {

        }
        return Locations;
    } 
 /*  public ArrayList<Location> parseLocation(String jsonText){
       try{
           Locations= new ArrayList<>();
           JSONParser j = new JSONParser();
           Map<String, Object> LocationListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List<Map<String,Object>> list= (List<Map<String,Object>>) LocationListJson.get("root");
           for ( Map<String,Object> obj : list){
               Location l= new Location();
               float id = Float.parseFloat(obj.get("id").toString());
               l.setId((int)id);
               l.setDate(((String)(obj.get("Date").toString())));
               l.setHeure(((String)(obj.get("Heure").toString())));
               float Duree = Float.parseFloat(obj.get("duree").toString());
               
                 float idAbonnement = Float.parseFloat(obj.get("idAbonnement").toString());
                   l.setIdAbonnement((int)idAbonnement);
                 float idUser= Float.parseFloat(obj.get("idUser").toString());
                 l.setIdUser((int)idUser);
             
               Locations.add(l);
               //
           }   
       }catch(IOException ex){
                   
                   }
           return Locations;

       
   } */
    
  /*  public ArrayList<Location> getAllLocations(){
        String url = PageWeb.BASE_URL + "espritApi/allLocation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Location = parseLocation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Location;
    }*/
  public ArrayList<Location> getAllLocations() {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"espritApi/allLocation";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Locations = parseLocations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Locations;
    }
 
   
    }


