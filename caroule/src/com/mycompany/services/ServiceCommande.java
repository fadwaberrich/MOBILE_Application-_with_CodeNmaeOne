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
import com.mycompany.entity.Produit;
import com.mycompany.entity.Commande;
import com.mycompany.myapp.AchatForm;
import com.mycompany.utils.PageWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import com.mycompany.myapp.AjoutCommande;
import com.mycompany.myapp.CommandeFrom;
import com.mycompany.myapp.SessionManager;
import com.mycompany.myapp.SujetForm;

/**
 *
 * @author Ahmed Elmoez
 */
public class ServiceCommande {

    public ArrayList<Commande> Commandes;

    public static ServiceCommande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    public ServiceCommande() {
        req = new ConnectionRequest();
    }

    //affichage des Commandes :
    //affichage des Emplacement
    public ArrayList<Commande> parseAchats(String jsonText) {
        try {

            System.out.println(jsonText);
            Commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> AchatListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) AchatListJson.get("root");
            for (Map<String, Object> obj : list) {

                Commande h = new Commande();

                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                h.setId((int) id);

                //  float idUser = Float.parseFloat(obj.get("IdUser").toString());
                //   h.setIdUser((int) idUser);
                float nbProduits = Float.parseFloat(obj.get("nbProduits").toString());
                h.setNbProduits((int) nbProduits);
                System.out.println(nbProduits);

                // float nb_participants = Float.parseFloat(obj.get("nb_participants").toString());
                //h.setNb_participants((int) obj.get("nb_participants"));
                //float nb_places = Float.parseFloat(obj.get("nb_places").toString());
                //h.setNb_places((int)obj.get("nb_participants"));
                Commandes.add(h);
            }

        } catch (IOException ex) {

        }
        return Commandes;
    }

    //affichage des Emplacement
    public ArrayList<Commande> getAllCommandes() {

        req = new ConnectionRequest();
        String url = PageWeb.BASE_URL + "commande/afficherCommande";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commandes = parseAchats(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Commandes;
    }

    public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "commande/deletemobilecomm/" + id;
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

    public void Add(Commande c, Form previous, Resources res) { 
        String url = PageWeb.BASE_URL + "commande/ajoutMobilecomm?NbProduits=" + c.getNbProduits() + "&idProduit=" + c.getIdProduit() + "&idUser=" + SessionManager.getId();
        System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        new CommandeFrom(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void Update(Commande c, Form previous, Resources res) {
        String url = PageWeb.BASE_URL + "commande/modifiermobilecomm?NbProduits=" + c.getNbProduits() + "&idProduit=" + c.getIdProduit() + "&id=" + c.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });

        new CommandeFrom(previous, res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

}
