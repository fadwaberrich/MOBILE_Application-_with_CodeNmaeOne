/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Message;
import com.mycompany.services.ServiceMessage;

/**
 *
 * @author PC
 */
public class MessageForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
//  Resources themee = UIManager.initFirstTheme("/theme_1");
    Form Current;

    public MessageForm(Form previous, Resources res) {

        super("Message", BoxLayout.y());
        Current = this;
        this.add(new InfiniteProgress());

        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                skip.setUIID("back");
                skip.addActionListener(e -> new AjoutMessageForm(res).show());
                add(skip);
                Button b7 = new Button("REFRESH");
                b7.setUIID("refresh");
                b7.addActionListener(l -> new MessageForm(Current, res).show());
                add(b7);
                for (Message m : new ServiceMessage().afficherMessage()) {

                    this.add(addItem_Publicite(m, res));

                }

                this.revalidate();
            });
        });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    mb.setHidden(false);
                    mb.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 7);

        this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AjoutMessageForm(res).show();
        });
    }

    public MultiButton addItem_Publicite(Message s, Resources res) {

        MultiButton m = new MultiButton();
        //  String url = "http://localhost/image/"+c.getImagee();

        System.out.println(s.getContenu());
        m.setTextLine1(s.getContenu());
        // m.setTextLine3(String.valueOf(s.getIdSujet()));
        // m.setTextLine4(String.valueOf(s.getIdUser()));
        /* m.setTextLine4(String.valueOf(c.getNb_participants()));
      m.setText(String.valueOf(c.getNb_places()));
        m.setText(c.getLieu());
          m.setText(c.getType());*/
        m.setEmblem(theme.getImage("arrow.png"));

        m.setVisible(true);
        Button b5 = new Button("supprimer Message");
        setVisible(true);
        Button b6 = new Button("modifier Message");
        setVisible(true);
        //b.addActionListener(e -> new AffichageActivite(res).show());
        add(b5);
        add(b6);
        b6.addActionListener(l -> new ModifierMessageForm(res, s).show());

//Click delete icon
        b5.addPointerPressedListener(l -> {
            Dialog dig = new Dialog("Suprression");
            if (dig.show("Suppression", "Vous voulez supprimer ce Message ?", "Annuler", "Cui")) {
                dig.dispose();
            } else {
                dig.dispose();
                System.out.println(s.getId());
                if (ServiceMessage.getInstance().delete(s.getId())) {
                    new MessageForm(Current, res);
                }
            }
        });
        Button skip = new Button("back");
        m.addActionListener(e -> {

            Form f2 = new Form("Detail", BoxLayout.y());

            f2.add("contenu : " + s.getContenu()).add("date : " + s.getDate()).add("Sujet: " + s.getIdSujet()).add("User : " + s.getIdUser()).add(skip);

            f2.show();

        });

        skip.addActionListener(e -> new AjoutMessageForm(res).show());

        return m;
    }
}
