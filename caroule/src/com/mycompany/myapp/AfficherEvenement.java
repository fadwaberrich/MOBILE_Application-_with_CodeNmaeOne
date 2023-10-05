/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Activite;
import com.mycompany.entity.Evenement;
import com.mycompany.services.ServiceEvenement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class AfficherEvenement extends BaseForm {
    Form current;
    Evenement c;
public ArrayList<Evenement> Evenements;
public ArrayList<Evenement> Event;
    public AfficherEvenement(Form previous,Resources res) {
        super("liste evenements", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste evenement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
     
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
           //TextField contenu = new TextField("", "search event", 20, TextArea.TEXT_CURSOR);      
           //this.add(contenu); 
         // Button a= new Button("recherche"); 
          //this.add(a); 
          //addGUIs(res);
           
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_EXIT_TO_APP, e -> new NewsfeedForm(res).show());
        ButtonGroup barGroup = new ButtonGroup();
       // RadioButton all = RadioButton.createToggle("All", barGroup);
        //all.setUIID("SelectBar");
        RadioButton Evenement = RadioButton.createToggle("Evenement", barGroup);
        Evenement.setUIID("SelectBar");
       // RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        //popular.setUIID("SelectBar");
       // RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        //myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, Evenement),
                FlowLayout.encloseBottom(arrow)
        ));
        
       // all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            //updateArrowPosition(all, arrow);
        });
        //bindButtonSelection(all, arrow);
        bindButtonSelection(Evenement, arrow);
       // bindButtonSelection(popular, arrow);
        //bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
 
//this.getToolbar().addSearchCommand(e -> {
  //      String text = (String) e.getSource(); 
//  Evenements = ServiceEvenement.getInstance().recherche(text);
   /*for (int iter = 0; iter < Evenements.size(); iter++) {
  addButton(res.getImage("news-item-1.jpg"),Evenements.get(iter).getNom().toString()+ "\n" +Evenements.get(iter).getDateD().toString()+ "\n" +Evenements.get(iter).getDateF().toString()+ "\n" +Evenements.get(iter).getLieu().toString()+"\n" +Evenements.get(iter).getType().toString()+"\n" +Evenements.get(iter).getNb_participants()+"\n" +Evenements.get(iter).getNb_places(), false, 26, 32,res,+Evenements.get(iter).getId());          
   }*/
  
  //  });*/

  this.show();
  TextField search = new TextField("", "Chercher un evenement par nom");
this.add(search);
 RadioButton recherche = RadioButton.createToggle("resultat recherche", barGroup);
 recherche.setUIID("SelectBar");

       SpanLabel sp = new SpanLabel();
       
Evenements =ServiceEvenement.getInstance().getAllEvenements();
    for (Evenement e :Evenements)
    {
     addButton(res.getImage("news-item-1.jpg"),e.getNom().toString()+ "\n" +e.getDateD().toString()+ "\n" +e.getDateF().toString()+ "\n" +e.getLieu().toString()+"\n" +e.getType().toString()+"\n" +e.getNb_participants()+"\n" +e.getNb_places(), false, 26, 32,res,+e.getId());
} 

  add(recherche);
 search.addDataChangedListener((d, t) -> { 
          for (Evenement e :Evenements){
    if (e.getNom().equals(search.getText())) {
    addButton(res.getImage("news-item-1.jpg"),e.getNom().toString()+ "\n" +e.getDateD().toString()+ "\n" +e.getDateF().toString()+ "\n" +e.getLieu().toString()+"\n" +e.getType().toString()+"\n" +e.getNb_participants()+"\n" +e.getNb_places(), false, 26, 32,res,+e.getId());
}}});


        Form pre = null;
  
    }
    TextField searchTF;
     ArrayList<Component> componentModels;
   private void addGUIs(Resources res) {
        ArrayList<Evenement> listHotels = ServiceEvenement.getInstance().getAllEvenements();
        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher un evenement par nom");
        searchTF.addDataChangedListener((d, t) -> {
           /* if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }*/
            componentModels = new ArrayList<>();
            for (Evenement listHotel : listHotels) {
                if (listHotel.getNom().startsWith(searchTF.getText())) {
                    addButton(res.getImage("news-item-1.jpg"),listHotel.getNom().toString()+ "\n" +listHotel.getDateD().toString()+ "\n" +listHotel.getDateF().toString()+ "\n" +listHotel.getLieu().toString()+"\n" +listHotel.getType().toString()+"\n" +listHotel.getNb_participants()+"\n" +listHotel.getNb_places(), false, 26, 32,res,+listHotel.getId());
                  
                }
                else{
                    addButton(res.getImage("news-item-1.jpg"),listHotel.getNom().toString()+ "\n" +listHotel.getDateD().toString()+ "\n" +listHotel.getDateF().toString()+ "\n" +listHotel.getLieu().toString()+"\n" +listHotel.getType().toString()+"\n" +listHotel.getNb_participants()+"\n" +listHotel.getNb_places(), false, 26, 32,res,+listHotel.getId());
                }
            }
            this.revalidate();
        });
        this.add(searchTF);

       /* if (listHotels.size() > 0) {
            for (Evenement listHotel : listHotels) {
               addButton(res.getImage("news-item-1.jpg"),listHotel.getNom().toString()+ "\n" +listHotel.getDateD().toString()+ "\n" +listHotel.getDateF().toString()+ "\n" +listHotel.getLieu().toString()+"\n" +listHotel.getType().toString()+"\n" +listHotel.getNb_participants()+"\n" +listHotel.getNb_places(), false, 26, 32,res,+listHotel.getId());
                
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }*/
    }
 
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
      
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                        
                )
                    
            );
 
        swipe.addTab("", page1);
    }
   
  private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount, Resources res,int idevent) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
        
       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
        Button b=new Button("Afficher Activité");
	getContentPane().add(b);
	setVisible(true);      
        b.addActionListener(e -> new AffichageActivite(idevent,res).show());
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
        
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

  

  
}
