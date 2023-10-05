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
import com.codename1.ui.Command;
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
import com.mycompany.entity.Commande;
import com.mycompany.services.ServiceCommande;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Date;

/**
 *
 * @author Ahmed Elmoez
 */
public class AjoutCommande extends Form {

    public AjoutCommande(Resources res) {
        super("Commande", BoxLayout.y());
        Button skip = new Button("back");
        skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
        add(skip);
        //TextField Id = new TextField("", "id", 20, TextArea.TEXT_CURSOR);

        TextField NbProduits = new TextField("", "nbProduits", 20, TextArea.TEXT_CURSOR);

        TextField idProduit = new TextField("", "idProduit", 20, TextArea.TEXT_CURSOR);
        TextField idUser = new TextField("", "IdUser", 20, TextArea.TEXT_CURSOR);

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
        Button b7 = new Button("liste Commande");
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

            //if (Id.getText().equals("")) {
            //Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);

            /*} else if (.isValid()) {
                                Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null);*/
            //}
            if (NbProduits.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

            } else if (idProduit.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

            } else if (idUser.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide de email ", "OK", null);/*else if (val_lastname.isValid()) {
                                Dialog.show("Erreur LASTNAME !", "il faut saisir des caracteres  !", "OK", null);

                            } */

 /*else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);

                            }  */

            } else {

                Commande p = new Commande();
                p.setIdUser(Integer.valueOf(idUser.getText()));
                p.setNbProduits(Integer.valueOf(NbProduits.getText()));
                p.setIdProduit(Integer.valueOf(idProduit.getText()));
                System.out.println("user " + p.getIdUser() + "prod" + p.getIdProduit()+ "id " + p.getNbProduits());
                // p.setIdProduit(Integer.valueOf(idProduit.getText()));
                // p.setIdUser(Integer.valueOf(idUser.getText()));
                ServiceCommande sc = new ServiceCommande();
                Form previous = null;
                sc.Add(p, previous, res);
                Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                //  String url = "http://localhost/pdf/ex.php";
/*Button btn = new Button("hee");
this.add(btn);

btn.addActionListener(ll->{

});
                 */
    //           ConnectionRequest cnreq = new ConnectionRequest();
  //              cnreq.setPost(false);

//                NetworkManager.getInstance().addToQueueAndWait(cnreq);

            }
        });

        this.add(NbProduits).add(idUser).add(idProduit).add(save);
        /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/

        add(b7);
        Form pre = null;
        b7.addActionListener(e -> new CommandeFrom(pre, res).show());
    }
}
