package twisk.monde;

public class ActiviteRestreinte extends Activite {


    /**
     * Constructeur d'une activite restreinte
     * @param nom le nom de l'activite-
     */
    public ActiviteRestreinte(String nom) {
        super (nom);
    }

    /**
     * Constructeur d'une activite restreinte
     * @param nom le nom de l'activite
     * @param t le temps que dure l'activite
     * @param e l'ecart temps
     */
    public ActiviteRestreinte(String nom,int t ,int e){
        super(nom,t,e);
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
