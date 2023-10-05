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
import com.mycompany.entity.Produit;
import com.mycompany.entity.Stock;
import com.mycompany.services.ServiceStock;

/**
 *
 * @author PC
 */
public class ModifierStockForm extends Form {
    
     public ModifierStockForm(Resources res,Stock s)  {
                super("Stock", BoxLayout.y());
              
                   TextField libelle = new TextField(s.getLibelle(), "libelle", 20, TextArea.TEXT_CURSOR);
                
                TextField prix = new TextField(String.valueOf(s.getPrix()), "prix", 20, TextArea.TEXT_CURSOR);
                
                 TextField quantite = new TextField(String.valueOf(s.getQuantite()), "quantite", 20, TextArea.EMAILADDR);
                 
                 TextField dispo = new TextField(s.getDisponibilite(), "dispo", 20, TextArea.TEXT_CURSOR);
                 
                  Button modif = new Button("modifier");
                  Button b12=new Button("liste Stock"); 
                  setVisible(true);
                  
                  modif.addActionListener(l
                                -> {

                          
                            if (libelle.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                          

                            } else if (prix.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date debut ", "OK", null);

                            }
                            
                            
                            else if (quantite.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Date fin ", "OK", null);

                            } 
                              else if (dispo.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lieu ", "OK", null);

                            }
                            
                            else {
                                  s.setId(s.getId());
                                s.setLibelle(libelle.getText());
                                s.setPrix(Integer.valueOf(prix.getText()));
                                s.setQuantite(Integer.valueOf(quantite.getText()));
                                s.setDisponibilite(dispo.getText());
                                
                               
                                ServiceStock sp = new ServiceStock();
                                Form previous = null;
                               sp.Update(s, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);
               }
           });
            this.add(libelle).add(prix).add(quantite).add(dispo).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b12);
         Form pre = null;
        b12.addActionListener(l->new StockForm(pre,res).show());
      
     }     
        
                  
}