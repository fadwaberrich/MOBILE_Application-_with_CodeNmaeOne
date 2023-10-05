/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.capture.Capture;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
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
import com.codename1.util.Base64;
import com.mycompany.myapp.MyApplication;
import com.mycompany.entity.Location;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceLocation;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class AjoutLocationForm extends Form{
    
     public AjoutLocationForm(Resources res)  {
                super("Location", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                   TextField Date = new TextField("", "Date", 20, TextArea.TEXT_CURSOR);
                
                TextField Heure = new TextField("", "Heure ", 20, TextArea.TEXT_CURSOR);
                
                 TextField Duree = new TextField("", "Duree", 20, TextArea.NUMERIC);
           
                      TextField IdAbonnement = new TextField("", "IdAbonnement", 20, TextArea.NUMERIC);
                    TextField IdUser = new TextField("", "IdUser", 20, TextArea.NUMERIC);
                 
             
              
  
        Button save = new Button("Ajouter");
       Button b7=new Button("liste location"); 
         setVisible(true);
         
    
           save.addActionListener((ActionEvent l)-> {

                          
                            if (Date.getText().equals("")) {
                                Dialog.show("Erreur", "insérer la date!", "OK", null);

                            /*} else if (.isValid()) {
                                Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null);*/

                            } else if (Heure.getText().equals("")) {
                                Dialog.show("Erreur", "préciser l'heure! ", "OK", null);

                            }
                            
                            
                            else if (Duree.getText().equals("")) {
                                Dialog.show("Erreur", " prciser la durée ", "OK", null);

                            }

                       
                            else {
           
                               Location lc = new Location();
                                lc.setDate(Date.getText());
                                lc.setHeure(Heure.getText());
                                lc.setDuree(Integer.valueOf(Duree.getText()));
                                
                                
                                lc.setIdUser(Integer.valueOf(IdUser.getText()));
                                lc.setIdAbonnement(Integer.valueOf(IdAbonnement.getText()));
                               
                               ServiceLocation loc = new ServiceLocation();
                                Form previous = null;
                               loc.addLocation(lc);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);                    
                                                                    
                            }
           });
       this.add(Date).add(Heure).add(Duree).add(IdUser).add(IdAbonnement).add(save);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
               
        add(b7);
        Form pre = null;
 b7.addActionListener(e -> new LocationForm(pre,res).show());    
 /********************************************************SMS*******************************************/
   save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
             com.codename1.io.rest.Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/AC1bfc52d30073068147b27bcfeae02c20/Messages.json").
        queryParam("To", "+21623251728").
        queryParam("From","+17579095719").
        queryParam("Body", "Bonjour Mr/Mme nous viendrons de vous infomer qu'une nouvelle locatioj a ete ajoutee").
        header("Authorization", "Basic " + Base64.encodeNoNewline(("AC1bfc52d30073068147b27bcfeae02c20"+ ":" + "626f9f30ef57e99f0239c2c46bff15e8").getBytes())).
        getAsJsonMap();
        if(result.getResponseData() != null) {
        String error = (String)result.getResponseData().get("error_message");
            if(error != null) {
            ToastBar.showErrorMessage(error);
            }
        } 
        else {
        ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
        }
            }
        });
        

   
   
   
   
     }
}
