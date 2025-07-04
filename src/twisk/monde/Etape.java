package twisk.monde;
//erreur * appuie sur alt entrer

import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class Etape implements Iterable<Etape>{
    private String nom ;
    private GestionnaireSuccesseurs gs ;
    private int numEtape;

    /**
     * Constructeur d'une Etape
     * @param nom nom
     */
    public Etape(String nom){
        this.nom = nom ;
        this.gs = new GestionnaireSuccesseurs();
        this.numEtape=0;
    }

    /**
     * Fonction ajouter successeur qui ajoute un successeur a l'etape e
     * @param e une liste d'etape
     */
    public void ajouterSuccesseur(Etape ...e){
       // for (Etape i : e){
            this.gs.ajouter(e); //utilisation de la fonction de la class Etape
        //}

    }

    public int nbSuccesseurs(){
        return gs.nbEtapes () ;
    }

    /**
     * fonction qui regarde si l'objet est une activité ou non
     * @return un boolean
     */
    public boolean estUneActivite(){
        return false;
    }

    /**
     * fonction qui regarde si l'objet est un guichet ou non
     * @return un boolean
     */
    public boolean estUnGuichet(){
        return false;
    }


    /**
     * Fonction return nombre jetons si l'objet est un guichet
     * @return nombre jetons
     */
    public int getNbJetons(){
        return 0 ;
    }

    /**
     * fonction qui return Nom
     * @return Nom
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * fonction qui return gestionnairesuccesseur
     * @return gestionnairesuccesseur
     */
    public GestionnaireSuccesseurs getGestionnaireSuccesseurs(){
        return this.gs;
    }



    /**
     * fonction qui traite tous les etats
     * @return un iterator
     */
    public Iterator<Etape> iterator(){
        return new Iterator<Etape>() {
            public boolean hasNext() {
                return false;
            }

            public Etape next() {
                return Etape.this;
            }
        };
    }

    @Override
    public String toString() {
        return nom + ":" +this.gs.toString();
    }

    /**
     * Fonction transforme a programme C
     * @return une chaîne de caractères contenant le code C
     */
    public String toC(){
        return "";
    }

    /**
     * fonction d'affichage delai du succeseur
     * @return un string builder
     */
    public String delais(){
        return "5";
    }

    /**
     * return Temps
     *@return Temps
     */
    public int getTemps() {
        return 5;
    }

    /**
     *return EcartTemps
     * @return EcartTemps
     */
    public int getEcartTemps() {
        return 5;
    }

    /**
     * procedure transforme nom de class plus lisible
     */
    public void lisibleC(){
        this.nom=this.nom.replace(" ","_").replace("é","e").replace("è","e").replace("à","e").replace("ê","e").replace("ç","c").replace("û","u").replace("ü","u").replace("â","a").replace("î","i").replace("ô","p").replace("ù","u");
    }

    public int getNumEtape() {
        return numEtape;
    }
}
