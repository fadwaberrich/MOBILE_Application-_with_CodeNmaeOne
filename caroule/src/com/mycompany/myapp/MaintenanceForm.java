/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Maintenance;
import com.mycompany.services.ServiceMaintenance;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class MaintenanceForm extends Form {
    Resources theme = UIManager.initFirstTheme("/theme");
//  Resources themee = UIManager.initFirstTheme("/theme_1");
    Form Current;
    
    public MaintenanceForm(Form previous,Resources res)
    {
        
           super("Maintenance",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjouterMaintenance(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(p->new MaintenanceForm(Current,res).show());
        add(b7);    
        
             for (Maintenance c : new ServiceMaintenance().getAllMaintenance()) {
                    

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
            new AjouterMaintenance(res).show();
        });
                  /* this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
         new AjoutEvenementForm(res).show();
        });*/
                     /*this.getToolbar().addCommandToOverflowMenu("Stat", null, ev -> {
     new StatForm().createPieChartForm("Pubs", new ServicePublicite().findAllStat());
        });*/
                      
    }
    
     public MultiButton addItem_Publicite(Maintenance c,Resources res) {
  
 MultiButton m = new MultiButton();
 //  String url = "http://localhost/image/"+c.getImagee();
   
     m.setTextLine1(String.valueOf(c.getId_produit_id()));
     m.setTextLine1(String.valueOf(c.getReclamation_id()));
     m.setTextLine1(String.valueOf(c.getRelation_id()));
     m.setTextLine1(c.getEtat());
     
      m.setTextLine2(c.getDate_debut());
        m.setTextLine3(c.getDate_fin());
        m.setTextLine3(c.getAdresse());
        m.setTextLine3(c.getDescription());
      /* m.setTextLine4(String.valueOf(c.getNb_participants()));
      m.setText(String.valueOf(c.getNb_places()));
        m.setText(c.getLieu());
          m.setText(c.getType());*/
         m.setEmblem(theme.getImage("arrow.png"));
         
      m.setVisible(true);
        Button b5=new Button("supprimer maintenance");
	setVisible(true); 
        Button b6=new Button("modifier maintenance");
	setVisible(true);  
        //b.addActionListener(e -> new AffichageActivite(res).show());
         add(b5);
         add(b6);
         b6.addActionListener(l->new ModifierMaintenance(res,c).show());
         
        
       
//Click delete icon
b5.addPointerPressedListener(l -> {
Dialog dig = new Dialog("Suprression");
if(dig.show("Suppression", "Vous voulez supprimer cette maintenance ?","Annuler","Cui")) {
dig.dispose ();
}
else {
dig.dispose();
if (ServiceMaintenance.getInstance().delete(c.getId())) {     
new EvenementForm(Current,res); 
}
}
});
Button skip = new Button("back");
        m.addActionListener(e -> {
            Form f2 = new Form("Detail",BoxLayout.y());
            f2.add("adress : "+c.getAdresse()).add("date debut : "+c.getDate_debut()).add("date fin : "+c.getDate_fin()).add("description : "+c.getDescription()).add("etat : "+c.getEtat()).add("produit : "+c.getId_produit_id()).add("nombre places : "+c.getReclamation_id()).add("reclamation: "+c.getReclamation_id()).add("relation: "+c.getRelation_id()).add(skip);   
       
 f2.show();

        });
   
  skip.addActionListener(e -> new AjouterMaintenance(res).show());
     
        return m;
}
}