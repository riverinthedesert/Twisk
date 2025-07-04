package twisk.outils;

import java.io.Serializable;

public class FabriqueIdentifiant implements Serializable {
    private int noEtape ;
    private static FabriqueIdentifiant ourInstance = new FabriqueIdentifiant();

    /**
     * Fonction fabriqueIdentifiant
     * @return l'instance de fabriqueIdentifiant
     */
    public static FabriqueIdentifiant getInstance() {
        return ourInstance;
    }


    /**
     * Fonction qui retourner le noEtape
     * @return le numero de l'etape
     */
    public String getIdentifiantEtapes(){
        noEtape++ ;
        return ("numero étape : "+noEtape);
    }
}
