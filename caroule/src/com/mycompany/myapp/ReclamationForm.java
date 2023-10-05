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
import com.mycompany.entity.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author PC
 */
public class ReclamationForm extends Form {
    Resources theme = UIManager.initFirstTheme("/theme");
//  Resources themee = UIManager.initFirstTheme("/theme_1");
    Form Current;
    
    public ReclamationForm(Form previous,Resources res)
    {
        
           super("v",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjoutReclamationForm(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(l->new ReclamationForm(Current,res).show());
        add(b7);     
             for (Reclamation c : new ServiceReclamation().getAllReclamation()) {

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
            new AjoutReclamationForm(res).show();
        });
                  /* this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
         new AjoutEvenementForm(res).show();
        });*/
                     /*this.getToolbar().addCommandToOverflowMenu("Stat", null, ev -> {
     new StatForm().createPieChartForm("Pubs", new ServicePublicite().findAllStat());
        });*/
                      
    }
    
     public MultiButton addItem_Publicite(Reclamation c,Resources res) {
  
 MultiButton m = new MultiButton();
 //  String url = "http://localhost/image/"+c.getImagee();
   
     m.setTextLine1(c.getDescription());
      m.setTextLine2(c.getDate());
        m.setTextLine3(c.getObjet());
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
         b6.addActionListener(l->new ModifierReclamationForm(res,c).show());
         
        
       
//Click delete icon
b5.addPointerPressedListener(l -> {
Dialog dig = new Dialog("Suprression");
if(dig.show("Suppression", "Vous voulez supprimer cet Reclamation ?","Annuler","Cui")) {
dig.dispose ();
}
else {
dig.dispose();
if (ServiceReclamation.getInstance().delete(c.getId())) {     
new ReclamationForm(Current,res); 
}
}
});
Button skip = new Button("back");
        m.addActionListener(e -> {
            Form f2 = new Form("Detail",BoxLayout.y());
            f2.add("Objet : "+c.getObjet()).add("Description: "+c.getDescription()).add("date: "+c.getDate()).add(skip);   
       
 f2.show();

        });
   
  skip.addActionListener(e -> new AjoutReclamationForm(res).show());
     
        return m;
}
}