package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurEffacer implements EventHandler {

    private MondeIG monde ;

    /**
     * Constructeur de l'ecouteur effacer
     * @param monde le monde concerné
     */
    public EcouteurEffacer(MondeIG monde){
        this.monde = monde ;
    }


    @Override
    public void handle(Event event) {
        this.monde.oublierSelection();
        this.monde.notifierObservateur ();
    }
}
