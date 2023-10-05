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
import com.mycompany.entity.Evenement;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceEvenement;

public class ModifierEvenementForm extends Form {
    
     public ModifierEvenementForm(Resources res,Evenement e)  {
                super("Evenement", BoxLayout.y());
              
                   TextField Nom = new TextField(e.getNom(), "Nom Evenement", 20, TextArea.TEXT_CURSOR);
                
                TextField Dated = new TextField(e.getDateD(), "Date debut ", 20, TextArea.TEXT_CURSOR);
                
                 TextField Datef = new TextField(e.getDateF(), "Date fin", 20, TextArea.EMAILADDR);
                 
                 TextField lieu = new TextField(e.getLieu(), "lieu", 20, TextArea.TEXT_CURSOR);
                 
                 TextField type = new TextField(e.getType(), "type", 20, TextArea.NUMERIC);
                 
                 TextField nb_participants = new TextField(String.valueOf(e.getNb_participants()), "nb participants", 20, TextArea.TEXT_CURSOR);
                 
                 TextField nb_places = new TextField(String.valueOf(e.getNb_places()), "nb places", 20, TextArea.TEXT_CURSOR);
                 
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste event"); 
                  setVisible(true);
                  
                  modif.addActionListener(m
                                -> {

                          
                            if (Nom.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                          

                            } else if (Dated.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date debut ", "OK", null);

                            }
                            
                            
                            else if (Datef.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date fin ", "OK", null);

                            } 
                              else if (lieu.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lieu ", "OK", null);

                            }
                                  else if (type.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de type ", "OK", null);

                            }
                                    else if (nb_participants.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nb participants ", "OK", null);

                            }
                                      else if (nb_places.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nb_places ", "OK", null);

                            }
                            
                            else {
                                e.setNom(Nom.getText());
                                e.setDateD(Dated.getText());
                                e.setDateF(Datef.getText());
                                 e.setLieu(lieu.getText());
                                 e.setType(type.getText());
                                 e.setNb_participants(Integer.valueOf(nb_participants.getText()));
                               e.setNb_places(Integer.valueOf(nb_places.getText()));
                               
                               ServiceEvenement sp = new ServiceEvenement();
                                Form previous = null;
                               sp.Update(e, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);
           //     ConnectionRequest cnreq = new ConnectionRequest();
            //    cnreq.setPost(false);
             
            //    NetworkManager.getInstance().addToQueueAndWait(cnreq);
               }
           });
            this.add(Nom).add(Dated).add(Datef).add(lieu).add(type).add(nb_participants).add(nb_places).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(m->new EvenementForm(pre,res).show());
      
     }     
        
                  
}
