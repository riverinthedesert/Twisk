package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape>{
    private ArrayList<Etape> et ;

    /**
     * Constructeur d'un gestionnaire de successeur
     */
    public GestionnaireSuccesseurs(){
        this.et = new ArrayList<>();
    }

    /**
     * Fonction qui permet d'ajouter une ou plusieurs etapes a l'arraylist
     * @param etapes la liste d'etapes a ajouter
     */
    public void ajouter(Etape ... etapes){
        this.et.addAll(Arrays.asList(etapes));
    }

    /**
     * Fonction qui calcule le nombre d'etapes dans la liste
     * @return int la taille de la liste
     */
    public int nbEtapes(){
        return this.et.size ();
    }

    /**
     * retourner le etape dans gestionnaire
     * @param index indice
     */
    public Etape getEtapes(int index) {
        return this.et.get(index);
    }

    public Iterator<Etape> iterator(){
       /* return new Iterator<>() {
            int i = 0;

            public boolean hasNext() {
                return this.i < GestionnaireSuccesseurs.this.et.size();
            }

            public Etape next() {
                this.i++;
                return GestionnaireSuccesseurs.this.et.get(this.i-1);
            }
        };*/
       return et.iterator ();
    }

    /**
     * fonction qui return gestionnairesuccesseur
     * @return gestionnairesuccesseur
     */
    ArrayList<Etape> getSuccesseur(){
        return this.et;
    }

    @Override
    public String toString() {
        Iterator<Etape> iterator = GestionnaireSuccesseurs.this.iterator();
        StringBuilder sb = new StringBuilder ();
        while (iterator.hasNext())
        {
            sb.append(iterator.next().getNom()).append(" ");
        }
        return " "+nbEtapes()+" successeurs - "+sb.toString();
    }

    /**
     * Fonction transforme a programme C
     * @return une chaîne de caractères contenant le code C
     */
    public String toC(){
        StringBuilder str = new StringBuilder();
        str.append(this.et.get(0).getNom()+");");
        return str.toString();
    }
}
