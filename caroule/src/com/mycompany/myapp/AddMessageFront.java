/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Message;
import com.mycompany.entity.Sujet;
import com.mycompany.services.ServiceMessage;
import com.mycompany.services.ServiceSujet;

/**
 *
 * @author user
 */
public class AddMessageFront extends Form {

    public AddMessageFront(Sujet sujet , Resources res) {
        super("Message", BoxLayout.y());
        Button skip = new Button("back");
        skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
        add(skip);
        
        Label contenulabel = new Label("contenu");
        TextField contenu = new TextField("", "contenu", 20, TextArea.TEXT_CURSOR);
        System.out.println("ejzakejzalkazjlejlazk"+ sujet.getId());
        Button save = new Button("Ajouter");
        setVisible(true);

        save.addActionListener(l
                -> {

            /*if (Id.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide  ", "OK", null);

            } else */if (contenu.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide  ", "OK", null);

            } else {
                Message m = new Message();
                m.setContenu(contenu.getText());
                m.setIdSujet(sujet.getId());
                ServiceMessage sm = new ServiceMessage();
                sm.AddFront(m, this, res , sujet);
                Form previous = null;
                Dialog.show("Ajout", "Ajout avec succés", "OK", null);

                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
                
                try {
                    NetworkManager.getInstance().addToQueueAndWait(cnreq);
                } catch (Exception e) { 
                }
                

            }
        });

        this.add(contenulabel).add(contenu).add(save);
    }
}
