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
import com.mycompany.entity.Maintenance;
import com.mycompany.services.ServiceMaintenance;

/**
 *
 * @author PC
 */
public class ModifierMaintenance extends Form {
    
     public ModifierMaintenance(Resources res,Maintenance  e)  {
                super("Maintenance ", BoxLayout.y());
              

                
                TextField adresse = new TextField(e.getAdresse(), "adresse", 20, TextArea.TEXT_CURSOR);
                 
                 TextField desc = new TextField(e.getDescription(), "description", 20, TextArea.TEXT_CURSOR);
                 
                 TextField etat = new TextField(e.getEtat(), "etat", 20, TextArea.TEXT_CURSOR);
                 
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste event"); 
                  setVisible(true);
                  
                  modif.addActionListener(m
                                -> {

                          
                              if (adresse.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de domaine ", "OK", null);

                            }
                              
                               else if (desc.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lien ", "OK", null);

                            } 
                             else if (etat.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lien ", "OK", null);

                            } 
                            else {
                                Maintenance p = new Maintenance();
                                p.setId(e.getId());
                                 p.setDescription(desc.getText());
                                 p.setEtat(etat.getText());
                                 p.setAdresse(adresse.getText());
                               System.out.println(p.getAdresse());
                                ServiceMaintenance sp = new ServiceMaintenance();
                                Form previous = null;
                                
                               sp.Update(p, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);
    //            ConnectionRequest cnreq = new ConnectionRequest();
  //              cnreq.setPost(false);
             
//                NetworkManager.getInstance().addToQueueAndWait(cnreq);
               }
           });
            this.add(desc).add(adresse).add(etat).add(modif);
            
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(m->new MaintenanceForm(pre,res).show());
      
     }     
        
                  
}
