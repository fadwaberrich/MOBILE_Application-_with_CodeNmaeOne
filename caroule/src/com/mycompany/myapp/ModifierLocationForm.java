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
import com.mycompany.entity.Location;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceLocation;

public class ModifierLocationForm extends Form {
    
     public ModifierLocationForm(Resources res,Location l)  {
                super("Location", BoxLayout.y());
              
                   TextField Date = new TextField(l.getDate(), "Date", 20, TextArea.TEXT_CURSOR);
                
                TextField Heure = new TextField(l.getHeure(), "Heure ", 20, TextArea.TEXT_CURSOR);
                
                 TextField Duree = new TextField(String.valueOf(l.getDuree()), "Duree", 20, TextArea.TEXT_CURSOR);
                 
               
                 
               
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste location"); 
                  setVisible(true);
                  
                  modif.addActionListener(i
                                -> {

                          
                            if (Date.getText().equals("")) {
                                Dialog.show("Erreur", "Champs vide: ", "OK", null);

                          

                            } else if (Heure.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide! ", "OK", null);

                            }
                               
                            else if (Duree.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide ! ", "OK", null);
                            } 
                            
                            
                            else {
                                l.setDate(Date.getText());
                                l.setHeure(Heure.getText());
                                l.setDuree(Integer.parseInt(Duree.getText()));
                                 
                                 
                               
                               ServiceLocation sl = new ServiceLocation();
                                Form previous = null;
                               sl.Update(l);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);

 
               }
           });
            this.add(Date).add(Heure).add(Duree).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(i->new LocationForm(pre,res).show());
      
     }     
        
                  
}
