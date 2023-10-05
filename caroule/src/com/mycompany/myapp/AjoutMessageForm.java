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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Message;
import com.mycompany.services.ServiceMessage;

/**
 *
 * @author PC
 */
public class AjoutMessageForm extends Form{
    

    
     public AjoutMessageForm(Resources res)  {
                super("Message", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                
                TextField contenu = new TextField("", "Nom Message", 20, TextArea.TEXT_CURSOR);
                
                
                TextField idSujet = new TextField("", "Sujet", 20, TextArea.EMAILADDR);
                 
                TextField idUser = new TextField("", "User", 20, TextArea.TEXT_CURSOR);
                          

        Button save = new Button("Ajouter");
       Button b7=new Button("liste Message"); 
         setVisible(true);

           save.addActionListener(l
                                -> {

                          
                            if (contenu.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);
                            }
                            else if (idSujet.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } else if (idUser.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

                            }
                            
                            else {
                                          
                                          
                                Message m = new Message();
                                m.setContenu(contenu.getText());
                                m.setIdSujet(Integer.valueOf(idSujet.getText()));
                                m.setIdUser(Integer.valueOf(idUser.getText()));

                                ServiceMessage sp = new ServiceMessage();
                                Form previous = null;
                               sp.Add(m, previous,res);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                                 
                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);

                NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                          
                            }
           });
        
        
        
          this.add(contenu).add(idSujet).add(idUser).add(save);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b7);
         Form pre = null;
 b7.addActionListener(e -> new MessageForm(pre,res).show());                 
     }
}