package twisk.vues;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.ecouteur.EcouteurActivite;
import twisk.ecouteur.EcouteurArc;
import twisk.ecouteur.EcouteurSelection;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

public class VueArcIG extends Pane implements Observateur {

    private Line ligne ;
    private MondeIG monde ;
    private Polyline fleche ;
    private ArcIG arc ;

    /**
     * Constructeur d'une vue d'un arc
     * @param monde le monde dans lequel on se situe
     * @param arc l'arc concerne
     */
    public VueArcIG(MondeIG monde,ArcIG arc){
        super();
        this.monde = monde ;
        this.arc = arc ;
        this.ligne = new Line (arc.getPts1 ().getAbcisseCentre (),arc.getPts1 ().getOrdonneeCentre (),arc.getPts2 ().getAbcisseCentre (),arc.getPts2 ().getOrdonneeCentre ());
        this.getChildren ().add (ligne);
        EventHandler<MouseEvent> mouseEventEventHandler = new EcouteurArc (monde,arc);
        this.setOnMouseClicked (mouseEventEventHandler);

    }

    /**
     * fonction qui permet de changer de style
     * @param couleur du style
     */
    public void setStyleLine(String couleur){
        this.ligne.setStyle (couleur);
    }

    @Override
    public void reagir() {

    }


}
