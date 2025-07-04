package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.monde.Monde;
import twisk.mondeIG.MondeIG;

import java.io.*;

public class EcouteurCharger implements EventHandler<ActionEvent> {
    private MondeIG monde ;
    public EcouteurCharger(MondeIG mnd) {
        this.monde = mnd ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
     //   monde=getObjFromFile();
        ObjectInputStream ois= null;
        try {
            ois = new ObjectInputStream(new FileInputStream("monde.ser"));
            monde=(MondeIG)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MondeIG getObjFromFile(){
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("monde.ser"));

            MondeIG person=(MondeIG)ois.readObject();

            return person;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
