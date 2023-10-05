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
import com.mycompany.entity.Evenement;
import com.mycompany.entity.Sujet;
import com.mycompany.myapp.AffichageSujet;
import com.mycompany.myapp.EvenementForm;
import com.mycompany.myapp.SessionManager;
import com.mycompany.myapp.SujetForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author user
 */
public class ServiceSujet {
     public ArrayList<Sujet> sujets;

    public static ServiceSujet instance = null;
    private ConnectionRequest req;

    public static ServiceSujet getInstance() {
        if (instance == null) {
            instance = new ServiceSujet();
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

    
    public ServiceSujet() {
        req = new ConnectionRequest();
    }

    //affichage des produits :
    /* public ArrayList<Sujet> AffichageSujet() {
        ArrayList<Sujet> result = new ArrayList<>();

        String url = PageWeb.BASE_URL + "sujet/display";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapProduit = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapProduit.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Sujet sujet = new Sujet();
                        sujet.setId(Integer.parseInt(obj.get("id").toString()));
                        sujet.setNbReponses(Integer.parseInt(obj.get("nbReponses").toString()));
                        sujet.setNbVues(Integer.parseInt(obj.get("nbVues").toString()));
                        sujet.setTitre(obj.get("titre").toString());
                        sujet.setContenu(obj.get("contenu").toString());

                        result.add(sujet);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }*/
    public ArrayList<Sujet> parseSujet(String jsonText) {
        ArrayList<Sujet> sujets = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> SujetListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) SujetListJson.get("root");
            for (Map<String, Object> obj : list) {
                Sujet sujet = new Sujet();
                float id = Float.parseFloat(obj.get("id").toString());
                sujet.setId((int) id);
                float nrf = Float.parseFloat(obj.get("nbReponses").toString());
                sujet.setNbReponses((int) nrf);
                float nbv = Float.parseFloat(obj.get("nbVues").toString());
                sujet.setNbVues((int) nbv);
                sujet.setTitre(obj.get("titre").toString());                
                sujet.setContenu(obj.get("contenu").toString());
                sujets.add(sujet);
            }

        } catch (IOException ex) {

        }
        return sujets;
    }

    public ArrayList<Sujet> getAllSujet() {

        req = new ConnectionRequest();
        String url = PageWeb.BASE_URL + "sujet/display";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sujets = parseSujet(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return sujets;
    }

    //afficher les détails d'un produits
    public Sujet DetailSujet(int id, Sujet sujet) {
        String url = PageWeb.BASE_URL + "display/" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((event) -> {

            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                sujet.setContenu(obj.get("contenu").toString());
                sujet.setTitre(obj.get("titre").toString());
                sujet.setId(Integer.parseInt(obj.get("id").toString()));
                sujet.setNbReponses(Integer.parseInt(obj.get("nbReponses").toString()));
                sujet.setNbVues(Integer.parseInt(obj.get("nbVues").toString()));

            } catch (IOException ex) {
                System.out.println("error related to SQL : (" + ex.getMessage());
            }
            System.out.println("data === " + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);

        return sujet;
    }

    public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "sujet/deletemobile/" + id;
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }

    public void Add(Sujet c, Form previous, Resources res) {
        String url = PageWeb.BASE_URL + "sujet/ajoutmobile?titre=" + c.getTitre() + "&contenu=" + c.getContenu() + "&idUser=" + SessionManager.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        new SujetForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
     public void Addfront(Sujet c, Form previous, Resources res) {
        String url = PageWeb.BASE_URL + "sujet/ajoutmobile?titre=" + c.getTitre() + "&contenu=" + c.getContenu() + "&idUser=" + SessionManager.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        new AffichageSujet(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void Update(Sujet c, Form previous, Resources res) {
        String url = PageWeb.BASE_URL + "sujet/modifiermobile?id="+c.getId()+"&titre=" + c.getTitre() + "&contenu=" + c.getContenu() ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });

        new SujetForm(previous, res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

}
