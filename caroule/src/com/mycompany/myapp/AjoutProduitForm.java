/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entity.Produit;
import com.mycompany.services.ServiceProduit;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Date;

/**
 *
 * @author PC
 */
public class AjoutProduitForm extends Form {
    
     String file ;
  
     
          Resources theme;
                      Resources themee = UIManager.initFirstTheme("/theme");

    
     public AjoutProduitForm(Resources res)  {
                super("Produits", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
                skip.addActionListener(j -> new NewsfeedForm(res).show());
                add(skip);
                //System.out.println("emailllll+ "+email);
                   TextField libelle = new TextField("", "Libelle", 20, TextArea.CENTER);
                
                //TextField image = new TextField("", "Image", 20, TextArea.TEXT_CURSOR);
                
                 TextField description = new TextField("", "Description", 20, TextArea.EMAILADDR);
                 
                 TextField prix = new TextField("", "prix", 20, TextArea.TEXT_CURSOR);
                 
                 TextField type = new TextField("", "type", 20, TextArea.NUMERIC);

                 
                  Button upload = new Button("Upload Image");
                  
                        /*Validator val_lib = new Validator();
                            val_lib.addConstraint(libelle, new LengthConstraint(3));
                            String text_saisir_des_caracteres = "^[0-9]+$";
                            val_lib.addConstraint(libelle, new RegexConstraint(text_saisir_des_caracteres, ""));*/
                           /* // val lastname   
                            Validator val_lastname = new Validator();
                            val_lastname.addConstraint(Libelle, new LengthConstraint(8));
                            val_lastname.addConstraint(Libelle, new RegexConstraint(text_saisir_des_caracteres, ""));
                  
                  
           String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";*/
                            
                             // val mail   
                            /*Validator val_mail = new Validator();
                            val_mail.addConstraint(Libelle, new LengthConstraint(8));
                            val_mail.addConstraint(Libelle, new RegexConstraint(text_mail, ""));*/
                            
        Button save = new Button("Ajouter");
        Button b2=new Button("liste des Produits"); 
         setVisible(true);
                 
               
       upload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/image/");
                    cr.setPost(true);
                    String mime = "/image";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    //file=fileNameInServer;
                    file = "bike8.png";
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

                          
                            if (libelle.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de libelle ", "OK", null);

                            /*} else if (val_lib.isValid()) {
                                Dialog.show("Erreur libelle !", "il faut saisir des caracteres  !", "OK", null);

                            */} else if (description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de libelle ", "OK", null);

                           /* }else if (val_lastname.isValid()) {
                                Dialog.show("Erreur LASTNAME !", "il faut saisir des caracteres  !", "OK", null);*/
                            }
                            else if (prix.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de prix ", "OK", null);
                            }
                            else if (type.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de type ", "OK", null);

                            /*} else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);*/
                            }  
                              
                            else {         
                                Produit p = new Produit();
                                p.setLibelle(libelle.getText());
                                p.setImage(file);
                                p.setDescription(description.getText());
                               // p.setPrix(prix.getText());
                                p.setType(type.getText());
                                
                                //p.setPrix(Integer.valueOf(prix.getText()));
                                ServiceProduit sp = new ServiceProduit();
                                Form previous = null;
                                sp.AjouterProduit(p, previous, res);
                                 Dialog.show("Ajout", "Ajouté avec succés", "OK", null);
                                 // String url = "http://localhost/pdf/ex.php";
                                 
/*Button btn = new Button("hee");
this.add(btn);

btn.addActionListener(ll->{

});
*/

                                         
                            }
           });
        
        
        
        
        
        
          this.add(libelle).add(description).add(prix).add(type).add(upload).add(save);
                  Produit p=new Produit();
      // p.setEmail(SessionManager.getEmail());
        
           this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
          new NewsfeedForm(themee).show();
        });
        
        
              add(b2);
         Form pre = null;
 b2.addActionListener(e -> new ProduitForm(pre,res).show());   
                 
     }
}
