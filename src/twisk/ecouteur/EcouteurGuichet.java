package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurGuichet implements EventHandler<ActionEvent> {

    private MondeIG monde ;
    /**
     * Constructeur de l'ecouteur
     * @param mnd le monde dans lequel on se situe
     */
    public EcouteurGuichet(MondeIG mnd) {
        this.monde = mnd ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //System.out.println ("ecouteur guichet");
        this.monde.ajouter ("Guichet");
        this.monde.notifierObservateur ();
    }
}

