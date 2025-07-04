package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

import java.io.*;

public class EcouteurSauvegarder implements EventHandler<ActionEvent> {

    private MondeIG monde ;
    public EcouteurSauvegarder(MondeIG mnd) {
        this.monde = mnd ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ObjectOutputStream oos = null;
        try{
            FileOutputStream fichier = new FileOutputStream ("monde.ser");
            oos = new ObjectOutputStream (fichier);
            oos.writeObject (this.monde);
            oos.flush ();
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }finally {
            try {
                if (oos != null){
                    oos.flush ();
                    oos.close ();
                }
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
}
