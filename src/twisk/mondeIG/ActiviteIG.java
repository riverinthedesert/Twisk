package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.io.Serializable;
import java.util.Iterator;

public class ActiviteIG extends EtapeIG implements Serializable {




    /**
     * Contructeur d'activiteIG
     * @param nom le nom de l'activite
     * @param idf l'identifiant de l'activite

     */
    public ActiviteIG(String nom, FabriqueIdentifiant idf) {
        super(nom, idf, TailleComposants.getInstance ().getLargeurActivite (), TailleComposants.getInstance ().getHauteurActivite ());

    }

  /*  public ActiviteIG(){
        super();
    }*/


    /**
     * si il est une activite
     * @return true
     */
    public boolean estUneActivite(){
        return true;
    }

    /**
     * si il est une activiteEestreinte
     * @return true
     */
    public boolean estUneActiviteRestreinte(){
        return true;
    }

}
