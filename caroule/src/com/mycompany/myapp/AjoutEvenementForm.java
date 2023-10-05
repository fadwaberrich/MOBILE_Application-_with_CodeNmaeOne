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
import com.mycompany.entity.Evenement;
import java.io.IOException;
import java.util.Date;
import com.mycompany.services.ServiceEvenement;
/**
 *
 * @author Lenovo
 */
public class AjoutEvenementForm extends Form{
    

    
     public AjoutEvenementForm(Resources res)  {
                super("Evenement", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
                   TextField Nom = new TextField("", "Nom Evenement", 20, TextArea.TEXT_CURSOR);
                
                TextField Dated = new TextField("", "Date debut ", 20, TextArea.TEXT_CURSOR);
                
                 TextField Datef = new TextField("", "Date fin", 20, TextArea.EMAILADDR);
                 
                 TextField lieu = new TextField("", "lieu", 20, TextArea.TEXT_CURSOR);
                 
                 TextField type = new TextField("", "type", 20, TextArea.NUMERIC);
                 
                 TextField nb_participants = new TextField("", "nb participants", 20, TextArea.TEXT_CURSOR);
                 
                 TextField nb_places = new TextField("", "nb places", 20, TextArea.TEXT_CURSOR);
                 
         
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
       Button b7=new Button("liste event"); 
         setVisible(true);
         
       //btnsupp.addActionListener(w -> new NewsfeedForm(res).show())
               
      /*  upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/image/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
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
        });   */
           save.addActionListener(l
                                -> {

                          
                            if (Nom.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);

                            /*} else if (.isValid()) {
                                Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null);*/

                            } else if (Dated.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

                            }/*else if (val_lastname.isValid()) {
                                Dialog.show("Erreur LASTNAME !", "il faut saisir des caracteres  !", "OK", null);

                            } */
                            
                            
                            else if (Datef.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } /*else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);

                            }  */
                              else if (lieu.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de domaine ", "OK", null);

                            }
                                  else if (type.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de prix ", "OK", null);

                            }
                                    else if (nb_participants.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lien ", "OK", null);

                            }
                                      else if (nb_places.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de affichage ", "OK", null);

                            }
                            
                            else {
                                          
                                          
                                Evenement p = new Evenement();
                                p.setNom(Nom.getText());
                                p.setDateD(Dated.getText());
                                p.setDateF(Datef.getText());
                                 p.setLieu(lieu.getText());
                                 p.setType(type.getText());
                                // p.setNb_participants(Integer.valueOf(nb_participants.getText()));
                               // p.setNb_places(Integer.valueOf(nb_places.getText()));
                               
                               ServiceEvenement sp = new ServiceEvenement();
                                Form previous = null;
                               sp.Add(p, previous,res);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                                //  String url = "http://localhost/pdf/ex.php";
/*Button btn = new Button("hee");
this.add(btn);

btn.addActionListener(ll->{

});
*/
 ;
             //   ConnectionRequest cnreq = new ConnectionRequest();
             //   cnreq.setPost(false);
               /* String data = "Nom : " + Nom.getText() + "<br>  Prenom : " + DateD.getText() + " <br>  mail :" + mail.getText() + " <br> domaine : " + domaine.getText() + " <br> lien : " + lien.getText()+ " <br> Prix : " + String.valueOf( Integer.valueOf(affichage.getText())*  2000           )+" DT"+"<br> Merci pour votre confiance &#128525;";

                cnreq.addArgument("data", data);
                cnreq.setUrl(url);
                cnreq.addResponseListener(evx
                        -> {
                    String valeur = new String(cnreq.getResponseData());
                     Dialog.show("PDF", "PDF", "OK", null);
                

                }
                );*/
              //  NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                                                    
                            }
           });
        
         
                  
        
        
        
        
          this.add(Nom).add(Dated).add(Datef).add(lieu).add(type).add(nb_participants).add(nb_places).add(save);
          /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/
        
         
        
        add(b7);
         Form pre = null;
 b7.addActionListener(e -> new EvenementForm(pre,res).show());                 
     }
}
