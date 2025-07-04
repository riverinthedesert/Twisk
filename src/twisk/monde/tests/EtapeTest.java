package twisk.monde.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {
    Etape etape;

    EtapeTest(){

    }

    @BeforeEach
    void setUp() {
        Etape actalter = new Activite ("moyenne roue");
        Etape act = new Activite ("sortie");
        this.etape=new Activite(" petite roue");
        this.etape.ajouterSuccesseur(act,actalter);

    }

    @Test
    void nbSuccesseurs() {
        Assertions.assertEquals( 2, this.etape.nbSuccesseurs());
    }

    @Test
    void iterator() {
        assertFalse (this.etape.iterator().hasNext());
    }

    @Test
    void toC() {
       // assertFalse (this.etape.toC().);
        assertEquals(
                "nb petite roue=(int)(rand()/(float)RAND_MAX)*2;\n" +
                        "switch(nb petite roue){\n" +
                        "case 0 :{\n" +
                        "transfert( petite roue,sortie);\n" +
                        "delai(5,5);\n" +
                        "break;\n" +
                        "}\n" +
                        "case 1 :{\n" +
                        "transfert( petite roue,sortie);\n" +
                        "delai(5,5);\n" +
                        "break;\n" +
                        "}\n" +
                        "}\n", this.etape.toC());
        System.out.printf(this.etape.toC());
    }
}