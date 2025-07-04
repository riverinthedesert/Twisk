package twisk.vues;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import twisk.ecouteur.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class VuePointDeControleIG extends Circle implements Observateur {

    private MondeIG monde ;
    private PointDeControleIG pc;

    /**
     * constructeur de la vue de point de controle
     */
    public VuePointDeControleIG(MondeIG monde,PointDeControleIG pc ){
        super();
        this.monde = monde ;
        this.pc = pc;
        this.setCenterX (pc.getAbcisseCentre ());
        this.setCenterY (pc.getOrdonneeCentre ());
        this.setRadius (5.0);
        //this.setStyle ("-fx-background-color: red;"); //#EE82EE
        //this.setStyle ("-fx-background-color: #EE82EE; -fx-color: red ; -fx-background-position : center ;");

        EventHandler<MouseEvent> mouseEventEventHandler = new EcouteurPointDeControle (monde,this.pc);
        this.setOnMouseClicked (mouseEventEventHandler);



    }




    @Override
    public void reagir() {

    }
}
