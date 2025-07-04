package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurRenommerGuichet implements EventHandler<ActionEvent> {

    private MondeIG monde ;

    /**
     * Constructeur de l'ecouteur renommerGuichet
     * @param mnd
     */
    public EcouteurRenommerGuichet(MondeIG mnd) {
        this.monde = mnd ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.monde.getSelectionGuichet ().size ()==1){
            TextInputDialog changerNom = new TextInputDialog ();
            changerNom.setTitle ("Changer le nom du guichet selectionné");
            changerNom.setHeaderText ("Entrer un nouveau nom");
            changerNom.setContentText ("nom :");
            Optional<String> result = changerNom.showAndWait ();
            result.ifPresent (name-> this.monde.getSelectionGuichet ().get (0).setNom (name));
            this.monde.notifierObservateur ();
        }
    }

}


