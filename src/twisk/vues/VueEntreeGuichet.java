package twisk.vues;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.FabriqueIdentifiant;

public class VueEntreeGuichet extends VueGuichet {
    /**
     * Constructeur de la vue de l'activite
     *
     * @param monde le monde dans lequel on se situe
     * @param et    l'etape que l'on construit
     */
    public VueEntreeGuichet(MondeIG monde, EtapeIG et) {
        super (monde, (GuichetIG) et);
        ImageView image = chargerImage ("e");
        this.getChildren() .add(image);
    }


    /**
     * Fonction qui permet de charger les images utilisées dans le code
     * @param url le chemin d'acces a l'image
     * @return une imageView
     */
    private ImageView chargerImage(String url){
        Image image = new Image ("twisk/ressources/images/"+url+".jpg");
        return new ImageView (image);
    }
}
