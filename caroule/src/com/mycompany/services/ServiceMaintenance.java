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
import com.mycompany.entity.Maintenance;
import com.mycompany.entity.Sujet;
import com.mycompany.myapp.MaintenanceForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
/**
 *
 * @author PC
 */
public class ServiceMaintenance {
     public ArrayList<Maintenance> Maintenances;
public boolean resultOK;
  public static boolean resultOk;

    public static ServiceMaintenance instance = null;
    private ConnectionRequest req;

    public static ServiceMaintenance getInstance() {
        if (instance == null) {
            instance = new ServiceMaintenance();
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

    
    public ServiceMaintenance() {
        req = new ConnectionRequest();
    }
//parser
        public ArrayList<Maintenance> parseMaintenance(String jsonText) {
        ArrayList<Maintenance> Maintenances = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> MaintenanceListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) MaintenanceListJson.get("root");
            for (Map<String, Object> obj : list) {
                Maintenance Maintenance = new Maintenance();
                float id = Float.parseFloat(obj.get("id").toString());
                Maintenance.setId((int) id);
                
               /* float Id_produit_id = Float.parseFloat(obj.get("id_produit_id").toString());
                Maintenance.setId_produit_id((int) Id_produit_id);
                
                float Reclamation_id = Float.parseFloat(obj.get("reclamation_id").toString());
                Maintenance.setReclamation_id((int) Reclamation_id);
                
                float Relation_id = Float.parseFloat(obj.get("relation_id").toString());
                Maintenance.setRelation_id((int) Reclamation_id);*/
                
                Maintenance.setAdresse(obj.get("adresse").toString());                
                //Maintenance.setDate_debut(obj.get("date_debut").toString());
               //Maintenance.setDate_fin(obj.get("date_fin").toString());
                Maintenance.setDescription(obj.get("description").toString());                
                Maintenance.setEtat(obj.get("etat").toString());
                Maintenance.setAdresse(obj.get("adresse").toString());
                
                Maintenances.add(Maintenance);
            }

        } catch (IOException ex) {

        }
        return Maintenances;
    }
        public ArrayList<Maintenance> getAllMaintenance() {

        req = new ConnectionRequest();
        String url = PageWeb.BASE_URL + "maintenance/displayall";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Maintenances = parseMaintenance(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Maintenances;
    }

    
    
    
     public void Add(Form previous,Resources res , Maintenance p) {
        String url = PageWeb.BASE_URL + "maintenance/ajoutermaintenance?adresse=" + p.getAdresse()+ "&description=" + p.getDescription()+ "&etat=" + p.getEtat() + "&relation_id=" 
                + p.getRelation_id() + "&reclamation=" + p.getReclamation_id() + "&idProduit="+ p.getId_produit_id() + "&idUser=" + p.getIdUser();
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
        String url = PageWeb.BASE_URL + "maintenance/deleteMain/"+id;
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
       
          public void Update(Maintenance p ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "maintenance/modifierMain?id="+ p.getId() + "&adresse=" + p.getAdresse()+ "&description=" + p.getDescription()+ "&etat=" + p.getEtat();
        req.setUrl(url);
        System.out.println("===>" + url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new MaintenanceForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
}
