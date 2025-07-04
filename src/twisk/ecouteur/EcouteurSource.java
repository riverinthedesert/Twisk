package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class EcouteurSource implements EventHandler {

    private MondeIG monde ;
    private EtapeIG etape;

    public EcouteurSource(MondeIG monde,EtapeIG etape){
        this.monde = monde ;
        this.etape = etape ;
    }

    @Override
    public void handle(Event event) {
        //Dragboard dragboard = this.etape.startDragAndDrop(TransferMode.COPY);
    }
}
