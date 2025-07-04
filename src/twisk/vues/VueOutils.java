package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button ;
import twisk.ecouteur.*;
import twisk.mondeIG.MondeIG;


public class VueOutils extends TilePane implements Observateur {
    private MondeIG mnd ;
    private Button button ;
    private Tooltip tooltip ;
    private Button buttonGuichet;
    private Tooltip tooltipGuichet;
    private Button buttonSimuler;
    private Button buttonArret;
    private ImageView imageSimuler;


    /**
     * Constructeur de la vueOutils
     * @param monde le monde dans lequel on se situe
     */
    public VueOutils(MondeIG monde){
        super ();
        this.mnd = monde;

        //ajout du bouton pour ajouter une activité
        this.button = new Button();
        this.tooltip = new Tooltip ("Ajouter une Activite");
        button.setTooltip (tooltip);
        button.setStyle ("-fx-border-color: #ff0000; -fx-border-width: 5px;");

        ImageView image = chargerImage ("plus");
        button.setGraphic (image);
        this.getChildren ().add(button);
        this.button.setOnAction(new EcouteurOutils (mnd));


        //ajout d'un bouton pour ajouter un guichet
        this.buttonGuichet = new Button ();
        this.tooltipGuichet = new Tooltip ("Ajouter un Guichet");
        buttonGuichet.setTooltip (tooltipGuichet);
        buttonGuichet.setStyle("-fx-border-color: green; -fx-border-width: 5px;");

        ImageView image2 = chargerImage ("ajout");
        buttonGuichet.setGraphic (image2);
        this.getChildren ().add (buttonGuichet);
        this.buttonGuichet.setOnAction (new EcouteurGuichet (mnd));


        //ajout d'un bouton pour ajouter un simulation
        this.buttonSimuler=new Button();

        //ImageView imageSimuler = null;
        if(this.mnd.getetatSimulation()) {
            imageSimuler = chargerImage("stop");
        }else{
            imageSimuler = chargerImage("simuler");
        }
        buttonSimuler.setOnAction(new EcouteurSimuler(mnd,imageSimuler));
        buttonSimuler.setGraphic(imageSimuler);
        this.getChildren ().add (buttonSimuler);


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

    /**
     * Fonction qui permet de mettre a jour les outils
     */
    @Override
    public void reagir() {
        if(this.mnd.getetatSimulation()) {
            imageSimuler = chargerImage("stop");
        }else{
            imageSimuler = chargerImage ("simuler");
        }
        buttonSimuler.setGraphic(imageSimuler);

        MondeIG mondeIG=this.mnd;

        buttonSimuler.setGraphic (imageSimuler);


    }
}
