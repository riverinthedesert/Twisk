package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;

public class SasSortie extends Activite {
    private ArrayList<Etape> sortie;

    /**
     * Constructeur d'un SasSortie
     */
    public SasSortie(){
        super("Sortie");
        this.sortie= new ArrayList<>();
    }

    /**
     * ajouter etapes dans SasSortie
     * @param etapes etape
     */
    public  void ajouterEtapes(Etape ... etapes)
    {
        for (Etape etape : etapes) {
            this.sortie.add(etape);
        }
    }

    /**
     * retourner etapes dans Sassortie
     * @return int la nombre de la sortie
     */
    public int  nbEtapes()
    {
        return this.sortie.size();
    }


    /**
     * verifier si il est dan Sassortie
     * @param etape etape
     * @return boolean
     */
    public boolean contient(Etape etape){
        for (Etape etape1:this.sortie) {
            if (etape == etape1) {
                return true;
            }
        }
            return false;
    }

    /**
     * getter etape de index i
     * @param index index
     * @return un etape
     */
    public Etape getEtape(int index) {
        return this.sortie.get(index);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Etape etape:sortie) {
            Iterator<Etape> iterator = etape.iterator();
            StringBuilder sb2 = new StringBuilder ();
            while (iterator.hasNext())
            {
                sb2.append(iterator.next().getNom()).append(" ");
            }
            str.append(etape.getNom()+": "+(etape.getGestionnaireSuccesseurs().nbEtapes()+1)+" successeurs - "+sb2.toString()+"sortie \n");
        }
        str.append("sortie : 0 successeur - \n");
        return str.toString();
    }

    /**
     * Fonction transforme a programme C
     * @return une chaîne de caractères contenant le code C
     */
    public String toC(){
        StringBuilder str = new StringBuilder();
        str.append("transfert("+this.sortie.get(0).getNom()+",sortie);\n");
        return str.toString();
    }
}