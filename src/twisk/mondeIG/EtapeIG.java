package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class EtapeIG implements Iterable<PointDeControleIG>, Serializable {
    private String nom ;
    private FabriqueIdentifiant identifiant ;
    protected int posX ;
    protected int posY ;
    private int largeur ;
    private int hauteur ;
    protected ArrayList<PointDeControleIG> pointsDeControle ;
    private ArrayList<EtapeIG> successeur;

    private int delai ;
    private  int ecart ;




    /**
     * Constructeur d'une etapeIG
     * @param nom le nom de l'etape
     * @param idf l'identifiant de l'etape
     * @param larg la largeur de l'etape
     * @param haut la hauteur de l'etape
     *
     */
    EtapeIG(String nom, FabriqueIdentifiant idf, int larg, int haut){
        this.nom = nom;
        this.identifiant = idf;
        this.largeur = larg ;
        this.hauteur = haut ;
        this.successeur=new ArrayList<>();
        construirePtsControle();

        this.delai = 5 ;
        this.ecart = 2 ;

    }

   /* EtapeIG(){

    }*/

    /**
     * Fonction qui permet de construire les points de controles d'une etape
     */
    public void construirePtsControle(){
        Random x = new Random ();
        Random y = new Random () ;
        this.posX =x.nextInt (900);
        this.posY =y.nextInt(900);

        int ptsControl = this.posX + (TailleComposants.getInstance ().getLargeurEtape ()/2);
        this.pointsDeControle = new ArrayList<> ();
        this.pointsDeControle.add (new PointDeControleIG (ptsControl,posY,"A",this));

        int ptsControl2 = this.posY + TailleComposants.getInstance ().getHauteurEtape ();//this.getPosY () + hauteur ;
        this.pointsDeControle.add (new PointDeControleIG (ptsControl, ptsControl2,"B",this));

        ptsControl = this.posX + TailleComposants.getInstance ().getLargeurEtape () ;
        ptsControl2 = this.posY + TailleComposants.getInstance ().getHauteurEtape ()/2;
        this.pointsDeControle.add (new PointDeControleIG (ptsControl,ptsControl2,"C",this));
        ptsControl = this.posX  ;
        this.pointsDeControle.add (new PointDeControleIG (ptsControl,ptsControl2,"D",this));

        this.successeur=new ArrayList<>(100);
    }

    /**
     * ajouter sussceurs dans ce etape
     * @param etapeIG etape succeseur
     */
    public void ajouterSucceseur(EtapeIG etapeIG){
        this.successeur.add(etapeIG);
    }

    /**
     * fonction qui retourner le succeseur
     * @return succeseur
     */
    public ArrayList<EtapeIG> getSuccesseur() {
        return successeur;
    }

    /**
     * Fonction qui recupere la position de l'etape
     * @return la position x de l'etape
     */
    public int getPosX() {
        return posX;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * prends sens de guichet
     * @param passens boolean
     * @param Vergauche boolean
     */
    public void setPassens(boolean passens,boolean Vergauche) {
    }

    /**
     * Fonction qui permet de recuperer le position de l'etape
     * @return la position en y de l'etape
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Fonction qui retourne la liste des points de controle de l'etape
     * @return la liste
     */
    public ArrayList<PointDeControleIG> getPointsDeControle() {
        return pointsDeControle;
    }

    /**
     * Fonction qui permet de connaitre le nom de l'activite
     * @return le nom de l'etape
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Fonction qui permet de connaitre l'identifiant de l'etape
     * @return l'identifiant de l'etape
     */
    public FabriqueIdentifiant getIdentifiant() {
        return this.identifiant;
    }

    /**
     * Fonction qui permet de connaitre la valeur de la hauteur de l'activite
     * @return la heuteur de l'etape
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Fonction get qui permet de connaitre la valeur de la largeur de l'activite
     * @return la largeur de l'etape
     */
    public int getLargeur(){return this.largeur;}

    /**
     * Fonction qui verifie si deux etapes sont identique
     * @param etape1 l'etape a evaluer
     * @return vrai si les deux etapes sont identique
     */
    public boolean equals(EtapeIG etape1){
        return this.getIdentifiant () == etape1.getIdentifiant ();
    }

    /**
     * fonction get qui permet de recuperer le delai de l'act
     * @return le delai
     */
    public int getDelai() {
        return delai;
    }

    /**
     * fonction set qui permet de modifier le delai
     * @param delai le delai a modifier
     */
    public void setDelai(int delai) {
        this.delai = delai;
    }

    /**
     * fonction get qui permet de recuperer l'ecart de l'activite
     * @return l'ecart
     */
    public int getEcart() {
        return ecart;
    }

    /**
     * fonction set qui permet de modifier l'ecart de l'act
     * @param ecart de l'activite
     */
    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return pointsDeControle.iterator ();
    }

    /**
     * si il est une activite
     * @return false
     */
    public boolean estUneActivite(){
        return false;
    }

    /**
     * si il est une activiteEestreinte
     * @return false
     */
    public boolean estUneActiviteRestreinte(){
        return false;
    }

    /**
     * si il est un guichet
     * @return false
     */
    public boolean estUnGuichet(){
        return false;
    }

    /**
     *foncton booleenne qui permet de controler que l'ajout d'un arc ne provoque pas un circuit
     * @param etape l'etape concerné
     * @return un booleen
     */
    public boolean estAccessibleDepuis(EtapeIG etape){
        boolean accessible = false ;
        if(etape.equals (this)){
            return true;
        }
        for(EtapeIG etapeIG1:etape.getSuccesseur()){
            if(etapeIG1==this){
                return true;
            }
            accessible=etapeIG1.estAccessibleDepuis(etape);
            if(accessible)
            {
                return true;
            }
        }
        return accessible;
    }

    /**
     * fonction qui regarde s'il y sens ou pas
     * @return boolean
     */
    public boolean isPassens() {
        return false;
    }

    /**
     * fonction qui regarde s'il vers gauche
     * @return boolean
     */
    public boolean isVergauche() {
        return false;
    }
}
