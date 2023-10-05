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
import com.mycompany.entity.Reclamation;
import com.mycompany.myapp.ReclamationForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author PC
 */
public class ServiceReclamation {
    public ArrayList<Reclamation> Reclamations;
     public boolean resultOK;
     public static boolean resultOk;

    public static ServiceReclamation instance = null;
    private ConnectionRequest req;

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public String[] splitvirgule(String str) {
        
        System.out.println(str);
        ArrayList<String> splitArray = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(str, ", ");//split by commas
        while (arr.hasMoreTokens()) {
            splitArray.add(arr.nextToken());
        }
        return splitArray.toArray(new String[splitArray.size()]);
    }

    public String[] splitegal(String str) {
        ArrayList<String> splitArray = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(str, "=");//split by commas
        while (arr.hasMoreTokens()) {
            splitArray.add(arr.nextToken());
        }
        return splitArray.toArray(new String[splitArray.size()]);
    }

    
    public ServiceReclamation() {
        req = new ConnectionRequest();
    }
//parser
        public ArrayList<Reclamation> parseReclamation(String jsonText) {
        ArrayList<Reclamation> Reclamations = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ReclamationListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation Reclamation = new Reclamation();
                
                float id = Float.parseFloat(obj.get("id").toString());
                Reclamation.setId((int) id);
                
               /* float Id_produit_id = Float.parseFloat(obj.get("id_produit_id").toString());
                Maintenance.setId_produit_id((int) Id_produit_id);
                
                float Reclamation_id = Float.parseFloat(obj.get("reclamation_id").toString());
                Maintenance.setReclamation_id((int) Reclamation_id);
                
                float Relation_id = Float.parseFloat(obj.get("relation_id").toString());
                Maintenance.setRelation_id((int) Reclamation_id);*/
                
                Reclamation.setDescription(obj.get("description").toString());                
                //Maintenance.setDate_debut(obj.get("date_debut").toString());
               //Maintenance.setDate_fin(obj.get("date_fin").toString());
                Reclamation.setDate(obj.get("date").toString());                
                Reclamation.setObjet(obj.get("objet").toString());
                
                Reclamations.add(Reclamation);
            }

        } catch (IOException ex) {

        }
        return Reclamations;
    }
        public ArrayList<Reclamation> getAllReclamation() {

        req = new ConnectionRequest();
        String url = PageWeb.BASE_URL + "reclamation/afficherRec";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamations = parseReclamation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclamations;
    }

    
    
    
     public void Add(Form previous,Resources res , Reclamation p) {
        String url = PageWeb.BASE_URL + "reclamation/ajoutRec?objet=" + p.getObjet()+ "&description=" + p.getDescription()+"&date="+ p.getDate()+"&idUser="+ 9;
         System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        //new MaintenanceForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
     //delete
       public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "reclamation/deleteRec/"+id;
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
       
          public void Update(Reclamation p ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "reclamation/modifierRec?id="+ p.getId() + "&objet=" + p.getObjet()+ "&description=" + p.getDescription();
        req.setUrl(url);
        System.out.println("===>" + url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new ReclamationForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
}
