package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class EcouteurActivite implements EventHandler {

    private MondeIG monde ;
    private EtapeIG et ;

    /**
     * Constructeur de l'ecouteur d'une activite
     * @param monde le monde dans lequel on se situe
     * @param et l'etape concernee
     */
    public EcouteurActivite(MondeIG monde, EtapeIG et){
        this.monde = monde ;
        this.et = et ;

    }

    @Override
    public void handle(Event event) {
        this.monde.gestionActivite (this.et);
        this.monde.notifierObservateur ();
    }
}
