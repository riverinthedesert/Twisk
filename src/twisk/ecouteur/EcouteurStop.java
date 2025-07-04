package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;

public class EcouteurStop implements EventHandler<ActionEvent> {

    private MondeIG monde ;

    public EcouteurStop(MondeIG mnd) {
        this.monde = mnd ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ThreadsManager.getInstance().detruireTout();
        KitC kitC=new KitC();
        kitC.killC();
        //this.monde.
    }
}
