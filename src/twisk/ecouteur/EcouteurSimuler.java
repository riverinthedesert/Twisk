package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import twisk.exceptions.MondeException;
import twisk.mondeIG.MondeIG;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;

public class EcouteurSimuler implements EventHandler {
    private MondeIG monde ;
    private ImageView image;

    /**
     * Constructeur de l'ecouteur simuler
     * @param monde le monde concerne
     * @param image l'image concerne
     */
    public EcouteurSimuler(MondeIG monde,ImageView image){
        this.monde = monde ;
        this.image = image;
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



    @Override
    public void handle(Event event) {


        try {
            if(!monde.getetatSimulation()) {

                monde.setEtatSimulation (false);
                image = chargerImage("stop");
                this.monde.simuler();

            }else{

                monde.setEtatSimulation (true);
                image = chargerImage("simuler");
                ThreadsManager.getInstance().detruireTout();
                KitC kitC=new KitC();
                kitC.killC();
                this.monde.resetMonde ();


                ThreadsManager.getInstance().arret();
            }
            this.monde.notifierObservateur ();
        } catch (MondeException e) {
            e.printStackTrace();
        }
    }
}
