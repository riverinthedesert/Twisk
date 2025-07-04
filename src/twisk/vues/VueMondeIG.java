package twisk.vues;



import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.ecouteur.EcouteurActivite;
import twisk.ecouteur.EcouteurPointDeControle;
import twisk.monde.Monde;
import twisk.mondeIG.*;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;
import twisk.simulation.Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class VueMondeIG  extends Pane implements Observateur {
    private MondeIG monde ;
    private int nbclient;
  //  private ArrayList<Circle> clients;



    /**
     * Constructeur de la vue de l'ensemble du monde
     * @param monde le monde que l'on veut visualiser
     */
    public VueMondeIG(MondeIG monde){
        this.monde = monde ;
        this.nbclient=monde.getNbclient();
        this.setOnDragOver (dragEvent -> {

            Dragboard dragboard = dragEvent.getDragboard ();
            if(dragEvent.getGestureSource () != this && dragEvent.getDragboard ().hasString ()){
                dragEvent.acceptTransferModes (TransferMode.ANY);

            }

            dragEvent.consume ();

        }
        );

        this.setOnDragDropped (dragEvent -> {
            Dragboard dragboard = dragEvent.getDragboard ();
            boolean succes = false ;
            if(dragboard.hasString ()){
                System.out.println ("dnd : " + dragboard.getString ());
                succes = true;
            }
            dragEvent.setDropCompleted (succes);
            dragEvent.consume ();
        });


    }

    /**
     * Fonction qui permet de charger les images utilisées dans le code
     * @param url le chemin d'acces a l'image
     * @return une imageView
     */
    private ImageView chargerImage(String url){
        Image image = new Image ("twisk/ressources/images/"+url+".jpg");
        return new ImageView (image);
    }

    public void miseAJourGuichet(EtapeIG et){

        HBox contour = new HBox ();
        VueGuichet vueGuichet = null ;
        if (et.estUnGuichet ()) {
            if (this.monde.getListeEntree ().contains (et)) {
                vueGuichet = new VueEntreeGuichet (monde, et);
            } else if (this.monde.getListeSortie ().contains (et)) {
                vueGuichet = new VueSortieGuichet (monde, et);
            } else {//else if(this.monde.){
                vueGuichet = new VueGuichet (monde, (GuichetIG) et);
            }
            //vueGuichet = new VueGuichet (monde, (GuichetIG) et);

            vueGuichet.setAlignment (Pos.CENTER);
            contour.relocate (et.getPosX (), et.getPosY ());
            contour.setPrefSize (TailleComposants.getInstance ().getLargeurEtape (), TailleComposants.getInstance ().getHauteurEtape ());
            contour.setStyle ("-fx-border-color: yellow; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-radius: 10 10 10 10; -fx-border-width:3;");
            if(et.isPassens()){
                if(et.isVergauche()) {
                    vueGuichet.getChildren().add(chargerImage("flecheGauche"));
                }
                else {
                    vueGuichet.getChildren().add(chargerImage("flecheDroite"));
                }
            }
            contour.getChildren ().add (vueGuichet);
            for (EtapeIG etape : monde.getSelectionGuichet ()) {
                if (etape.equals (et)) {
                    contour.setStyle ("-fx-border-color: green ;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-radius: 10 10 10 10; -fx-border-width:3px;");
                }
            }
        }
        this.getChildren ().add (contour);
    }

    /**
     * Fonction qui permet de faire la mise a jour de toutes les activite
     * @param et une etape
     */
    public void miseAJourActivite(EtapeIG et){
        if(et.estUneActivite ()) {

            HBox contour = new HBox ();
            VueEtapeIG vueActivite = null;

            if (this.monde.getListeEntree ().contains (et)) {
                vueActivite = new VueEntree (monde, et);
            } else if (this.monde.getListeSortie ().contains (et)) {
                vueActivite = new VueSortie (monde, et);
            } else {//else if(this.monde.){
                vueActivite = new VueActiviteIG (monde, et);
            }

            vueActivite.setAlignment (Pos.CENTER);

            contour.relocate (et.getPosX (), et.getPosY ());
            contour.setPrefSize (TailleComposants.getInstance ().getLargeurEtape (), TailleComposants.getInstance ().getHauteurEtape ());
            contour.setStyle ("-fx-border-color: #0059FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-radius: 10 10 10 10; -fx-border-width:3;");
            contour.getChildren ().add (vueActivite);
            for (EtapeIG etape : monde.getSelectionAct ()) {
                if (etape.equals (et)) {
                    contour.setStyle ("-fx-border-color: green ;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-radius: 10 10 10 10; -fx-border-width:3px;");
                }
            }


            this.getChildren ().add (contour);

        }
    }

    /**
     * Fonction qui permet de mettre a jour tous les arcs
     *
     */
    public void miseAJourArc(){
        for(ArcIG arc : monde.getListeArc ()) {

            VueArcIG vueArc = new VueArcIG (monde, arc);
            for (ArcIG l : monde.getSelectionArc ()){
                if(l.equals (arc)){
                    vueArc.setStyleLine ("-fx-background-color: red; ");

                }
            }
            this.getChildren ().add (vueArc);
        }
    }

    /**
     * Fonctionn qui permet de mettre a jour tous les points de controle
     * @param et une etape
     */
    public void miseAJourPointDeControle(EtapeIG et){
        for(PointDeControleIG pc : et){
            VuePointDeControleIG vuePtsControl = new VuePointDeControleIG (monde,pc);
            vuePtsControl.setStyle ("-fx-background-color: #EE82EE; -fx-color: red ;");
            vuePtsControl.setFill (Color.BLUEVIOLET);
            this.getChildren ().add (vuePtsControl);

        }
    }

    /**
     * fonction qui permet de changer de couleur
     * @param vue la vue
     */
    public void changerCouleur(VuePointDeControleIG vue){
        vue.setFill (Color.GREEN);
        this.getChildren().add(vue);
    }



    /**
     * Fonction qui permet de mettre a jour la vue de l'ensemble du monde
     */
    @Override
    public void reagir() {
        this.getChildren().clear();

        miseAJourArc();
        for (EtapeIG et : monde) {
            //System.out.println (et);
            miseAJourActivite(et);
            miseAJourGuichet(et);
            miseAJourPointDeControle(et);
        }



        Pane panneau = this;
        Runnable command = new Runnable(){
            @Override
            public void run() {
                Iterator iterator=monde.getSimulation().iterator();
                while(iterator.hasNext()){
                    Client client= (Client) iterator.next();
                    Circle circle=new Circle();
                    if(client.getEtape().getNom()=="entree"||client.getEtape().getNom()=="sortie") {
                        circle.setCenterX(0);
                        circle.setCenterY(0);
                        circle.setRadius(5);
                        circle.setFill(Color.YELLOW);
                        panneau.getChildren().add(circle);
               //         System.out.println("entree");
                    }
                    else{
                        circle.setCenterX(monde.getCorrespondanceEtapes().get(client.getEtape()).getPosX() + client.getRang() * 12 + 20);
                        circle.setCenterY(monde.getCorrespondanceEtapes().get(client.getEtape()).getPosY() + 60);
                        circle.setRadius(5);
                        circle.setFill(Color.YELLOW);
                        panneau.getChildren().add(circle);
                  //      System.out.println(client.getRang());
                    }
                }
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }

    }
}
