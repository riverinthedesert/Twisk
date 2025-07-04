package twisk.simulation;

import twisk.monde.Etape;
import twisk.mondeIG.EtapeIG;

public class Client {

    private Etape etape ;
    private int numeroClient ;
    private int rang ;

    /**
     * Constructeur d'un client
     * @param numero un numero d'etape
     */
    public Client(int numero){
        this.etape = new Etape ("entree");
        this.numeroClient = numero ;
        this.rang = 0 ;
    }

    /**
     * Fonction qui permet d'acceder a une etape et a un rang particulier
     * @param etape l'etape souhaite
     * @param rang le rang souhaite
     */
    public void allerA(Etape etape, int rang){
        this.etape = etape ;
        this.rang = rang ;
    }

    public Etape getEtape() {
        return etape;
    }

    public int getRang() {
        return rang;
    }

    public int getNumeroClient() {
        return numeroClient;
    }
}
