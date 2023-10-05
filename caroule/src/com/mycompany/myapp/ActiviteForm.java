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
import com.mycompany.entity.Activite;
import com.mycompany.services.ServiceActivite;
/**
 *
 * @author Lenovo
 */
public class ActiviteForm extends Form{
    Resources theme = UIManager.initFirstTheme("/theme");
//  Resources themee = UIManager.initFirstTheme("/theme_1");
    Form Current;
    
    public ActiviteForm(Form previous,Resources res)
    {
        
           super("Activite",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjoutActivite(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(l->new ActiviteForm(Current,res).show());
        add(b7);     
             for (Activite c : new ServiceActivite().getAllActivites()) {

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
                  
                    /*boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;*/
                   mb.setHidden(false);
                    mb.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 7);
        
        
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AjoutActivite(res).show();
        });
                   this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
         new AjoutActivite(res).show();
        });
                     /*this.getToolbar().addCommandToOverflowMenu("Stat", null, ev -> {
     new StatForm().createPieChartForm("Pubs", new ServicePublicite().findAllStat());
        });*/
                      
    }
    
     public MultiButton addItem_Publicite(Activite c,Resources res) {
  
 MultiButton m = new MultiButton();
   String url = "http://localhost:81/image/"+c.getImage();
   
     m.setTextLine1(c.getNom());
      m.setTextLine2(c.getDescription());
        m.setTextLine3(c.getImage());
    Image imge;
         EncodedImage enc;
        //enc = EncodedImage.createFromImage(theme.getImage("a.png"), false);
       // imge = URLImage.createToStorage(enc, url, url);
      ///  m.setIcon(imge);
         m.setEmblem(theme.getImage(url));
         
    //  m.setVisible(true);
        Button b5=new Button("supprimer activite");
	setVisible(true); 
        Button b6=new Button("modifier activite");
	setVisible(true);  
         add(b5);
         add(b6);
         b6.addActionListener(l->new modifierActiviteForm(res,c).show());
         
        
       
//Click delete icon
b5.addPointerPressedListener(l -> {
Dialog dig = new Dialog("Suprression");
if(dig.show("Suppression", "voulez vous supprimer cette activité ?","Annuler","Cui")) {
dig.dispose ();
}
else {
dig.dispose();
if (ServiceActivite.getInstance().delete(c.getId())) {     
new ActiviteForm(Current,res); 
}
}
});
Button skip = new Button("back");
        m.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
      
                 
     
     
            f2.add("nom : "+c.getNom()).add("description : "+c.getDescription()).add(skip);   
       String url2 = "http://localhost:81/image/"+c.getImage();

  Image imge2;
        EncodedImage enc2;
//        enc2 = EncodedImage.createFromImage(theme.getImage("a.png"), false);
  //      imge2 = URLImage.createToStorage(enc2, url2, url2);
     //   f2.add(imge2);
 f2.show();

        });
   
  skip.addActionListener(e -> new AjoutActivite(res).show());
     
        return m;
}
}
