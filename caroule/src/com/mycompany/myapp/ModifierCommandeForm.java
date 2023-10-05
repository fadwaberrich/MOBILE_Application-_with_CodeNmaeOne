/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
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
import com.mycompany.entity.Commande;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceCommande;


/**
 *
 * @author Ahmed Elmoez
 */
public class ModifierCommandeForm extends Form {
    
     public ModifierCommandeForm(Resources res,Commande c)  {
                super("Commande", BoxLayout.y());
              
                // TextField Id = new TextField(String.valueOf(c.getId()), "Numero De Commande", 20, TextArea.TEXT_CURSOR);
                 TextField nbProduits = new TextField(String.valueOf(c.getNbProduits()), "Nombre De Produit", 20, TextArea.TEXT_CURSOR);
                 
                  Button modif = new Button("modifier");
                  Button b8=new Button("My Commande"); 
                  setVisible(true);
                  
                  
                  modif.addActionListener(l
                                -> {

                          
                            if (nbProduits.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                            } 
                              
                          /*  else if (nbProduits.getText().equals(""))
                            {
                                Dialog.show("Erreur", "Champ vide de Date debut ", "OK", null);

                            }
                                
                        */
                            else {
                                
                          //    c.setId(Integer.valueOf(Id.getText()));
                             c.setNbProduits(Integer.valueOf(nbProduits.getText()));
                               
                               ServiceCommande sp = new ServiceCommande();
                                Form previous = null;
                               sp.Update(c, previous,res);
                               //sp.getAllCommandes();

                                 Dialog.show("modifier", "modifier avec succés", "OK", null);

 ;
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
             
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
               }
           });
            this.add(nbProduits).add(modif);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(l->new CommandeFrom(pre,res).show());
      
     }     
        
                  
}

    

