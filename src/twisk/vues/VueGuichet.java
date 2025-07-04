package twisk.vues;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import twisk.ecouteur.EcouteurActivite;
import twisk.ecouteur.EcouteurGuichet;
import twisk.ecouteur.EcouteurSelection;
import twisk.ecouteur.EcouteurSelectionGuichet;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

import java.util.Iterator;


public class VueGuichet extends VueEtapeIG implements Observateur{
    private MondeIG monde;
    private GuichetIG guichet;
    private Label label ;
    private boolean PasSens;
    private ImageView sens;

    public VueGuichet(MondeIG monde, GuichetIG guichet){

        super (monde,guichet);

        this.monde = monde ;
        this.guichet = guichet ;
        this.label = new Label (guichet.getNom () + " : " + guichet.getNombreJeton () + "jetons ");
        this.PasSens=true;
        this.sens=new ImageView();

        this.getChildren ().addAll (label);

            Rectangle contientClient = new Rectangle ();
            contientClient.relocate (guichet.getPosX (),guichet.getPosY ());
            contientClient.setStyle ("-fx-border-width:3;");
            contientClient.setStyle ("-fx-border-color: red; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
            contientClient.setHeight (TailleComposants.getInstance ().getHauteurGuichet ());
            contientClient.setWidth (TailleComposants.getInstance ().getLargeurGuichet ());
            this.getChildren ().add(contientClient);

       //}
        EventHandler<MouseEvent> mouseEventEventHandler = new EcouteurSelectionGuichet (monde,guichet);
        this.setOnMouseClicked (mouseEventEventHandler);

    }


    @Override
    public void reagir() {
     /*   if(this.guichet.isPassens()) {
            this.sens = chargerImage("flecheGauche");
            this.getChildren().add(sens);
        }*/
    }
}
