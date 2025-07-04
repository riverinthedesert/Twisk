package twisk.ecouteur;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.exceptions.ArcException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class EcouteurPointDeControle implements EventHandler {
    private PointDeControleIG pts1 ;
    private MondeIG monde ;



    /**
     * constructeur de l'ecouteur
     * @param monde le monde dans lequel on se situe
     * @param pts le pts de controle associe
     */
    public EcouteurPointDeControle(MondeIG monde, PointDeControleIG pts){
        this.monde = monde ;
        this.pts1 = pts ;

    }

    @Override
    public void handle(Event event) {


        try{
            //verifier que le pts1 et le pts2 ne sont pas dans la meme etape
            this.monde.addPointArc (this.pts1);


        }catch(ArcException a){
            Alert boiteDialogue = new Alert (Alert.AlertType.INFORMATION);
            boiteDialogue.setTitle("Erreur");
            boiteDialogue.setHeaderText ("Problème pour implémenter cet arc");
            boiteDialogue.setContentText ("Il est impossible de créer cet arc. " + a.getMessage());
            boiteDialogue.show ();
            new PauseTransition (Duration.millis (100));
        }

        this.monde.notifierObservateur ();
    }
}
