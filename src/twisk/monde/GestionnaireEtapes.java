package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtapes implements Iterable<Etape>{
    private ArrayList<Etape> etapes;
    private ArrayList<Etape> sorite;

    /**
     * Constructeur d'un gestionnaire d'etape
     */
    public GestionnaireEtapes(){
        this.etapes = new ArrayList<> (100);
        this.sorite = new ArrayList<>(100);
    }

    /**
     * Fonction qui ajoute des etapes dans le gestionnaire
     * @param etapes une liste d'etape
     */
    public void ajouter(Etape ... etapes)
    {
        this.etapes.addAll(Arrays.asList(etapes));
    }

    /**
     * Fonction qui compte le nombre d'etapes
     * @return le nombre d'etapes
     */
    public int nbEtapes(){
        return etapes.size();
    }

    /**
     * retourner le etape dans gestionnaire
     * @param index indice
     * @return Etape etape
     */
    public Etape getEtapes(int index) {
        return this.etapes.get(index);
    }

    /**
     * retourner le etape dans gestionnaire
     * @param etape etape
     * @return int indice
     */
    public int getIndexEtapes(Etape etape) {
        int i=0;
        for(Etape etape1:etapes){
            if(etape1==etape){
                return i;
            }
            i++;
        }
        return i;
    }

    /**
     * fontion qui retourne le gestionnaire etapes
     * @return etapes
     */
    public ArrayList<Etape> getEtapes() {
        return etapes;
    }

    /**
     * fonction qui indique si il est sorite
     * @param etape etape
     */
    public void ajoutSortie(Etape etape){
        this.sorite.add(etape);
    }



    /**
     * Fonction iterator dans gestionnaire
     * @return un iterator
     */
    public Iterator<Etape> iterator(){
        /*return new Iterator<>() {
            int i = 0;

            public boolean hasNext() {
                return this.i < GestionnaireEtapes.this.etapes.size();
            }

            public Etape next() {
                this.i++;
                return getEtapes(this.i-1);
            }
        };*/
        return this.etapes.iterator ();
    }

    @Override
    public String toString() {
        Iterator<Etape> iterator = this.iterator();
        StringBuilder sb = new StringBuilder ();
        while (iterator.hasNext())
        {
            Etape etape=iterator.next();
            String tmp=etape.toString();
            // on n'ajoute pas des etapse par sortie
            int interrupteur=0;
            for(Etape etapesorite:this.sorite) {
                if(etape==etapesorite){
                    interrupteur=1;
                }
            }
            if(interrupteur==0) {
                sb.append(tmp).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Fonction transforme a programme C
     * @return une chaîne de caractères contenant le code C
     */
    public String toC(){
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        str1.append("#define entree 0\n");
        int i=1;
        int j=1;
        for (Etape etape : this.etapes) {
            str1.append("#define "+etape.getNom()+" "+i+"\n");
            i+=1;
            if(etape.estUnGuichet()) {
                str2.append("#define num_sem_guichet"+j+" "+j+"\n");
                j+=1;
            }
        }
        str1.append("#define sortie "+i+"\n");
        return str1.toString()+str2.toString();
    }


}
