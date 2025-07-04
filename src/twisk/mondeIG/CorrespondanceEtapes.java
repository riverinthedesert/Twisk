package twisk.mondeIG;

import twisk.monde.Etape;

import java.io.Serializable;
import java.util.HashMap;

public class CorrespondanceEtapes implements Serializable {
    private HashMap<EtapeIG,Etape> correspondance;
    private HashMap<Etape,EtapeIG> correspondance2;


    public CorrespondanceEtapes(){
        this.correspondance=new HashMap<>(100);
        this.correspondance2=new HashMap<>(100);
    }

    public void ajouter(EtapeIG etig, Etape et){
        this.correspondance.put(etig,et);
        this.correspondance2.put(et,etig);
    }

    public Etape get(EtapeIG e){
        return this.correspondance.get(e);
    }

    public EtapeIG get(Etape e){
        return this.correspondance2.get(e);
    }
}
