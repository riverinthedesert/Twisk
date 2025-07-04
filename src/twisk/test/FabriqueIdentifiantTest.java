package twisk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.FabriqueIdentifiant;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueIdentifiantTest {
    FabriqueIdentifiant fi ;
    String id ;
    String id2 ;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        FabriqueIdentifiant fi = FabriqueIdentifiant.getInstance ();
        id = fi.getIdentifiantEtapes () ;
        id2 = fi.getIdentifiantEtapes () ;
    }

    @org.junit.jupiter.api.Test
    void getInstance() {
        Assertions.assertNotEquals (null,fi);

    }

    @org.junit.jupiter.api.Test
    void getIdentifiantEtapes() {
        Assertions.assertNotEquals (0,id);
        System.out.println (id);
        System.out.println (id2);

    }
}