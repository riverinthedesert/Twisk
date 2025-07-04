package twisk.vues;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import twisk.ecouteur.EcouteurActivite;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

//classe fille
public class VueActiviteIG extends VueEtapeIG implements Observateur {

    private Label label ;


    /**
     * Constructeur de la vue de l'activite
     * @param monde le monde dans lequel on se situe
     * @param etape l'etape que l'on construit
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super (monde, etape);

        this.label = new Label (etape.getNom ()+" : "+etape.getDelai ()+" +/- "+etape.getEcart ()+ " temps");
        HBox contientClient = new HBox ();
        contientClient.relocate (etape.getPosX (),etape.getPosY ());
        contientClient.setStyle ("-fx-border-color: #0059FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; ");

        contientClient.setPrefSize (TailleComposants.getInstance ().getLargeurActivite (),TailleComposants.getInstance ().getHauteurActivite ());

        this.getChildren ().add (label);
        this.getChildren ().add(contientClient);


        EventHandler<MouseEvent> mouseEventEventHandler = new EcouteurActivite (monde,etape);
        this.setOnMouseClicked (mouseEventEventHandler);

    }

    /**
     * Fonction qui met a jour la vue de l'activité
     */
    public void reagir(){
        super.reagir ();

    }
}
