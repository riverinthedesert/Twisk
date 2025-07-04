package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

public class EcouteurArc implements EventHandler {

    private MondeIG monde ;
    private ArcIG arc ;

    /**
     * Constructeur de l'ecouteur d'un arc
     * @param monde le monde concerné
     * @param arc l'arc concerné
     */
    public EcouteurArc(MondeIG monde,ArcIG arc){
        this.monde = monde ;
        this.arc = arc ;
    }

    @Override
    public void handle(Event event) {
        this.monde.gestionArc (this.arc);
        this.monde.notifierObservateur ();
    }
}
