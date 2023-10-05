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
import com.mycompany.entity.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author PC
 */
public class AjoutReclamationForm extends Form{
    

    
     public AjoutReclamationForm(Resources res)  {
                super("Reclamation", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                   TextField objet = new TextField("", "objet", 20, TextArea.TEXT_CURSOR);
                
                TextField Date = new TextField("", "date ", 20, TextArea.TEXT_CURSOR);
                
                 TextField description = new TextField("", "description", 20, TextArea.EMAILADDR);
                 
                 TextField idUser = new TextField("", "idUser", 20, TextArea.TEXT_CURSOR);

        Button save = new Button("Ajouter");
       Button b7=new Button("liste Reclamation"); 
         setVisible(true);
         

           save.addActionListener(l
                                -> {

                          
                            if (objet.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);

                            } else if (Date.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

                            }
                            
                            else if (description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } 
                              else if (idUser.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de domaine ", "OK", null);

                            }
                            
                            else {
                                          
                                          
                                Reclamation p = new Reclamation();
                                p.setObjet(objet.getText());
                                p.setDate(Date.getText());
                                p.setDescription(description.getText());
                                
                               ServiceReclamation sp = new ServiceReclamation();
                                Form previous = null;
                               sp.Add( previous,res,p);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);

                                                               
                            }
           });
        

        
          this.add(objet).add(description).add(Date).add(idUser).add(save);
        
        add(b7);
         Form pre = null;
 b7.addActionListener(e -> new ReclamationForm(pre,res).show());                 
     }
}
