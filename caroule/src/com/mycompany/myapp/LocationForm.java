/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Rest;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.myapp.MyApplication;
import com.mycompany.entity.Location;
import com.mycompany.services.ServiceLocation;
import java.util.Map;
/**
 *
 * @author Azus
 */
public class LocationForm extends Form {
    Resources theme = UIManager.initFirstTheme("/theme");
//  Resources themee = UIManager.initFirstTheme("/theme_1");
    Form Current;
    
    public LocationForm(Form previous,Resources res)
    {
        
           super("Location",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjoutLocationForm(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(l->new LocationForm(Current,res).show());
        add(b7);     
             for (Location l : new ServiceLocation().getAllLocations()) {

            this.add(addItem_Publicite(l,res));

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
                    String line3 = mb.getTextLine3();
                  String line4 = mb.getTextLine4();
                    /*boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;*/
                   mb.setHidden(false);
                    mb.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 7);
        
        
               this.getToolbar().addCommandToOverflowMenu("back", null, location -> {
            new AjoutLocationForm(res).show();
        });
                  /* this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
         new AjoutEvenementForm(res).show();
        });*/
                     /*this.getToolbar().addCommandToOverflowMenu("Stat", null, ev -> {
     new StatForm().createPieChartForm("Pubs", new ServicePublicite().findAllStat());
        });*/
                      
    }
    
     public MultiButton addItem_Publicite(Location l,Resources res) {
  
 MultiButton m = new MultiButton();
 
   
     m.setTextLine1(l.getDate());
      m.setTextLine2(l.getHeure());
        m.setTextLine3(String.valueOf(l.getDuree()));
         m.setTextLine4(String.valueOf(l.getIdAbonnement()));
      /* m.setTextLine4(String.valueOf(c.getNb_participants()));
      m.setText(String.valueOf(c.getNb_places()));
        m.setText(c.getLieu());
          m.setText(c.getType());*/
         m.setEmblem(theme.getImage("arrow.png"));
         
      m.setVisible(true);
        Button b5=new Button("supprimer location");
	setVisible(true); 
        Button b6=new Button("modifier location");
	setVisible(true);  
        //b.addActionListener(e -> new AffichageActivite(res).show());
         add(b5);
         add(b6);
         b6.addActionListener(loc->new ModifierLocationForm(res,l).show());
         
        
       
        //Click delete icon
        b5.addPointerPressedListener(loc -> {
        Dialog dig = new Dialog("Suprression");
        if(dig.show("Suppression", "Vous voulez supprimezr ?","Annuler","Cui")) {
        dig.dispose ();
        }
        else {
        dig.dispose();
        if (ServiceLocation.getInstance().delete(l.getId())) {     
        new LocationForm(Current,res); 
        }
        }
        });
        
        /*b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                
             com.codename1.io.rest.Response<Map> resultt = Rest.post("https://api.twilio.com/2010-04-01/Accounts/AC1bfc52d30073068147b27bcfeae02c20/Messages.json").
        queryParam("To", "+21623251728").
        queryParam("From","+17579095719").
        queryParam("Body", "Bonjour Mr/Mme nous viendrons de vous infomer qu'unelocation a ete suprimee").
        header("Authorization", "Basic " + Base64.encodeNoNewline(("AC1bfc52d30073068147b27bcfeae02c20"+ ":" + "626f9f30ef57e99f0239c2c46bff15e8").getBytes())).
        getAsJsonMap();
        if(resultt.getResponseData() != null) {
        String error = (String)resultt.getResponseData().get("error_message");
            if(error != null) {
            ToastBar.showErrorMessage(error);
            }
        } 
        else {
        ToastBar.showErrorMessage("Error sending SMS: " + resultt.getResponseCode());
        }
            }
        });*/
        
        Button skip = new Button("back");
                m.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
      
     
            f2.add("Date : "+l.getDate()).add("Heure : "+l.getHeure()).add("Duree : "+l.getDuree()).add("idAbonnement : "+l.getIdAbonnement()).add("idUser :"+l.getIdUser());   
       
 f2.show();
        });  
  skip.addActionListener(e -> new AjoutLocationForm(res).show());
     
        return m;
}
}