package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Paint;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

//classe mere
abstract class VueEtapeIG extends VBox implements Observateur {
    //private Label label;
    private EtapeIG etape ;
    private MondeIG mnd ;

    /**
     * Constructeur de la vue d'une etape
     * @param monde le monde dans lequel on se situe
     * @param etape l'etape qu'on veut visualiser
     */
    public VueEtapeIG(MondeIG monde, EtapeIG etape){
        super();

        this.mnd = monde ;
        this.etape = etape ;

        this.reagir ();

    }

    /**
     * Fonction qui met a jour la vu de l'activite
     */
    @Override
    public void reagir() {
        this.setOnDragDetected (mouseEvent -> {
            Dragboard dragboard = this.startDragAndDrop (TransferMode.ANY);
            ClipboardContent content = new ClipboardContent ();
            content.putString ("Une Activite");


            dragboard.setContent (content);
            mouseEvent.consume ();
        });

    }

}
