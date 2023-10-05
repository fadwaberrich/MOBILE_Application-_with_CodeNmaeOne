/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author Lenovo
 */
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import com.mycompany.entity.Abonnement;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceAbonnement;

public class ModifierAbonnementForm extends Form{
    
     public ModifierAbonnementForm (Resources res,Abonnement e)  {
                super("Abonnement", BoxLayout.y());
              
                   TextField type = new TextField(e.getType(), "type abonnement ", 20, TextArea.TEXT_CURSOR);
                
                TextField Dated = new TextField(e.getDateD(), "Date debut ", 20, TextArea.TEXT_CURSOR);
                
                 TextField Datef = new TextField(e.getDateF(), "Date fin", 20, TextArea.EMAILADDR);
                     
                 TextField prix = new TextField(String.valueOf(e.getPrix()), "prix abonnement ", 20, TextArea.TEXT_CURSOR);
                 
                
                 
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste abonnement"); 
                  setVisible(true);
                  
                  modif.addActionListener(l
                                -> {

                          
                            if (type.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de type abonnement  ", "OK", null);

                          

                            } else if (Dated.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date debut ", "OK", null);

                            }
                            
                            
                            else if (Datef.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date fin ", "OK", null);

                            } 
                              else if (prix.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de prix abonnement  ", "OK", null);

                              }
                            
                            else {
                                e.setType(type.getText());
                                e.setDateD(Dated.getText());
                                e.setDateF(Datef.getText()); 
                                 e.setPrix(Integer.valueOf(prix.getText()));
                               
                               
                               ServiceAbonnement sp = new ServiceAbonnement();
                                Form previous = null;
                               sp.Update(e, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);

 ;
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
             
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
               }
           });
            this.add(type).add(Dated).add(Datef).add(prix).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(l->new AbonnementForm(pre,res).show());
      
     }     
        
}
