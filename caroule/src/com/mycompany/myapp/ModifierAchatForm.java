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
import com.mycompany.entity.Achat;
import com.mycompany.services.ServiceAchat;

/**
 *
 * @author PC
 */
public class ModifierAchatForm extends Form {

    public ModifierAchatForm(Resources res, Achat s) {
        super("Achat", BoxLayout.y());

        // TextField id = new TextField(String.valueOf(s.getId()),"id", 20, TextArea.TEXT_CURSOR);
        TextField nomclient = new TextField(s.getNomClient(), "nom client", 20, TextArea.TEXT_CURSOR);
        TextField numeroClient = new TextField(String.valueOf(s.getNumeroClient()), "numero client", 20, TextArea.TEXT_CURSOR);

        //TextField date = new TextField("", "date", 20, TextArea.TEXT_CURSOR);
        //TextField idProduit = new TextField("", "Id Produit", 20, TextArea.TEXT_CURSOR);
        // TextField idUser = new TextField("", "Id User", 20, TextArea.TEXT_CURSOR);
        Button modif = new Button("modifier");
        Button b12 = new Button("liste Achat");
        setVisible(true);

        modif.addActionListener(l
                -> {

            /*if (id.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de id ", "OK", null);

                            } /*else if (date.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de date ", "OK", null);

                            }*/
            if (nomclient.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

            } else if (numeroClient.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide de numeroclient ", "OK", null);

            } /*   else if (idProduit.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de produit ", "OK", null);

                            }*/ else {
                //s.setId(Integer.valueOf(id.getText()));
                //s.setDate(date.getText());
                s.setNomClient(nomclient.getText());
                s.setNumeroClient(Integer.valueOf(numeroClient.getText()));

                //s.setIdProduit(Integer.valueOf(idProduit.getText()));
                ServiceAchat sp = new ServiceAchat();
                Form previous = null;
                sp.Update(s, previous, res);
                Dialog.show("modifier", "modifier avec succés", "OK", null);

            }
        });
        this.add(nomclient).add(numeroClient).add(modif);
        /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/

        add(b12);
        Form pre = null;
        b12.addActionListener(l -> new AchatForm(pre, res).show());

    }

}
