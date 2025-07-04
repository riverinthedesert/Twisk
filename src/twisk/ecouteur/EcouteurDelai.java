package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import twisk.exceptions.DelaiException;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurDelai implements EventHandler {

    private MondeIG monde ;

    /**
     * Constructeur de l'ecouteur delai
     * @param monde le monde concerné
     */
    public EcouteurDelai(MondeIG monde){
        this.monde = monde ;
    }

    /**
     * Fonction qui verifie si c'est un int ou pas
     * @param chaine la chaine concerné
     * @return un booleen
     * @throws DelaiException
     */
    private boolean verifSiInt(String chaine) throws DelaiException{
        Boolean verif = true ;
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e){
            verif = false ;
            throw new DelaiException ("le champs a ajouter doit etre obligatoirement un int");
        }
        return verif;


    }


    @Override
    public void handle(Event event) {

            if (this.monde.getSelectionAct ().size ()==1) {
                TextInputDialog ajoutDelai = new TextInputDialog ();
                ajoutDelai.setTitle ("Ajouter un delai");
                ajoutDelai.setHeaderText ("Entrer un delai");
                ajoutDelai.setContentText ("delai :");
                Optional<String> result = ajoutDelai.showAndWait ();

                if (result.isPresent ()) {
                    try {
                        if(verifSiInt (result.get ())){
                            result.ifPresent (delai -> this.monde.getSelectionAct ().get (0).setDelai (Integer.parseInt (delai)));
                        }
                    }
                    catch (DelaiException e) {
                            Alert boiteDialogue = new Alert (Alert.AlertType.INFORMATION);
                            boiteDialogue.setTitle("Erreur");
                            boiteDialogue.setHeaderText ("Problème pour ajouter un delai");
                            boiteDialogue.setContentText ("Il est impossible d'ajouter ce delai. " + e.getMessage());
                            boiteDialogue.show ();
                        }



                TextInputDialog ajoutEcart = new TextInputDialog ();
                ajoutEcart.setTitle ("Ajouter un ecart");
                ajoutEcart.setHeaderText ("Entrer un ecart");
                ajoutEcart.setContentText ("ecart :");
                Optional<String> ec = ajoutEcart.showAndWait ();
                if (ec.isPresent ()) {

                    try {
                        if(verifSiInt (ec.get ())){
                            ec.ifPresent (ecart -> this.monde.getSelectionAct ().get (0).setEcart (Integer.parseInt (ecart)));
                        }
                    } catch (DelaiException e) {
                        Alert boiteDialogue = new Alert (Alert.AlertType.INFORMATION);
                        boiteDialogue.setTitle("Erreur");
                        boiteDialogue.setHeaderText ("Problème pour ajouter un écart");
                        boiteDialogue.setContentText ("Il est impossible d'ajouter cet écart. " + e.getMessage());
                        boiteDialogue.show ();
                    }

                }


            }
        }
        this.monde.notifierObservateur ();
        }


}
