package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.ArrayList;
import java.util.Random;

public class GuichetIG extends EtapeIG {

    private int nombreJeton ;
    private boolean Passens;
    private boolean Vergauche;

    /**
     * Contructeur du guicherIG
     * @param nom le nom du guichet
     * @param idf l'identifiant du guichet

     */
    public GuichetIG(String nom, FabriqueIdentifiant idf) {
        super(nom, idf, TailleComposants.getInstance ().getLargeurGuichet (), TailleComposants.getInstance ().getHauteurGuichet ());
        construirePtsControle ();
        this.nombreJeton = 2 ;
        this.Passens=false;
        this.Vergauche=false;
    }


    @Override
    public void construirePtsControle(){
        Random x = new Random ();
        Random y = new Random () ;
        this.posX =x.nextInt (900);
        this.posY =y.nextInt(900);

        int ptsControl = this.posX + TailleComposants.getInstance ().getLargeurEtape () ;
        this.pointsDeControle = new ArrayList<> ();
        int ptsControl2 = this.posY + TailleComposants.getInstance ().getHauteurEtape ()/2;
        this.pointsDeControle.add (new PointDeControleIG (ptsControl,ptsControl2,"C",this));
        ptsControl = this.posX  ;
        this.pointsDeControle.add (new PointDeControleIG (ptsControl,ptsControl2,"D",this));
    }


    /**
     * prends sens de guichet
     * @param passens boolean
     * @param Vergauche boolean
     */
    public void setPassens(boolean passens,boolean Vergauche) {
        this.Passens = passens;
        this.Vergauche=Vergauche;
    }

    /**
     * Getter qui permet de recuperer le nombre de jeton
     * @return un int
     */
    public int getNombreJeton() {
        return nombreJeton;
    }

    /**
     * Setter qui permet de modifier le nombre de jeton
     * @param nombreJeton
     */
    public void setNombreJeton(int nombreJeton) {
        this.nombreJeton = nombreJeton;
    }

    /**
     * Fonction qui regarde s'il s'agit bien d'un guichet
     * @return false
     */
    public boolean estUnGuichet(){
        return true;
    }

    /**
     * fonction qui regarde s'il y sens ou pas
     * @return boolean
     */
    public boolean isPassens() {
        return Passens;
    }

    /**
     * fonction qui regarde s'il vers gauche
     * @return boolean
     */
    public boolean isVergauche() {
        return Vergauche;
    }
}
