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
import com.mycompany.entity.Stock;
import com.mycompany.services.ServiceStock;

/**
 *
 * @author PC
 */
public class StockForm extends Form {
    Resources theme = UIManager.initFirstTheme("/theme");
    Form Current;
    
    public StockForm(Form previous,Resources res)
    {
        
           super("Stock",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjoutStockForm(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(l->new StockForm(Current,res).show());
        add(b7);     
             for (Stock s : new ServiceStock().getAllStocks()) {

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
            new AjoutStockForm(res).show();
        });
}
    
     public MultiButton addItem_Publicite(Stock s,Resources res) {
  
 MultiButton m = new MultiButton();
 //  String url = "http://localhost/image/"+c.getImagee();
   
     m.setTextLine4(s.getLibelle());
      m.setTextLine2(String.valueOf(s.getPrix()));
        m.setTextLine3(String.valueOf(s.getQuantite()));
      m.setText(s.getDisponibilite());
     // m.setText(String.valueOf(s.getIdProduit()));
        //m.setText(String.valueOf(s.getEmplacement()));

        m.setEmblem(theme.getImage("arrow.png"));
         
      m.setVisible(true);
        Button b5=new Button("supprimer Stock");
	setVisible(true); 
        Button b6=new Button("modifier Stock");
	setVisible(true);  
        //b.addActionListener(e -> new AffichageActivite(res).show());
         add(b5);
         add(b6);
         b6.addActionListener(l->new ModifierStockForm(res,s).show());
         
        
       
//Click delete icon
b5.addPointerPressedListener(l -> {
Dialog dig = new Dialog("Suprression");
if(dig.show("Suppression", "Vous voulez supprimezr ce reclamation ?","Annuler","Cui")) {
dig.dispose ();
}
else {
dig.dispose();
if (ServiceStock.getInstance().delete(s.getId())) {     
new StockForm(Current,res); 
}
}
});
Button skip = new Button("back");
        m.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
      
                 
     
     
            f2.add("Libelle : "+s.getLibelle()).add("Prix : "+s.getPrix()).add("Quantite : "+s.getQuantite()).add("Disponibilite : "+s.getDisponibilite()).add("Produits stockés : "+s.getIdProduit()).add("Emplacement du Stock : "+s.getEmplacement()).add(skip);   
       
 f2.show();

        });
   
  skip.addActionListener(e -> new AjoutStockForm(res).show());
     
        return m;
}
}
