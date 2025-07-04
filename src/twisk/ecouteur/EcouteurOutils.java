package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurOutils implements EventHandler {

    private MondeIG monde ;


    /**
     * Constructeur de l'ecouteur
     * @param monde le monde dans lequel on se situe
     */
    public EcouteurOutils(MondeIG monde){
        this.monde = monde ;

    }

    @Override
    public void handle(Event event) {

        this.monde.ajouter("Activité");
        this.monde.notifierObservateur ();

    }
}
