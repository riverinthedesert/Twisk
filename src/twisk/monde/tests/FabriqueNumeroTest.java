package twisk.monde.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.FabriqueNumero;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueNumeroTest {
    FabriqueNumero fn ;
    int m;
    int n;

    FabriqueNumeroTest(){

    }

    @BeforeEach
    void setUp() {
        FabriqueNumero fn = FabriqueNumero.getInstance ();
        m = fn.getNumeroEtape ();
        n = fn.getNumeroSemaphore ();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
        Assertions.assertNotEquals (null , fn.getInstance ());
    }

    @Test
    void getNumeroSemaphore(){
        Assertions.assertEquals (1,n);
    }

    @Test
    void getNumeroEtape() {
        Assertions.assertEquals (0,m);
    }

    @Test
    void reset() {
        FabriqueNumero.getInstance ().reset ();
        m = FabriqueNumero.getInstance ().getNumeroEtape ();
        n = FabriqueNumero.getInstance ().getNumeroSemaphore ();
        Assertions.assertEquals (0,m);
        Assertions.assertEquals (1,n);
    }
}