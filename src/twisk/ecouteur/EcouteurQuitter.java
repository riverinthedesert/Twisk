package twisk.ecouteur;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;

public class EcouteurQuitter implements EventHandler{

    private MondeIG monde ;

    /**
     * Constructeur de l'ecouteur quitter
     * @param monde le monde dans lequel on se situe
     */
    public EcouteurQuitter(MondeIG monde){
        this.monde = monde ;
    }

    @Override
    public void handle(Event event) {
        ThreadsManager.getInstance().detruireTout();
        monde.getSimulation().getKitC().killC();
        Platform.exit ();

    }
}
