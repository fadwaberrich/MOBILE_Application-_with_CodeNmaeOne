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
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Sujet;
import com.mycompany.services.ServiceSujet;

/**
 *
 * @author user
 */
public class ModifierSujetForm extends Form{

    public ModifierSujetForm(Resources res, Sujet e) {
        super("Sujet", BoxLayout.y());

           Label contenulabel = new Label("contenu");
       Label titrelabel = new Label("titre");
        TextField titre = new TextField(e.getTitre(), "titre", 20, TextArea.TEXT_CURSOR);
        TextField contenu = new TextField(e.getContenu(), "contenu", 20, TextArea.TEXT_CURSOR);

        Button modif = new Button("modifier");
        Button b8 = new Button("liste Sujet");
        setVisible(true);

        modif.addActionListener(l
                -> {

            if (titre.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

            } else if (contenu.getText().equals("")) {
                Dialog.show("Erreur", "Champ vide de Date debut ", "OK", null);
            }  else {
                e.setTitre(titre.getText());
                e.setContenu(contenu.getText());
                ServiceSujet sp = new ServiceSujet();
                Form previous = null;
                sp.Update(e, previous, res);
                Dialog.show("modifier", "modifier avec succés", "OK", null);
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);

                NetworkManager.getInstance().addToQueueAndWait(cnreq);
            }
        });
        this.add(titrelabel).add(titre).add(contenulabel).add(contenu).add(modif);
        /*        Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());*/

        add(b8);
        Form pre = null;
        b8.addActionListener(l -> new SujetForm(pre, res).show());

    }

}
