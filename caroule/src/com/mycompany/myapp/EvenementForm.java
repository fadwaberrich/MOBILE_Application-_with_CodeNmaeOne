/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.entity.Evenement;
import com.mycompany.services.ServiceEvenement;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class EvenementForm extends Form {
    Resources theme = UIManager.initFirstTheme("/theme");
//  Resources themee = UIManager.initFirstTheme("/theme_1");
    Form Current;
    
    public EvenementForm(Form previous,Resources res)
    {
        
           super("Evenement",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjoutEvenementForm(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(l->new EvenementForm(Current,res).show());
        add(b7);     
             for (Evenement c : new ServiceEvenement().getAllEvenements()) {

            this.add(addItem_Publicite(c,res));

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
            new AjoutEvenementForm(res).show();
        });
                  /* this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
         new AjoutEvenementForm(res).show();
        });*/
                     /*this.getToolbar().addCommandToOverflowMenu("Stat", null, ev -> {
     new StatForm().createPieChartForm("Pubs", new ServicePublicite().findAllStat());
        });*/
                      
    }
    
     public MultiButton addItem_Publicite(Evenement c,Resources res) {
  
 MultiButton m = new MultiButton();
 //  String url = "http://localhost/image/"+c.getImagee();
   
     m.setTextLine1(c.getNom());
      m.setTextLine2(c.getDateD());
        m.setTextLine3(c.getDateF());
      /* m.setTextLine4(String.valueOf(c.getNb_participants()));
      m.setText(String.valueOf(c.getNb_places()));
        m.setText(c.getLieu());
          m.setText(c.getType());*/
         m.setEmblem(theme.getImage("arrow.png"));
         
      m.setVisible(true);
        Button b5=new Button("supprimer event");
	setVisible(true); 
        Button b6=new Button("modifier event");
	setVisible(true);  
        //b.addActionListener(e -> new AffichageActivite(res).show());
         add(b5);
         add(b6);
         b6.addActionListener(l->new ModifierEvenementForm(res,c).show());
         
        
       
//Click delete icon
b5.addPointerPressedListener(l -> {
Dialog dig = new Dialog("Suprression");
if(dig.show("Suppression", "Vous voulez supprimer cet evenement ?","Annuler","Cui")) {
dig.dispose ();
}
else {
dig.dispose();
if (ServiceEvenement.getInstance().delete(c.getId())) {     
new EvenementForm(Current,res); 
}
}
});
Button skip = new Button("back");
        m.addActionListener(e -> {
            Form f2 = new Form("Detail",BoxLayout.y());
            f2.add("nom : "+c.getNom()).add("date debut : "+c.getDateD()).add("date fin : "+c.getDateF()).add("type : "+c.getType()).add("lieu : "+c.getLieu()).add("nombre participants : "+c.getNb_participants()).add("nombre places : "+c.getNb_places()).add(skip);   
       
 f2.show();

        });
   
  skip.addActionListener(e -> new AjoutEvenementForm(res).show());
     
        return m;
}
}