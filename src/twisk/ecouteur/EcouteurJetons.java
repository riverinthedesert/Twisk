package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurJetons implements EventHandler<ActionEvent> {

    private MondeIG monde ;

    /**
     * Constructeur de l'ecouteur sur les jetons
     * @param mnd le monde dans lequel on se situe
     */
    public EcouteurJetons(MondeIG mnd){
        this.monde = mnd ;

    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.monde.getSelectionGuichet ().size ()==1){
            TextInputDialog changerNom = new TextInputDialog ();
            changerNom.setTitle ("Changer le nombre de jetons du guichet selectionné");
            changerNom.setHeaderText ("Entrer un nombre de jetons");
            changerNom.setContentText ("nom :");
            Optional<String> result = changerNom.showAndWait ();
            result.ifPresent (name-> this.monde.getSelectionGuichet ().get (0).setNombreJeton (Integer.parseInt (name)));
            this.monde.notifierObservateur ();
        }
    }
}
