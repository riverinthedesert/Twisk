package twisk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.EtapeIG;
import twisk.outils.FabriqueIdentifiant;

import static org.junit.jupiter.api.Assertions.*;

class EtapeIGTest {
    EtapeIG etape;
    FabriqueIdentifiant id ;

    EtapeIGTest(){}

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.id = new FabriqueIdentifiant ();
        this.etape=new ActiviteIG("moi",id);

    }



    @Test
    void iterator() {
        Assertions.assertNotEquals( "2", this.etape.toString());
        System.out.println(" ");
        System.out.println(this.etape.toString());
    }

}