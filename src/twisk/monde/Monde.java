/*--module-path  /usr/share/openjfx/lib/
--add-modules javafx.controls,javafx.fxml,javafx.graphics*/

package twisk.monde;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

public class Monde implements Iterable<Etape> {
    private SasEntree entree;
    private SasSortie sortie;
    private GestionnaireEtapes lesEtapes;

    /**
     * Constructeur d'un monde
     */
    public Monde(){
        this.sortie = new SasSortie ();
        this.entree = new SasEntree ();
        this.lesEtapes = new GestionnaireEtapes ();
    }

    /**
     * Ajouter des etapes dans le sas d'entree
     * @param etapes un tableau d'etapes
     */
    public void aCommeEntree(Etape ... etapes){
        this.entree.ajouterEtapes(etapes);
    }

    /**
     * getter de sas entree
     * @return un sas entree
     */
    public SasEntree getEntree() {
        return entree;
    }

    /**
     * getter de sas sortie
     * @return le sas de sortie
     */
    public SasSortie getSortie() {
        return sortie;
    }

    /**
     * getter etape de index i
     * @param index index
     * @return un etape
     */
    public Etape getEtape(int index) {
        return lesEtapes.getEtapes(index);
    }

    /**
     * getter index de Etape dans ce monde
     * @param etape etape
     * @return un index
     */
    public int getIndex(Etape etape) {
        return lesEtapes.getIndexEtapes(etape);
    }

    /**
     * getter getEcartTemps d'etape de index i
     * @param index index
     * @return int
     */
    public int getEcartTemps(int index) {
        return lesEtapes.getEtapes(index).getEcartTemps();
    }

    /**
     * getter getTemps d'etape de index i
     * @param index index
     * @return int
     */
    public int getTemps(int index) {
        return lesEtapes.getEtapes(index).getTemps();
    }

    /**
     * ajouter a dans le sas de sortie
     * @param etapes un tableau d'etapes
     */
    public void aCommeSortie(Etape ... etapes) {
        this.sortie.ajouterEtapes (etapes);
    }

    /**
     * Fonction qui ajoute une etape
     * @param etapes les etapes a ajouter
     */
    public void ajouter(Etape ... etapes) {
        this.lesEtapes.ajouter(etapes) ;
    }

    /**
     * Fonction qui compte le nombre d'etapes
     * @return le nombre d'etat
     */
    public int nbEtapes() {
        return this.lesEtapes.nbEtapes()+2;
    }

    /**
     * Fonction qui permet de returner le nombre d'entree
     * @return le nb d'entree
     */
    public int nbEtapesEntree(){
        return this.getEntree ().nbEtapes ();
    }

    /**
     * Fonction qui permet de returner le nombre de sortie
     * @return le nb de sortie
     */
    public int nbEtapesSortie(){
        return this.getSortie().nbEtapes();
    }

    /**
     * Fonction qui permet de recuperer l'etape entree
     * @param m l'emplacement de l'etape
     * @return l'etape recherchée
     */
    public Etape getEtapeEntree(int m){
        return this.getEntree().getEtape(m);
    }

    /**
     * Fonction qui permet de recuperer l'etape sortie
     * @param m l'emplacement de l'etape
     * @return l'etape recherche
     */
    public Etape getEtapeSortie(int m){
        return this.getSortie().getEtape(m);
    }

    /**
     * Fonction qui retoune nbjeton de guicher
     * @return tableau de jeton
     */
    public int[] nbJeton() {
        int[]  nt=new int[this.nbGuichets()];
        int i=0;
        for(Etape etape: this.lesEtapes){
            if(etape.estUnGuichet()){
                nt[i]=etape.getNbJetons();
            }
        }
        return nt;
    }

    /**
     * fonction nb guichet
     * @return le nombre de guichet dans le monde
     */
    public int nbGuichets(){
        int j=0;
        for(int i=0;i<this.lesEtapes.nbEtapes();i++)
        {
            if(this.lesEtapes.getEtapes(i).estUnGuichet())
            {
                j=j+1;
            }
        }
        return j;
    }



    /**
     * Fonction iterateur
     * @return un iterateur
     */
    public Iterator<Etape> iterator(){
        return  this.lesEtapes.iterator();
    }

    @Override
    public String toString() {
        // on indique que des etapes dans sassotie
        for(Etape etape:lesEtapes.getEtapes()){
            if(this.sortie.contient(etape))
            {
               lesEtapes.ajoutSortie(etape);
            }
        }
        return this.entree.toString()+lesEtapes.toString()+this.sortie.toString();
    }

    /**
     * Fonction transforme a programme C
     * @return une chaîne de caractères contenant le code C
     */
    public String toC(){
        for(Etape etape:lesEtapes){
            etape.lisibleC();
        }
        StringBuilder str = new StringBuilder();
        str.append("#include \"def.h\" \n\n");
        str.append(this.lesEtapes.toC()+"\n");
        str.append("void simulation(int ids)\n");
        str.append("{\n");
        str.append(entree.toC());
        str.append(sortie.toC());
        str.append("}");
        return str.toString();
    }
}

