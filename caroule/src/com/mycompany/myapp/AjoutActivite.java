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
import com.mycompany.entity.Activite;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceActivite;
import static com.sun.glass.ui.Cursor.setVisible;
/**
 *
 * @author Lenovo
 */
public class AjoutActivite extends Form {
       String file ;
    public AjoutActivite(Resources res)  {
                super("Activite", BoxLayout.y());
                        
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                   TextField Nom = new TextField("", "Nom activite", 20, TextArea.TEXT_CURSOR);
                
                TextField Description = new TextField("", "Description ", 20, TextArea.TEXT_CURSOR);
                
                 //TextField image = new TextField("", " image", 20, TextArea.EMAILADDR);
                 Button upload = new Button("Upload Image");
          TextField idEvenement = new TextField("", "id event ", 20, TextArea.TEXT_CURSOR);
                 /*Label lab = new Label("0 DT");
                 affichage.addActionListener(www->{
                     
                    lab.setText(String.valueOf( Integer.valueOf(affichage.getText())*  2000           )+" DT");
                 });
                 
                  Button upload = new Button("Upload Image");
                  
                         Validator val_firstname = new Validator();
                            val_firstname.addConstraint(Prenom, new LengthConstraint(8));
                            String text_saisir_des_caracteres = "^[0-9]+$";
                            val_firstname.addConstraint(Prenom, new RegexConstraint(text_saisir_des_caracteres, ""));
                            // val lastname   
                            Validator val_lastname = new Validator();
                            val_lastname.addConstraint(Nom, new LengthConstraint(8));
                            val_lastname.addConstraint(Nom, new RegexConstraint(text_saisir_des_caracteres, ""));
                  
                  
           String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                            
                             // val mail   
                            Validator val_mail = new Validator();
                            val_mail.addConstraint(mail, new LengthConstraint(8));
                            val_mail.addConstraint(mail, new RegexConstraint(text_mail, ""));*/
        Button save = new Button("Ajouter");
       Button b7=new Button("liste Activite"); 
         setVisible(true);
         
       //btnsupp.addActionListener(w -> new NewsfeedForm(res).show())
               
      upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/image/uploadimage.php");
                    cr.setPost(true);
                    String mime = "/image";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });   
           save.addActionListener(l
                                -> {

                          
                            if (Nom.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom activite ", "OK", null);

                            /*} else if (.isValid()) {
                                Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null);*/

                            } else if (Description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de description activite ", "OK", null);

                            }
      
                           /* else if (image.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de image activite ", "OK", null);

                            }*/ 
                              
                            
                            else {
                                          
                                          
                                Activite a = new Activite();
                                a.setNom(Nom.getText());
                                a.setDescription(Description.getText());
                                a.setImage(file);
                                a.setIdEvenement(Integer.valueOf(idEvenement.getText()));

                        
                               
                               ServiceActivite sp = new ServiceActivite();
                                Form previous = null;
                               sp.Add(a, previous,res);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                              ;
 ;
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
             
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                                                    
                            }
           });
        
          this.add(Nom).add(Description).add(upload).add(idEvenement).add(save);
   
        add(b7);
         Form pre = null;
 b7.addActionListener(e -> new ActiviteForm(pre,res).show());                 
     }

    

}
