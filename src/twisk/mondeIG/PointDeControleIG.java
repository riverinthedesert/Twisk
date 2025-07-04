package twisk.mondeIG;

import com.sun.javafx.scene.paint.GradientUtils;

import java.io.Serializable;

public class PointDeControleIG implements Serializable {
    private int abcisseCentre;
    private int ordonneeCentre ;
    private String identifiant;
    private EtapeIG etape ;

    /**
     * Constructeur d'un point de controle
     * @param abscisse l'abcisse du point centrale
     * @param ordonnee l'ordonne du point centrale
     * @param identifiant l'identifiant unique du point
     * @param etape l'etape a laquelle il est rattache
     */
    public PointDeControleIG(int abscisse, int ordonnee, String identifiant,EtapeIG etape){
        this.abcisseCentre = abscisse;
        this.ordonneeCentre = ordonnee;
        this.identifiant = identifiant;
        this.etape = etape;
    }

    public boolean equals(PointDeControleIG pts){
        return (this.abcisseCentre == pts.getAbcisseCentre ()) && (this.ordonneeCentre == pts.getOrdonneeCentre ()) && (this.identifiant.equals(pts.getIdentifiant ())) && (this.etape.equals (pts.getEtape ()));

    }
    /**
     * Fonction get qui permet de recuperer l'ordonner du pts centrale
     * @return l'ordonne
     */
    public int getOrdonneeCentre() {
        return ordonneeCentre;
    }

    /**
     * Fonction qui retourne l'identifiant du point de controle
     * @return l'identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Fonction get etape qui retourne l'etape a laquelle le pts de controle est attribue
     * @return l'etape
     */
    public EtapeIG getEtape() {
        return etape;
    }

    /**
     * Fonction qui retourne l'abscisse du point centrale
     * @return l'abcisse du point
     */
    public int getAbcisseCentre() {
        return abcisseCentre;
    }

    /**
     * Foncton d'affichage
     * @return un toString
     */
    public String toString(){
        StringBuilder sb = new StringBuilder ();
        sb.append ("abs : ").append (this.abcisseCentre).append (" ord : ").append (this.ordonneeCentre).append ("\n");
        sb.append (this.identifiant).append ("\n");
        sb.append ("etape :").append (this.etape);
        return sb.toString ();

    }
}
