package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class EcouteurEntree implements EventHandler {

    private MondeIG monde ;


    /**
     * Constructeur de l'ecouteur d'entree
     * @param monde
     */
    public EcouteurEntree(MondeIG monde){
        this.monde = monde ;
    }
    @Override
    public void handle(Event event) {
        this.monde.gestionEntree ();
        this.monde.notifierObservateur ();
    }
}
