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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Emplacement;
import com.mycompany.services.ServiceEmplacement;

/**
 *
 * @author PC
 */
public class AjoutEmplacementForm extends Form{
    
    
     String file ;
  
     
          Resources theme;
                      Resources themee = UIManager.initFirstTheme("/theme");
    
     public AjoutEmplacementForm(Resources res)  {
         
                super("Emplacement", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
      
                 TextField capacite = new TextField("", "Capacite", 20, TextArea.TEXT_CURSOR);
                 
                 TextField lieu = new TextField("", "lieu", 20, TextArea.TEXT_CURSOR);
                 
             //boutons    
       Button save = new Button("Ajouter");
       Button b9=new Button("liste Emplacement"); 
         setVisible(true);
         
         //appel
         save.addActionListener(l
                                -> {

                          
                            if (capacite.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);
                                
                            } else if (lieu.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

                            }
                            
                            else {
                                          
                                          
                                Emplacement e = new Emplacement();
                                 e.setLieu(lieu.getText());
                                 e.setCapacite(Integer.valueOf(capacite.getText()));
                                // p.setNb_participants(Integer.valueOf(nb_participants.getText()));
                               // p.setNb_places(Integer.valueOf(nb_places.getText()));
                               
                                ServiceEmplacement sp = new ServiceEmplacement();
                                Form previous = null;
                               sp.AjouterEmplacement(e, previous,res);
                           

                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                            
                                                                    
                            }
           });
          this.add(lieu).add(capacite).add(save);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b9);
         Form pre = null;
 b9.addActionListener(e -> new EmplacementForm(pre,res).show());                 
     }
}

