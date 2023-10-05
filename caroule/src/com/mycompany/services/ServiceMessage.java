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
import com.mycompany.entity.Message;
import com.mycompany.entity.Sujet;
import com.mycompany.myapp.DetailSujet;
import com.mycompany.myapp.MessageForm;
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
public class ServiceMessage {

    public ArrayList<Message> messages;

    public static ServiceMessage instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceMessage() {
        req = new ConnectionRequest();
    }

    public static ServiceMessage getInstance() {
        if (instance == null) {
            instance = new ServiceMessage();
        }
        return instance;
    }

    public String[] splitvirgule(String str) {
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

    public ArrayList<Message> parseMessage(String jsonText) {
        messages = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> MessageListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) MessageListJson.get("root");
            for (Map<String, Object> obj : list) {

                Message m = new Message();
                float id = Float.parseFloat(obj.get("id").toString());
                m.setId((int) id);
                System.out.println(obj);
                m.setContenu(obj.get("contenu").toString());
                m.setDate(obj.get("Date").toString());
                messages.add(m);
                System.out.println(messages);
            }

        } catch (IOException ex) {

        }
        return messages;
    }

    public ArrayList<Message> getAllMessage(int sujet) {

        req = new ConnectionRequest();
        String url = PageWeb.BASE_URL + "message/display/" + sujet;
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                messages = parseMessage(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return messages;
    }

    //affichage back
    public ArrayList<Message> afficherMessage() {
        ArrayList<Message> result = new ArrayList<>();

        String url = PageWeb.BASE_URL + "message/affichage";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapMessage = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapMessage.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Message m = new Message();
                        m.setId((int)Float.parseFloat(obj.get("id").toString()));
                        m.setContenu(obj.get("contenu").toString());
                        // m.sletIdUser((int) Float.parseFloat(obj.get("idUser").toString()));

                      /*  Map<String, String> map = new HashMap<>();
                        String usertomap = obj.get("idUser").toString().substring(1, obj.get("idUser").toString().length() - 1);
                        System.out.println(usertomap);

                        for (String keyValue : splitvirgule(usertomap)) {
                            String[] key = splitegal(keyValue);
                            map.put(key[0], key[1]);
                        }*/
//                         m.setIdUser((int) Float.parseFloat(map.get("id")));

                        result.add(m);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    //delete
    public boolean delete(int id) {
        String url = PageWeb.BASE_URL + "message/deletemobile/" + id;
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
    //ajout

    public void Add(Message m, Form previous, Resources res) {
        String url = PageWeb.BASE_URL + "message/ajoutmobile?contenu=" + m.getContenu() + "&idSujet=" + m.getIdSujet() + "&idUser=" + m.getIdUser();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        new MessageForm(previous, res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void AddFront(Message m, Form previous, Resources res , Sujet sujet) {
        String url = PageWeb.BASE_URL + "message/ajoutmobile?contenu=" + m.getContenu() + "&idSujet=" + m.getIdSujet() + "&idUser=" + SessionManager.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        new DetailSujet(sujet,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    //update

    public void Update(Message m, Form previous, Resources res) {
        String url = PageWeb.BASE_URL + "message/modifiermobile?id=" + m.getId() + "&contenu=" + m.getContenu();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });

        new MessageForm(previous, res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
}
