package twisk;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.FabriqueIdentifiant;
import twisk.vues.VueActiviteIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

import static javafx.application.Application.launch;

public class MainTwisk extends Application {

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("TWISK");
        BorderPane root = new BorderPane();
        MondeIG mnd = new MondeIG ();

        VueOutils vo = new VueOutils (mnd);
        mnd.ajouterObservateur (vo);
        root.setBottom (vo);

        VueMenu menu = new VueMenu (mnd);
        mnd.ajouterObservateur (menu);
        root.setTop (menu);

        VueMondeIG vueMonde = new VueMondeIG (mnd);
        mnd.ajouterObservateur (vueMonde);
        //vueMonde.mettreAJour ();


        root.setCenter (vueMonde);



        Scene sc = new Scene(root, 1000, 1000);
        primaryStage.setScene(sc);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}