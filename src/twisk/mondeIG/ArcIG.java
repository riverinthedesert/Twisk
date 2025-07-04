package twisk.mondeIG;

import java.util.Objects;

public class ArcIG {

    private PointDeControleIG pts1 ;
    private PointDeControleIG pts2 ;

    /**
     * Constructeur d'un arc
     * @param pts1 le point de depart
     * @param pts2 le point d'arrivee
     */
    public ArcIG(PointDeControleIG pts1,PointDeControleIG pts2){
        this.pts1 = pts1 ;
        this.pts2 = pts2 ;
    }

    /**
     * fonction equals qui permet de comparer un arc
     * @param arc l'arc a comparer
     * @return un booleen
     */
    public boolean equals(ArcIG arc) {
        return ((arc.getPts1 ().equals (this.getPts1 ())) && (arc.getPts2 ().equals (this.getPts2 ()))) || (arc.getPts2 ().equals (this.getPts1 ()) && (arc.getPts1 ().equals (this.getPts2 ())));
    }


    /**
     * Fonction qui permet de recuperer le point 1
     * @return le premier point
     */
    public PointDeControleIG getPts1() {
        return pts1;
    }

    /**
     * Fonction qui permet de recuperer le deuxieme point
     * @return le second point
     */
    public PointDeControleIG getPts2() {
        return pts2;
    }
}
