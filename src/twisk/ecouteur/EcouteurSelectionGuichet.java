package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

public class EcouteurSelectionGuichet implements EventHandler<MouseEvent> {

    private MondeIG monde ;
    private GuichetIG guichet ;

    /**
     * Constructeur de l'ecouteur de selection guichet
     * @param monde le monde concerne
     * @param guichet le guihet concerne
     */
    public EcouteurSelectionGuichet(MondeIG monde, GuichetIG guichet) {
        this.monde = monde ;
        this.guichet = guichet ;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.monde.gestionGuichet (this.guichet);
        this.monde.notifierObservateur ();
    }
}
