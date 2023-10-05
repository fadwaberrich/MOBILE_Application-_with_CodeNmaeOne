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
import com.mycompany.entity.Favoris;
import com.mycompany.entity.Produit;
import com.mycompany.entity.Stock;
import com.mycompany.myapp.AjoutProduitForm;
import com.mycompany.myapp.ProduitForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map; 
import java.util.List; 

/**
 *
 * @author PC
 */
public class ServiceProduit {
    
public ArrayList<Produit> Produit;

    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
  //parseproduit
    public ArrayList<Produit> parseProduits(String jsonText) {
        try {
            Produit = new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String, Object> ProduitListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ProduitListJson.get("root");
            for(Map<String,Object> obj :list){
           
                Produit p = new Produit();

                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setId((int) id);

                p.setLibelle(obj.get("libelle").toString());
                p.setDescription(obj.get("description").toString());
                p.setType(obj.get("type").toString());
                p.setPrix(prix);
               

              //  h.setDateCreation((Date) Date.parseDate(obj.get("DateCreation").toString()));
               

                Produit.add(p);
            }

        } catch (IOException ex) {

        }
        return Produit;
    }
    
    //affichage des produits :
    public ArrayList<Produit> getAllProduits() {
        
 req = new ConnectionRequest();
        String url = PageWeb.BASE_URL +"display";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produit = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produit;
    }

    
    //afficher les détails d'un produits
    public Produit DetailProduit(int id,Produit prod){
        String url = PageWeb.BASE_URL+"exploreProduit"+id;
        req.setUrl(url);
        
        String str = new String (req.getResponseData());
        req.addResponseListener( ( (event)-> {
            
            JSONParser jsonp =new JSONParser();
            try {
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));  
            
                prod.setLibelle(obj.get("libelle").toString());
                prod.setDescription(obj.get("description").toString());
                prod.setImage(obj.get("image").toString());
                prod.setType(obj.get("type").toString());
                prod.setPrix(Integer.parseInt(obj.get("prix").toString()));
                prod.getFavoris();
            }
            catch(IOException ex){
                System.out.println("error related to SQL : ("+ex.getMessage());
            }
            System.out.println("data === "+str);
        }));
                NetworkManager.getInstance().addToQueueAndWait(req);

                return prod;
    }
        
        //Ajouter un produit
 public void AjouterProduit(Produit p ,Form previous,Resources res ) {
        String url = PageWeb.BASE_URL + "AjouterProduit?libelle="+p.getLibelle()+"&description="+p.getDescription()+"&image="+p.getImage()+"&prix="+p.getPrix()+"&type="+p.getType();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new ProduitForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
 
 //supprimer Prduit

    public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "deleteProd/"+id;
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
    
    //update produit
    public void Update(Produit p ,Form previous,Resources res) {
        String url = PageWeb.BASE_URL + "updateProduit?id="+p.getId()+ "&libelle="+p.getLibelle()+"&image="+p.getImage()+"&description="+p.getDescription()+"&prix="+p.getPrix()+"&type="+p.getType();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        new ProduitForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }

}
