package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurSelection implements EventHandler {

    private MondeIG monde ;

    /**
     * Constructeur de l'ecouteur de selection
     * @param monde le monde concerne
     */
    public EcouteurSelection(MondeIG monde){
        this.monde = monde ;
    }


    @Override
    public void handle(Event event) {

        this.monde.supprimerSelection ();
        this.monde.notifierObservateur ();
    }
}
