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
import com.mycompany.entity.Achat;
import com.mycompany.services.ServiceAchat;


public class AchatForm extends Form {
    Resources theme = UIManager.initFirstTheme("/theme");
    Form Current;
    
    public AchatForm(Form previous,Resources res)
    {
        
           super("Achat",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjoutAchatForm(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(l->new AchatForm(Current,res).show());
        add(b7);     
             for (Achat s : new ServiceAchat().getAllAchats()) {

            this.add(addItem_Publicite(s,res));

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
                  
                    /*boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;*/
                   mb.setHidden(false);
                    mb.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 7);
        
        
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AjoutAchatForm(res).show();
        });
}
    
     public MultiButton addItem_Publicite(Achat s,Resources res) {
  
 MultiButton m = new MultiButton();
 //  String url = "http://localhost/image/"+c.getImagee();
   
       
      m.setText(String.valueOf(s.getIdProduit()));
      m.setText(String.valueOf(s.getIdUser()));
        m.setText(String.valueOf(s.getNumeroClient()));
        m.setText(s.getDate());
         m.setText(s.getNomClient());
         m.setText(String.valueOf(s.getId()));

        m.setEmblem(theme.getImage("arrow.png"));
         
      m.setVisible(true);
        Button b5=new Button("supprimer Achat");
	setVisible(true); 
        Button b6=new Button("modifier Achat");
	setVisible(true);  
        
         add(b5);
         add(b6);
         b6.addActionListener(l->new ModifierAchatForm(res,s).show());
         
        
       
//Click delete icon
b5.addPointerPressedListener(l -> {
Dialog dig = new Dialog("Suprression");
if(dig.show("Suppression", "Vous voulez supprimer cet Achat ?","Annuler","Cui")) {
dig.dispose ();
}
else {
dig.dispose();
if (ServiceAchat.getInstance().delete(s.getId())) {     
new AchatForm(Current,res); 
}
}
});
Button skip = new Button("back");
        m.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
      
                 
     
     
            f2.add("Id : "+s.getId()).add("IdUser : "+s.getIdUser()).add("Date : "+s.getDate()).add("NomClient : "+s.getNomClient()).add("IdProduit : "+s.getIdProduit()).add("Numero Client : "+s.getNumeroClient()).add(skip);   
       
 f2.show();

        });
   
  skip.addActionListener(e -> new AjoutAchatForm(res).show());
     
        return m;
}
}
