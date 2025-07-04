package twisk.outils;

import javafx.concurrent.Task;
import twisk.monde.Monde;
import twisk.mondeIG.MondeIG;
import twisk.simulation.Simulation;

import java.util.ArrayList;

public class ThreadsManager {
    private ArrayList<Thread> lesThreads;
    protected static ThreadsManager instance = new ThreadsManager ();
 //   private MondeIG monde;

    /**
     * constructeur de ThreadsManager
     */
    public ThreadsManager(){
        lesThreads=new ArrayList<>(100);
    }

    /**
     * fonction get instance
     * @return une instance de l'objet
     */
    public static ThreadsManager getInstance(){return instance;}

    /**
     * fonction qui lance le Task
     * @param task task
     */
    public void lancer(Task task){
        Thread thread = new Thread(task);
     //   this.monde=monde;
        this.lesThreads.add(thread);
     //   simulation.simuler(monde);
        thread.start();
     //   monde.simuler();
    }

    /**
     * arreter le prorcessus
     */
    public void arret(){
        for(Thread thread:lesThreads){
            thread.interrupt();
        }
    }

    /**
     * fonction qui detruire la threads
     */
    public void detruireTout(){
        for (Thread thread:lesThreads){
            thread.interrupt();
        }
        lesThreads.clear();
    }
}
