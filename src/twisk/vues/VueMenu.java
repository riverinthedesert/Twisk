package twisk.vues;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import twisk.ecouteur.*;
import twisk.mondeIG.MondeIG;

public class VueMenu extends MenuBar implements Observateur{
    private MondeIG mnd;

    /**
     * constructeur vuemenu
     * @param mnd le monde dans lequel on se situe
     */
    public VueMenu(MondeIG mnd){
        super();
        this.mnd = mnd;
        /***********************MENU FICHIER*********************************/
        Menu fichier = new Menu ("Fichier");
        MenuItem sauvegarder = new MenuItem ("Sauvergarder");
        sauvegarder.setOnAction (new EcouteurSauvegarder(mnd));
        fichier.getItems ().add (sauvegarder);
        MenuItem charger = new MenuItem ("Charger");
        charger.setOnAction (new EcouteurCharger(mnd));
        fichier.getItems ().add (charger);
        MenuItem quitter = new MenuItem ("Quitter");
        quitter.setOnAction (new EcouteurQuitter (mnd));
        fichier.getItems ().add (quitter);
        /***********************MENU EDITION*********************************/
        Menu edition = new Menu("Édition");
        MenuItem supprSelect = new MenuItem ("Supprimer la séléction");
        supprSelect.setOnAction (new EcouteurSelection (mnd));
        edition.getItems ().add (supprSelect);
        MenuItem renomAct = new MenuItem ("Renommer la selection");
        renomAct.setOnAction (new EcouteurRenommer (mnd));
        edition.getItems ().add (renomAct);
        MenuItem effacerSelection = new MenuItem ("Effacer la selection");
        effacerSelection.setOnAction (new EcouteurEffacer (mnd));
        edition.getItems ().add(effacerSelection);
        MenuItem renomGuichet = new MenuItem ("Modifier le nom du guichet");
        renomGuichet.setOnAction (new EcouteurRenommerGuichet(mnd));
        edition.getItems ().add (renomGuichet);
        /***********************MENU MONDE*********************************/
        Menu monde = new Menu ("Monde");
        MenuItem entree = new MenuItem ("Entree");
        entree.setOnAction (new EcouteurEntree (mnd));
        monde.getItems ().add (entree);
        MenuItem sortie = new MenuItem ("Sortie");
        sortie.setOnAction (new EcouteurSortie (mnd));
        monde.getItems ().add (sortie);
        /***********************MENU PARAMETRE*********************************/
        Menu param = new Menu("Paramètres");
        MenuItem delai = new MenuItem ("delai et écart");
        delai.setOnAction (new EcouteurDelai(mnd));
        param.getItems ().add (delai);

        MenuItem nbJeton = new MenuItem ("modifier le nombre de jeton");
        nbJeton.setOnAction (new EcouteurJetons(mnd));
        param.getItems ().add (nbJeton);

        this.getMenus ().addAll (fichier,edition,monde,param);



    }

    @Override
    public void reagir() {

    }
}
