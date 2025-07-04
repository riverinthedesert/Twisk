package twisk;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;

public class ClientTwisk {

        //dans ce monde le probleme du rendu intermediaire est mis en avant pour le corriger
    private static void Monde1(){
        System.out.println("monde1:");
        Monde m=new Monde();
        Guichet aat=new Guichet("acces_au_magasin",2);
        Activite t=new Activite("magasin",5,5);
        m.ajouter(aat,t);
        m.aCommeEntree(aat);
        m.aCommeSortie(t);
        aat.ajouterSuccesseur(t);
        Simulation sim=new Simulation();
        sim.setNbClients(3);
        sim.simuler(m);
        System.out.println();
    }

    private static void Monde2(){
        System.out.println("monde2:");
        Monde m2=new Monde();
        Activite path=new Activite("fireBlue",5,5);
        Activite zoo2 = new Activite("silverStar", 3, 1);
        Guichet aat2=new Guichet("acces_a rulantica",2);
        Activite t2=new Activite("rulantica",5,5);
        m2.ajouter(zoo2,aat2,t2,path);
        m2.aCommeEntree(zoo2);
        m2.aCommeSortie(path);
        zoo2.ajouterSuccesseur(aat2);
        aat2.ajouterSuccesseur(t2);
        t2.ajouterSuccesseur(path);

        ClassLoaderPerso classLoader2=new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        try {
            classLoader2.loadClass("twisk.simulation.Simulation");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Simulation simulation=new Simulation();
        simulation.setNbClients(3);
        simulation.simuler(m2);
    }

    private static void Monde3(){
        System.out.println("monde3:");
        Monde m=new Monde();
        Activite zoo = new Activite("balade au zoo", 3, 1);
        Guichet aat=new Guichet("acces_au_toboggan",2);
        Activite t=new Activite("toboggan",5,5);
        m.ajouter(zoo,aat,t);
        m.aCommeEntree(zoo);
        m.aCommeSortie(t);
        zoo.ajouterSuccesseur(aat);
        aat.ajouterSuccesseur(t);
        Simulation sim=new Simulation();
        sim.setNbClients(3);
        sim.simuler(m);
        System.out.println();
    }

    private static void Monde4(){
        Monde2 ();
        Monde3 ();


    }

    private static void Monde5(){
        System.out.println("monde1:");
        Monde m=new Monde();
        Activite t=new Activite("magasin",5,5);
        m.ajouter(t);
        m.aCommeEntree(t);
        m.aCommeSortie(t);
        Simulation sim=new Simulation();
        sim.setNbClients(3);
        sim.simuler(m);
        System.out.println();
    }


    public static void main(String[] args){
        Monde2 ();

    }



}
