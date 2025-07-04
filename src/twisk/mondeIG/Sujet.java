package twisk.mondeIG;

import twisk.simulation.Simulation;
import twisk.vues.Observateur;

import java.util.ArrayList;

public class Sujet {
    private ArrayList<Observateur> listeVue ;

    public Sujet(){
        this.listeVue = new ArrayList<> () ;
    }


    /**
     * Fonction qui permet de mettre a jour les vues
     */
    public void notifierObservateur(){

        for(Observateur v : listeVue){
            v.reagir ();
        }

    }

    /**
     * Fonction qui permet d'ajouter une vue
     * @param v la vue à ajouter
     */
    public void ajouterObservateur(Observateur v){
        this.listeVue.add(v) ;
    }


    /**
     * obtenir l'etat pour simulation
     * @return Simulation
     */
    public Simulation getSimulation() {
        return null;
    }

}
