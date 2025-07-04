package twisk.ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurPanneau implements EventHandler {

    private MondeIG monde ;

    public EcouteurPanneau(MondeIG monde){
        this.monde = monde ;
    }
    @Override
    public void handle(Event event) {

    }
}
