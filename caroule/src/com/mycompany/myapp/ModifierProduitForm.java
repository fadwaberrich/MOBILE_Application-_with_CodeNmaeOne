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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Produit;
import com.mycompany.services.ServiceProduit;
import java.io.IOException;
import java.util.Date;

/**
 * @author PC
 */
public class ModifierProduitForm extends Form {
    
         String file ;
  
     
          Resources theme;
                      Resources themee = UIManager.initFirstTheme("/theme");
                      
         public ModifierProduitForm(Resources res,Produit p)  {
                super("Produits", BoxLayout.y());
                //System.out.println("emailllll+ "+email);
                TextField libelle = new TextField(p.getLibelle(), "Libelle", 20, TextArea.CENTER);
                
                TextField image = new TextField(p.getImage(), "Image", 20, TextArea.TEXT_CURSOR);
                
                 TextField description = new TextField(p.getDescription(), "Description", 20, TextArea.EMAILADDR);
                 
                 TextField prix = new TextField(String.valueOf(p.getPrix()), "prix", 20, TextArea.TEXT_CURSOR);
                 
                 TextField type = new TextField(p.getType(), "type", 20, TextArea.NUMERIC);
                 
                 ///buttons
                 Button upload = new Button("Upload Image");
                 Button update = new Button("Modifier");
                 Button b3=new Button("liste event"); 
                 setVisible(true);
                 
                 //****************************************reupload image 
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
                 /*****************************************************************************/
                 update.addActionListener(l
                                -> {

                          
                            if (libelle.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de libelle ", "OK", null);

                            } else if (description.getText().equals("")) {
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
                                
                                p.setLibelle(libelle.getText());
                                p.setImage(file);
                                p.setDescription(description.getText());
                                //p.setPrix(String.valueOf(prix.getText());
                                p.setType(type.getText());
                                
                                p.setPrix(Integer.valueOf(prix.getText()));
                                ServiceProduit sp = new ServiceProduit();
                                Form previous = null;
                                sp.AjouterProduit(p, previous, res);
                                 Dialog.show("Modifier", "Modifié avec succés", "OK", null);
                                                                          
                                    }
                         });
                 
                 //passer parametre au bouton
                 this.add(libelle).add(description).add(prix).add(type).add(upload).add(update);
                 add(b3);
                 Form pre = null;
                 b3.addActionListener(l->new ProduitForm(pre,res).show());
}
}
