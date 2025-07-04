package twisk.simulation;

import twisk.monde.Etape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ClientsManager implements Iterable, Serializable {
    private HashMap<Integer,Client> clients;

    /**
     * Constructeur avec une liste de clients
     * @param tabClients les clients de base
     */
    public ClientsManager(int ... tabClients){
        this.clients=new HashMap<Integer,Client>(100);
        this.setClients(tabClients);
    }

    /**
     * Fonction set qui permet d'instancier les clients identifiés par leur numéro de processus
     * @param tabClients la table des clients
     */
    public void setClients(int ... tabClients){
        for(int i : tabClients){
            clients.put(i,new Client(i));
        }
    }

    /**
     * Fonction qui met a jour les attributs etape et rang d'un client
     * @param numeroClient le cnumero du client concerné
     * @param etape l'etape concerné
     * @param rang le rang concerné
     */
    public void allerA(int numeroClient, Etape etape, int rang){
        int Succ=Reperter(rang);
        clients.get(numeroClient).allerA(etape,Succ);
    }

    /**
     * fonction qui eviter interconnextion entre deux rang dqns cients
     * @param rang rang
     */
    public int Reperter(int rang){
        for(Client client:clients.values()){
            if(rang==client.getRang())
            {
                int Succ=rang+1;
                return Reperter(Succ);
            }
        }
        return rang;
    }

    /**
     * Fonction qui permet de faire le ménage dans les clients
     */
    public void reset(){
        clients.clear ();
    }

    @Override
    public Iterator<Client> iterator() {
        return clients.values().iterator();
    }

    public HashMap<Integer,Client> getClients() {
        return clients;
    }
}
