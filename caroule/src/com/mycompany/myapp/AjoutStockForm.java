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
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceStock;

/**
 *
 * @author PC
 */
public class AjoutStockForm extends Form{
    

    
     public AjoutStockForm(Resources res)  {
                super("Stock", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                   TextField libelle = new TextField("", "libelle Stock", 20, TextArea.TEXT_CURSOR);
                
                TextField prix = new TextField("", "prix", 20, TextArea.TEXT_CURSOR);
                
                 TextField quantite = new TextField("", "quantite", 20, TextArea.EMAILADDR);
                 
                 TextField disponibilite = new TextField("", "disponibilite", 20, TextArea.TEXT_CURSOR);
                 
                 TextField IdProduit = new TextField("", "IdProduit", 20, TextArea.NUMERIC);
                 
                 TextField emplacement = new TextField("", "emplacement", 20, TextArea.NUMERIC);
                 
                 

                 
        //buttons
         Button save = new Button("Ajouter");
        Button b11=new Button("liste des Stocks"); 
         setVisible(true);
         
         //data
         save.addActionListener(l
                                -> {

                          
                            if (libelle.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);

                            } else if (prix.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

                            }
                            else if (quantite.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } 
                              else if (disponibilite.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de domaine ", "OK", null);

                            }
                                  else if (IdProduit.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de prix ", "OK", null);

                            }
                            
                            else {       
                                Stock s = new Stock();
                                s.setLibelle(libelle.getText());
                                s.setPrix(Integer.valueOf(prix.getText()));
                                s.setQuantite(Integer.valueOf(quantite.getText()));
                                s.setDisponibilite(disponibilite.getText());
                                s.setEmplacement(Integer.valueOf(emplacement.getText()));
                                s.setIdProduit(Integer.valueOf(IdProduit.getText()));
                                
                                ServiceStock sp = new ServiceStock();
                                Form previous = null;
                               sp.AjouterStock(s, previous,res);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);

                                }
                            });
         this.add(libelle).add(prix).add(quantite).add(disponibilite).add(IdProduit).add(emplacement).add(save);
         
            add(b11);
            Form pre = null;
            b11.addActionListener(e -> new StockForm(pre,res).show());
     }
}
