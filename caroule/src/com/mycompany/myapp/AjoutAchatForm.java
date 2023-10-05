
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
import com.mycompany.entity.Achat;
import com.mycompany.services.ServiceAchat;

/**
 *
 * @author PC
 */
public class AjoutAchatForm extends Form{
    

    
     public AjoutAchatForm(Resources res)  {
                super("Achat", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                   TextField iduser = new TextField("", "idUser", 20, TextArea.TEXT_CURSOR);
                
                TextField date = new TextField("", "date", 20, TextArea.TEXT_CURSOR);
                
                 TextField nomclient = new TextField("", "nom client", 20, TextArea.TEXT_CURSOR);
                 
                 TextField numeroClient = new TextField("", "numero client", 20, TextArea.TEXT_CURSOR);
                 
                 TextField idProduit = new TextField("", "Id Produit", 20, TextArea.NUMERIC);

                 
        //buttons
         Button save = new Button("Ajouter");
        Button b11=new Button("liste des Achats"); 
         setVisible(true);
         
         //data
         save.addActionListener(l
                                -> {

                          
                            if (iduser.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de id ", "OK", null);

                            } 
                            else if (date.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de date ", "OK", null);

                            }
                            else if (nomclient.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                            } 
                              else if (numeroClient.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de numeroclient ", "OK", null);

                            }
                                  else if (idProduit.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de produit ", "OK", null);

                            }
                            
                            else {       
                                Achat s = new Achat();
                                s.setIdUser(Integer.valueOf(iduser.getText()));
                                s.setDate(date.getText());
                                s.setNomClient(nomclient.getText());
                              s.setNumeroClient(Integer.valueOf(numeroClient.getText()));
                                
                                s.setIdProduit(Integer.valueOf(idProduit.getText()));
                                
                                ServiceAchat sp = new ServiceAchat();
                                Form previous = null;
                               sp.add(s, previous,res);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                                 
                                 ConnectionRequest cnreq = new ConnectionRequest();
                                 cnreq.setPost(false);
                                 NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                }
                            });
         this.add(iduser).add(date).add(nomclient).add(numeroClient).add(idProduit).add(save);
         
            add(b11);
            Form pre = null;
            b11.addActionListener(e -> new AchatForm(pre,res).show());
     }
}
