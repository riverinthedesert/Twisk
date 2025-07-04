package twisk.mondeIG;

import javafx.application.Platform;
import javafx.concurrent.Task;
import twisk.ecouteur.EcouteurSimuler;
import twisk.exceptions.ArcException;
import twisk.exceptions.MondeException;
import twisk.exceptions.TwiskException;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.ThreadsManager;
import twisk.simulation.Simulation;
import twisk.vues.Observateur;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG extends Sujet implements Iterable<EtapeIG>,Observateur, Serializable {
    private HashMap<FabriqueIdentifiant,EtapeIG> et ;
    private ArrayList<Observateur> listeVue ;
    private ArrayList<ArcIG> listeArc ;
    private ArrayList<PointDeControleIG> pointArc;
    private Sujet sujet ;
    private ArrayList<EtapeIG> selectionAct ;
    private ArrayList<ArcIG> selectionArc ;
    private ArrayList<GuichetIG> selectionGuichet ;

    public ArrayList<GuichetIG> getSelectionGuichet() {
        return selectionGuichet;
    }

    public void setSelectionGuichet(ArrayList<GuichetIG> selectionGuichet) {
        this.selectionGuichet = selectionGuichet;
    }

    private ArrayList<EtapeIG> listeEntree ;
    private ArrayList<EtapeIG> listeSortie ;
    private HashMap<EtapeIG,ArrayList<EtapeIG>> successeur;
    private CorrespondanceEtapes correspondanceEtapes;
    private Simulation simulation;

    public void setEtatSimulation(boolean etatSimulation) {
        this.etatSimulation = etatSimulation;
    }

    private boolean etatSimulation;
    private int nbclient;


    /**
     * Constructeur par default
     */
    public MondeIG(){
        super();
        this.et = new HashMap<> ();
        FabriqueIdentifiant fid = new FabriqueIdentifiant ();
        //et.put (fid,new ActiviteIG ("Activité",fid));
        this.listeVue = new ArrayList<> ();
        this.listeArc = new ArrayList<> ();
        this.pointArc = new ArrayList<> (2);
        this.selectionAct = new ArrayList<> ();
        this.selectionArc = new ArrayList<> ();
        this.selectionGuichet = new ArrayList<> ();
        this.listeEntree = new ArrayList<> ();
        this.listeSortie = new ArrayList<> ();
        this.successeur=new HashMap<>(100);
        this.correspondanceEtapes=new CorrespondanceEtapes();
        this.simulation=new Simulation();
        this.etatSimulation=false;
        this.nbclient=0;
    }

    public HashMap<FabriqueIdentifiant, EtapeIG> getEt() {
        return et;
    }

    /**
     * Fonction ajouter qui permet de
     * @param type le type d'etape a ajouter
     */
    public void ajouter(String type){
        if (type=="Activité"){
            FabriqueIdentifiant id = new FabriqueIdentifiant ();
            ActiviteIG act = new ActiviteIG (type,id);
            this.et.put (id, act);
        } else if (type=="Guichet"){
            FabriqueIdentifiant id = new FabriqueIdentifiant ();
            GuichetIG guichet = new GuichetIG ("guichet",id);
            this.et.put (id, guichet);
        }


    }




    /**
     * fonction qui permet d'ajouter un arc dans une liste
     * @param pts1 le 1er point de l'arc
     * @param pts2 le second point de l'arc
     * @throws ArcException une exception
     */
    private void addArc(PointDeControleIG pts1, PointDeControleIG pts2) throws ArcException{

        if (!(pts1.getEtape ().equals (pts2.getEtape ()))) {
            //verifier qu'il n'y a pas deux arc qui relient les deux memes etapes
            if (this.estVrai (pts1, pts2)) {
                if(!pts1.getEtape().estAccessibleDepuis(pts2.getEtape())) {
                    this.listeArc.add(new ArcIG(pts1, pts2));
                    if(pts1.getEtape().estUnGuichet()&&!pts1.getEtape().isPassens()){
                        if(pts1.getIdentifiant().equals("C")) {
                            pts1.getEtape().setPassens(true,false);
                        }
                        else{
                            pts1.getEtape().setPassens(true,true);
                        }
                    }
                    if(pts2.getEtape().estUnGuichet()&&!pts2.getEtape().isPassens()){
                        if(pts2.getIdentifiant().equals("C")) {
                            pts2.getEtape().setPassens(true,true);
                        }
                        else{
                            pts2.getEtape().setPassens(true,false);
                        }
                    }

                    // pour ajouter succeseur
                    ArrayList<EtapeIG> etapeIGSucceseur = new ArrayList<>(100);
                    if (this.successeur.containsKey(this.pointArc.get(0).getEtape())) {
                        etapeIGSucceseur = this.successeur.get(this.pointArc.get(0).getEtape());
                    }
                    etapeIGSucceseur.add(this.pointArc.get(1).getEtape());
                //    this.pointArc.get(0).getEtape().ajouterSucceseur(this.pointArc.get(1).getEtape());
                    this.successeur.put(this.pointArc.get(0).getEtape(), etapeIGSucceseur);
                    pts1.getEtape().ajouterSucceseur(pts2.getEtape());
                }
                else {
                    this.pointArc.clear ();
                    throw new ArcException ("Un arc peut être ajouter parce que c'est un circulation");
                }
            }
            else {
                //this.pointArc.removeAll (pointArc);
                this.pointArc.clear ();
                throw new ArcException ("Un arc peut être ajouter seulement s'il n'a a pas déja un arc entre les deux activités");
            }
        }else {
            this.pointArc.clear ();
            throw new ArcException ("Un arc peut être ajouter seulement s'il les deux points ne sont pas dans la même étape");
        }

    }


    /**
     * Fonction qui permet d'ajouter un arc si la liste contient deux points
     * @param pt un ppoint de controle
     */
    public void addPointArc(PointDeControleIG pt) throws ArcException {

        if(pointArc.size ()<2) {
            this.pointArc.add (pt);
            if(pointArc.size () == 2){
                this.addArc (this.pointArc.get(0),this.pointArc.get (1));

            }

        }else{
            //this.pointArc.removeAll (pointArc);
            this.pointArc.clear ();
            addPointArc (pt);
        }

    }



    /**
     * fonction booleenne  qui s'occupe des conditions sur les arc
     * @param pts1 le premier pts de controle
     * @param pts2 le second point de controle
     * @return un booleen
     */
    private boolean estVrai(PointDeControleIG pts1, PointDeControleIG pts2){
        boolean contrainte = true ;
        boolean condition1 ;
        boolean condition2 ;
        boolean condition3 ;
        boolean condition4 ;
        for(ArcIG arc : this.listeArc){
            condition1 = (arc.getPts1 ().getEtape ().equals (pts1.getEtape ()));
            condition2 = (arc.getPts1 ().getEtape ().equals (pts2.getEtape ())) ;
            condition3 = ((arc.getPts2 ().getEtape ().equals (pts1.getEtape ())));
            condition4 = ((arc.getPts2 ().getEtape ().equals (pts2.getEtape ())));
            if(condition1){
                if(condition4){

                    contrainte = false ;
                }

            }else if (condition2){
                if (condition3){
                    contrainte = false ;
                }
            }
        }
        return contrainte;
    }


    @Override
    //permet de consulter toutes les étapes mémorisées
    public Iterator<EtapeIG> iterator() {
        return et.values().iterator () ;
    }

    /**
     * Second iterateur pour parcourir la liste des arcs
     * @return un iterator
     */
    public Iterator<ArcIG> iterateurArc(){
        return listeArc.iterator ();
    }




    /**
     * Fonction get qui permet de recupere les activites
     * @return liste
     */
    public ArrayList<EtapeIG> getSelectionAct() {
        return selectionAct;
    }
    /**
     * fonction get qui permet de recupere les arc selectionnes
     * @return une liste
     */
    public ArrayList<ArcIG> getSelectionArc() {
        return selectionArc;
    }

    /**
     * Fonction qui permet de recuperer les vue
     * @return une liste de toutes les vues
     */
    public ArrayList<Observateur> getListeVue() {
        return listeVue;
    }

    /**
     * fonction get qui permet de recuperer les pts d'un arc
     * @return une liste
     */
    public ArrayList<PointDeControleIG> getPointArc() {
        return pointArc;
    }

    /**
     * Fonction qui permet de recuperer la liste de tous les arcs
     * @return une liste d'arcs
     */
    public ArrayList<ArcIG> getListeArc() {
        return listeArc;
    }

    /**
     * fonction get qui permet de recuperer les entree du monde
     * @return une liste
     */
    public ArrayList<EtapeIG> getListeEntree() {
        return listeEntree;
    }

    /**
     * fonction get qui permet de recuperer les sorties du monde
     * @return une liste
     */
    public ArrayList<EtapeIG> getListeSortie() {
        return listeSortie;
    }



    /**
     * Fonction qui va permet de regarder si l'activite est presente dans la liste des selectionner pour savoir si on la met comme selectionner ou non
     * @param etape l'activite clickée
     */
    public void gestionActivite(EtapeIG etape){
        boolean appartient = false ;
        for (EtapeIG act : selectionAct){
            if (act.equals (etape)){
                appartient = true ;
            }
        }
        if (appartient){
            selectionAct.remove (etape);
        }else{
            selectionAct.add (etape);
        }
    }

    /**
     * fonction qui permet de gerer les arc
     * @param arc l'arc selectionne
     */
    public void gestionArc(ArcIG arc){
        boolean appartient = false ;
        for(ArcIG a : selectionArc){
            if(a.equals (arc)){
                appartient = true ;
            }
        }
        if (appartient){
            selectionArc.remove (arc);
        }else{
            selectionArc.add (arc);
        }
    }

    public void gestionGuichet(GuichetIG g){
        boolean appartient = false ;
        for (GuichetIG gui : selectionGuichet){
            if(gui.equals (g)){
                appartient = true ;
            }
        }
        if(appartient){
            selectionGuichet.remove (g);
        }else{
            selectionGuichet.add (g);
        }
    }

    /**
     * fonction qui, permet d'ajouter les entrees du monde
     */
    public void gestionEntree(){

        for (EtapeIG act : selectionAct){
            if(listeEntree.contains(act)){
                this.listeEntree.remove (act);
            }else{
                this.listeEntree.add (act);
            }

        }
        for (EtapeIG gui : selectionGuichet ){
            if(listeEntree.contains(gui)){
                this.listeEntree.remove (gui);
            }else{
                this.listeEntree.add (gui);
            }
        }
    }

    /**
     * fonction qui permet d'ajouter les sorties du monde
     */
    public void gestionSortie(){
        for (EtapeIG act : selectionAct){
            if(listeSortie.contains (act)){
                this.listeSortie.remove (act);
            }else {
                this.listeSortie.add (act);
            }
        }
        for (EtapeIG gui : selectionGuichet){
            if(listeSortie.contains (gui)){
                this.listeSortie.remove (gui);
            }else {
                this.listeSortie.add (gui);
            }
        }
    }

    public void resetMonde(){
        this.listeSortie.clear ();
        this.listeEntree.clear ();
        this.listeArc.clear ();
        this.pointArc.clear ();
        this.successeur.clear ();
        this.et.clear ();
        this.etatSimulation = false;
    }

    /**
     * fonction qui permet de supprimer les objets selectionnes
     */
    public void supprimerSelection() {

        {

            ArrayList<ArcIG> temp = new ArrayList<ArcIG> ();
            for (EtapeIG tour : this.getSelectionAct ()) {
                if (et.containsKey (tour.getIdentifiant ())) {
                    for (ArcIG arc : this.getListeArc ()) {
                        if ((tour.equals (arc.getPts1 ().getEtape ()) || tour.equals (arc.getPts2 ().getEtape ()))) {
                            temp.add (arc);
                        }
                    }
                    this.getListeArc ().removeAll (temp);
                    temp.clear ();

                    this.et.remove (tour.getIdentifiant ());
                }
            }
            listeArc.removeAll (selectionArc);
            this.selectionArc.clear ();

            this.selectionAct.clear ();
            temp = new ArrayList<> ();
            for (EtapeIG tour : this.getSelectionGuichet ()) {
                if (et.containsKey (tour.getIdentifiant ())) {
                    for (ArcIG arc : this.getListeArc ()) {
                        if ((tour.equals (arc.getPts1 ().getEtape ()) || tour.equals (arc.getPts2 ().getEtape ()))) {
                            temp.add (arc);
                        }
                    }
                    this.getListeArc ().removeAll (temp);
                    temp.clear ();

                    this.et.remove (tour.getIdentifiant ());
                }
            }
            for(EtapeIG etapeIG:this.et.values()){
                if(etapeIG.estUnGuichet()){
                    for(ArcIG arcIG:selectionArc){
                        if(arcIG.getPts1()==etapeIG.getPointsDeControle().get(0)||arcIG.getPts1()==etapeIG.getPointsDeControle().get(1)||arcIG.getPts2()==etapeIG.getPointsDeControle().get(0)||arcIG.getPts2()==etapeIG.getPointsDeControle().get(1)){
                            etapeIG.setPassens(false,false);
                        }
                    }
                }
            }
            listeArc.removeAll (selectionArc);
            this.selectionArc.clear ();
            this.selectionGuichet.clear ();
        }
    }

    /**
     * fonction qui permet d'annuler toutes les selections
     */
    public void oublierSelection(){
        if (this.selectionAct!=null){
            this.selectionArc.clear ();
        }
        if (this.selectionArc!=null){
            this.selectionAct.clear ();
        }
        if(this.selectionGuichet!=null){
            this.selectionGuichet.clear ();
        }


    }


    /**
     * demander la simulation sur ce monde
     * @throws MondeException
     */
    public void simuler() throws MondeException{
        this.etatSimulation=true;
        this.notifierObservateur();

        verifierMondeIG();
        this.simulation=new Simulation();
        this.simulation.setNbClients(5);
        this.nbclient=simulation.getNbclient();


        Monde monde=creerMonde();


        Sujet sujetPerso=this;
        Task<Void> task1=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                    simulation.simuler(monde);
                    etatSimulation=false;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            notifierObservateur();
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ie) {
                    }
                return null;
            }
        };
        Task<Void> task2=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(etatSimulation){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            notifierObservateur();
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ie) {
                    }
                }
                return null;
            }
        };
        ThreadsManager.getInstance().lancer(task1);
        ThreadsManager.getInstance().lancer(task2);
    }


    /**
     * tester si le ​MondeIG est totalement correct
     * @throws MondeException
     */
    private void verifierMondeIG() throws MondeException{
        /*for (EtapeIG etape : et){
            if
        }


        }
        if(i>5){
            throw new MondeException ("erreur");
        }*/
    }

    /**
     * créer le monde par l’appel de la fonction
     * @return
     */
    private Monde creerMonde(){
       Monde m=new Monde();


        // pour le premier parcours,on seulement creer etaps, il va fabriquer trop nullPoint si on directement mettre succeseur
        int i=0;
        for(EtapeIG etapeIG:this.et.values()){
            Etape etape=new Etape("null");
            if(etapeIG.estUneActivite()){
                etape=new Activite(etapeIG.getNom(),5,5);
            }
            if (etapeIG.estUneActiviteRestreinte()){
                etape=new Activite(etapeIG.getNom(),5,5);
            }
            if(etapeIG.estUnGuichet()){
                etape=new Guichet(etapeIG.getNom(),2);
            }

            this.correspondanceEtapes.ajouter(etapeIG,etape);
            m.ajouter(etape);
            if(listeEntree.contains(etapeIG)){
                m.getEntree().ajouterEtapes(etape);
            }
            if(listeSortie.contains(etapeIG)){
                m.getSortie().ajouterEtapes(etape);
            }
        }

        // après premier parcours, maintenant on peut mettre successeur
        for(EtapeIG etapeIG:this.et.values()){
            if(this.successeur.containsKey(etapeIG)) {
                Etape[] etapes = new Etape[this.successeur.get(etapeIG).size()];
                for (int j = 0; j < this.successeur.get(etapeIG).size(); j++) {
                    etapes[j] = this.correspondanceEtapes.get(this.successeur.get(etapeIG).get(j));
                }
                this.correspondanceEtapes.get(etapeIG).ajouterSuccesseur(etapes);
            }
        }
        return m;
    }

    /**
     * reagir pour l'observateur
     */
    @Override
    public void reagir() {

    }

    /**
     * obtenir l'etat pour simulation
     * @return Simulation
     */
    public Simulation getSimulation() {
        return simulation;
    }


    /**
     * verifier si il est dans l'etat simulation
     * @return etatsimulation
     */
    public boolean getetatSimulation(){
        return this.etatSimulation;
    }


    /**
     * retounrer le nombre des clients
     * @return nombre client
     */
    public int getNbclient() {
        return nbclient;
    }


    /**
     * retounrer corespondance
     * @return correespondance
     */
    public CorrespondanceEtapes getCorrespondanceEtapes() {
        return correspondanceEtapes;
    }




}
