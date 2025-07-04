package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Monde;
import twisk.mondeIG.Sujet;
import twisk.outils.KitC;

import java.io.Serializable;
import java.lang.System;

import java.io.IOException;
import java.util.Iterator;

import static java.lang.System.exit;

public class Simulation extends Sujet implements Iterable, Serializable {

    //déclaration des fonctions natives dans la classe java
    public native int[] start_simulation(int nbEtapes,int nbGuichets,int nbClients,int[] tabJetonsGuichet);
    public native int[] ou_sont_les_clients(int nbEtapes,int nbClients);
    public native void nettoyage();
    private  int nbclient;
    private  ClientsManager clientsManagers;
    private KitC kitC;

    /**
     * Constructeur sans argument
            */
    public Simulation(){
        this.nbclient=2;
        this.kitC=new KitC();
        this.clientsManagers=new ClientsManager();
    }

    /**
     *  initialisation nb client
     * @param nb nbclient
     */
    public void setNbClients(int nb) {
        this.nbclient=nb;
    }

    /**
     * fonction d'arret de la simulation
     * @param situation le tableau de données sur les clients
     * @param nbClients le nombre de clients
     * @return un booleen
     */
    public Boolean estFini(int[] situation ,int nbClients){
        boolean estfini = true;

        if (situation[situation.length-nbClients-1] == nbClients){
            estfini = false ;
        }
        return estfini ;
    }
    /**
     *  simuler simplement lemonde
     * @param monde le monde
     */
    public void simuler(Monde monde) {
        System.out.print(monde.toString());
        this.kitC.creerEnvironnement();
        this.kitC.creerFichier(monde.toC());
        this.kitC.compiler();
        this.kitC.construireLaLibrairie();
        System.out.println ("\n\n");

        System.load ("/tmp/twisk/libTwisk.so");

        int nombreEtapes = monde.nbEtapes();
        System.out.println ("nb Etapes : " + nombreEtapes);
        int nbClients = nbclient ;
        System.out.println ("nb client " + nbClients);
        int nombreGuichet = monde.nbGuichets ();
        int[] tabJetonsGuichet = monde.nbJeton() ;

        //permet de definir les clients qui vont parcourir le monde
        int[] simul = start_simulation(nombreEtapes,nombreGuichet,nbClients,tabJetonsGuichet);
        System.out.print  ("les clients:");
        for(int i = 0 ; i < nbClients;i++){
            System.out.print (simul[i]+" ");
        }

        System.out.println ("\n\n");

        // set clients manger


        // clientmanager
        int[] tabs=new int[nbClients];
        for(int i=0;i<nbClients;i++){
            tabs[i]=simul[i];
        }
        this.clientsManagers=new ClientsManager(tabs);





        /*recherche de memoire*/

        int[] situation;
        boolean v = true;

        while (v){

            situation=ou_sont_les_clients(nombreEtapes,nbClients);
            v = estFini (situation,nbClients);


            // represent combien etape on a deja parcours mais non inclus sortie et entree
            int nbEtapeParcouru=0;
            // index de etape dans ce monde
            int n=0;
            // pour les etapes non inclus entree et sorite
            int ctr=nombreEtapes-2;
            if (monde.nbEtapes () == 1){
                System.out.print("Etape  (entree et sortie) " + situation[n * (nbClients + 1)] + " clients :");

                System.out.println ("\n");
            }
            // on commence par etape 0(entree),
            System.out.print("Etape 0 (entree) " + situation[n * (nbClients + 1)] + " clients :");
            for (int i =  n * (nbClients + 1) + 1; i < situation[n * (nbClients + 1)] + 1; i++) {
                System.out.print(situation[i] + " ");
            }
            System.out.println ("\n");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
               this.kitC.killC();
              //  break;
                Runtime.getRuntime().exit(0);
            }
            // ensuite etape dans gestionnaire des etapes sauf les etapes commmeSortie
            for(int m = 0; m < monde.nbEtapesEntree () ; m++) {
                n = monde.getIndex(monde.getEtapeEntree(m)) + 1;
                while (nbEtapeParcouru < ctr - 1) {
                    System.out.print("Etape " + n + " (" + monde.getEtape(n - 1).getNom() + ") " + situation[n * (nbClients + 1)] + " clients :");
                    for (int i = n * (nbClients + 1) + 1; i < situation[n * (nbClients + 1)] + n * (nbClients + 1) + 1; i++) {
                        System.out.print(situation[i] + " ");
                        if(situation[i] != 0){
                            this.clientsManagers.allerA(situation[i],monde.getEtape(n-1),0);
                        }
                    }
                    n = monde.getIndex(monde.getEtape(n - 1).getGestionnaireSuccesseurs().getEtapes(0)) + 1;
                    nbEtapeParcouru += 1;
                    System.out.println("\n");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        this.kitC.killC();
                     //   break;
                        Runtime.getRuntime().exit(0);
                    }
                }
            }
            //seulement les etapes commmeSortie
            for(int m = 0 ; m < monde.nbEtapesSortie() ; m++) {
                n = monde.getIndex(monde.getEtapeSortie(m)) + 1;
                //System.out.println (n);
                System.out.print("Etape " + n + " (" + monde.getEtape(n - 1).getNom() + ") " + situation[n * (nbClients + 1)] + " clients :");
                for (int i = n * (nbClients + 1) + 1; i < situation[n * (nbClients + 1)] + n * (nbClients + 1) + 1; i++) {
                    System.out.print(situation[i] + " ");
                    if(situation[i] != 0){
                        this.clientsManagers.allerA(situation[i],monde.getEtape(n-1),0);
                    }
                }
                System.out.println("\n");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    this.kitC.killC();
                  //  break;
                    Runtime.getRuntime().exit(0);
                }
            }
            //etape sortie
            n=nombreEtapes-1;
            System.out.print("Etape " + n + " (sortie) " + situation[n * (nbClients + 1)] + " clients :");
            for (int i = n * (nbClients + 1) + 1; i < situation[n * (nbClients + 1)] + n * (nbClients + 1) + 1; i++) {
                System.out.print(situation[i] + " ");
                if(situation[i] != 0){
                    this.clientsManagers.getClients().get(situation[i]).allerA(new Etape("sortie"),i- n * (nbClients + 1) - 1);
                }
            }
            System.out.println ("\n");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                this.kitC.killC();
             //   break;
                Runtime.getRuntime().exit(0);
            }
            System.out.println ("---------------------------------------------------------------------------------------------------------");


        }
        nettoyage ();

    }



    public int getNbclient() {
        return nbclient;
    }

    /**
     * obtenir clientManager
     * @return clientManager
     */
    public ClientsManager getClientsManagers() {
        return clientsManagers;
    }

    /**
     * obtenir KITC
     * @return  kitc
     */
    public KitC getKitC() {
        return kitC;
    }

    @Override
    public Iterator<Client> iterator() {
        return clientsManagers.iterator();
    }
}
