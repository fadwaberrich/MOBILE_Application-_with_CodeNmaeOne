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
import com.mycompany.entity.Emplacement;
import com.mycompany.services.ServiceEmplacement;

/**
 *
 * @author PC
 */
public class ModifierEmplacementForm extends Form {
    
     public ModifierEmplacementForm(Resources res,Emplacement e)  {
                super("Emplacement", BoxLayout.y());
              
                   TextField Lieu = new TextField(e.getLieu(), "Lieu", 20, TextArea.TEXT_CURSOR);
                
                TextField Capacite = new TextField(String.valueOf(e.getCapacite()), "Capacite ", 20, TextArea.TEXT_CURSOR);
                 
                TextField Stock = new TextField(String.valueOf(e.getStock()), "Stock ", 20, TextArea.TEXT_CURSOR);
                
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste Emplacement"); 
                  setVisible(true);
                  
                  modif.addActionListener(l
                                -> {

                          
                            if (Lieu.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                          

                            } else if (Capacite.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date debut ", "OK", null);

                            }
                            
                            else {
                                e.setLieu(Lieu.getText());
                                e.setCapacite(Integer.valueOf(Capacite.getText()));
                                //e.setStock(Stock.getText());


                               
                                ServiceEmplacement sp = new ServiceEmplacement();
                                Form previous = null;
                               sp.Update(e, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);

               }
           });
            this.add(Lieu).add(Capacite).add(Stock).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(l->new EmplacementForm(pre,res).show());
      
     } 
}