package twisk.monde.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Etape;
import twisk.monde.SasSortie;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SasSortieTest {
    SasSortie sortie;

    SasSortieTest(){

    }
    @BeforeEach
    void setUp() {
        Etape e3=new Etape("tonton");
        this.sortie=new SasSortie();
        this.sortie.ajouterEtapes(e3);
    }

    @Test
    void nbEtapes() {
        assertEquals(1, this.sortie.nbEtapes());
    }

    @Test
    void toC() {
        assertEquals("transfert(tonton,sortie);\n", this.sortie.toC());
        System.out.printf(this.sortie.toC());
    }
}