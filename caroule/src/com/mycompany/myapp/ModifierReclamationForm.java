/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

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
public class ModifierReclamationForm extends Form {
    
     public ModifierReclamationForm(Resources res,Reclamation r)  {
                super("Reclamation", BoxLayout.y());
                
                   TextField objet = new TextField(r.getObjet(), "objet", 20, TextArea.TEXT_CURSOR);
                
                 TextField description = new TextField(r.getDescription(), "description", 20, TextArea.EMAILADDR);
                 
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste Reclamation"); 
                  setVisible(true);
                  
                  modif.addActionListener(m
                                -> {

                          
                            if (objet.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                          

                            } 
                            
                            
                            else if (description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date fin ", "OK", null);

                            } 
                            
                            else {
                                r.setObjet(objet.getText());
                                r.setDescription(description.getText());
                               // r.setLieu(lieu.getText());
                               
                               ServiceReclamation sp = new ServiceReclamation();
                                Form previous = null;
                               sp.Update(r, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);
           //     ConnectionRequest cnreq = new ConnectionRequest();
            //    cnreq.setPost(false);
             
            //    NetworkManager.getInstance().addToQueueAndWait(cnreq);
               }
           });
            this.add(objet).add(description).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(m->new ReclamationForm(pre,res).show());
      
     }     
        
                  
}