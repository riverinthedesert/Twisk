package twisk.outils;

public class FabriqueNumero {
    private int cptEtape ;
    private int cptSemaphore ;
    protected static FabriqueNumero instance = new FabriqueNumero ();

    /**
     * constructeur de fabrique numero
     */
    private FabriqueNumero(){
        this.cptEtape = 0;
        this.cptSemaphore = 1;
    }

    /**
     * fonction get instance
     * @return une instance de l'objet
     */
    public static FabriqueNumero getInstance(){
        return instance ;
    }

    /**
     * Fonction getnumeroEtape
     * @return le numero de l'etape
     */
    public int getNumeroEtape(){
        return cptEtape ;
    }

    /**
     * fonction get numero semaphore
     * @return le numero de la semaphore
     */
    public int getNumeroSemaphore(){
        return cptSemaphore ;
    }

    /**
     * fonction reset qui reinitialise les compteur d'etape et de semaphore
     */
    public void reset(){
        this.cptEtape = 0 ;
        this.cptSemaphore = 1 ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder ();
        sb.append (this.cptEtape);
        return sb.toString ();
    }


}
