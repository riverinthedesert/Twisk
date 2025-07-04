package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurRenommer implements EventHandler {

    MondeIG monde ;

    /**
     * Constructeur de l'ecouteur renpmmer
     * @param monde le monde concerné
     */
    public EcouteurRenommer(MondeIG monde){
        this.monde = monde ;
    }


    @Override
    public void handle(Event event) {
        if (this.monde.getSelectionAct ().size ()==1){
            TextInputDialog changerNom = new TextInputDialog ();
            changerNom.setTitle ("Changer le nom de l'activite selectionnée");
            changerNom.setHeaderText ("Entrer un nouveau nom");
            changerNom.setContentText ("nom :");
            Optional<String> result = changerNom.showAndWait ();
            result.ifPresent (name-> this.monde.getSelectionAct ().get (0).setNom (name));
            this.monde.notifierObservateur ();
        }
    }
}
