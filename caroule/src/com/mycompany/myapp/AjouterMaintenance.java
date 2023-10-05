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
import com.mycompany.entity.Maintenance;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceMaintenance;

/**
 *
 * @author PC
 */
public class AjouterMaintenance extends Form{
    

    
     public AjouterMaintenance(Resources res)  {
                super("Maintenance", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                   TextField idProduit = new TextField("", "produit à maintenire", 20, TextArea.TEXT_CURSOR);
                
                 TextField adresse = new TextField("", "adresse", 20, TextArea.TEXT_CURSOR);
                 
                 TextField reclamation = new TextField("", "Reclamation", 20, TextArea.NUMERIC);
                 
                 TextField relation = new TextField("", "relation", 20, TextArea.TEXT_CURSOR);
                 
                 TextField desc = new TextField("", "description", 20, TextArea.TEXT_CURSOR);
                 
                 TextField etat = new TextField("", "etat", 20, TextArea.TEXT_CURSOR);
                 
         
                 
        Button save = new Button("Ajouter");
       Button b7=new Button("liste Maintenace"); 
         setVisible(true);
         
           save.addActionListener(l
                                -> {

                          
                            if (idProduit.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);

                            /*} else if (.isValid()) {
                                Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null);*/

                            }  /*else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);

                            }  */
                              else if (adresse.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de domaine ", "OK", null);

                            }
                                  else if (reclamation.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de prix ", "OK", null);

                            }
                                    else if (relation.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lien ", "OK", null);

                            }    else if (desc.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lien ", "OK", null);

                            } 
                             else if (etat.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lien ", "OK", null);

                            } 
                            else {
                                          
                                          
                                Maintenance p = new Maintenance();
                                p.setId_produit_id(Integer.valueOf(idProduit.getText()));
                                 p.setRelation_id(Integer.valueOf(relation.getText()));
                                 p.setReclamation_id(Integer.valueOf(reclamation.getText()));
                                 p.setDescription(desc.getText());
                                 p.setEtat(etat.getText());
                                 p.setAdresse(adresse.getText());
                                p.setIdUser(Integer.valueOf(relation.getText()));
                               // p.setNb_places(Integer.valueOf(nb_places.getText()));
                               
                               ServiceMaintenance sp = new ServiceMaintenance();
                                Form previous = null;
                               sp.Add(previous,res,p);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                                //  String url = "http://localhost/pdf/ex.php";
/*Button btn = new Button("hee");
this.add(btn);

btn.addActionListener(ll->{

});
*/

               ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
               
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                     
                                                                    
                            }
           });

          this.add(idProduit).add(desc).add(reclamation).add(relation).add(adresse).add(etat).add(save);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/

        add(b7);
         Form pre = null;
 b7.addActionListener(e -> new MaintenanceForm(pre,res).show());                 
     }
}
