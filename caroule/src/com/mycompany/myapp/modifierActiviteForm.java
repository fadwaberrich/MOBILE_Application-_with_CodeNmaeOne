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
import com.mycompany.entity.Activite;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceActivite;
public class modifierActiviteForm extends Form {
    public modifierActiviteForm (Resources res,Activite e)  {
                super("Activite", BoxLayout.y());
              
                   TextField nom = new TextField(e.getNom(), "nom activite ", 20, TextArea.TEXT_CURSOR);
                
                TextField description = new TextField(e.getDescription(), "description activite ", 20, TextArea.TEXT_CURSOR);
                
                 TextField image = new TextField(e.getImage(), "image ", 20, TextArea.EMAILADDR);
                     
                
                
                 
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste activite"); 
                  setVisible(true);
                  
                  modif.addActionListener(l
                                -> {

                          
                            if (nom.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom activite  ", "OK", null);

                          

                            } else if (description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de description activite", "OK", null);

                            }
                            
                            
                            else if (image.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de image activite ", "OK", null);

                            } 
                             
                            
                            else {
                                e.setNom(nom.getText());
                                e.setDescription(description.getText());
                                e.setImage(image.getText()); 
                                 
                               
                               
                               ServiceActivite sp = new ServiceActivite();
                                Form previous = null;
                               sp.Update(e, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);

 ;
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
             
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
               }
           });
            this.add(nom).add(description).add(image).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(l->new ActiviteForm(pre,res).show());
      
     }     
}
