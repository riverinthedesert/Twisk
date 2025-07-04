package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurSortie implements EventHandler{

    private MondeIG monde;

    /**
     * Constructeur de l'ecouteur sortie
     * @param monde le monde concerne
     */
    public EcouteurSortie(MondeIG monde){
        this.monde = monde ;
    }
    @Override
    public void handle(Event event) {
        this.monde.gestionSortie ();
        this.monde.notifierObservateur ();

    }
}
