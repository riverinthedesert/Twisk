package twisk.monde.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.SasEntree;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SasEntreeTest {
    SasEntree entree;

    SasEntreeTest(){

    }
    @BeforeEach
    void setUp() {
        Etape e2=new Activite("bopp");
        Etape e3=new Activite("tonton");
        this.entree=new SasEntree();
        this.entree.ajouterEtapes(e2);
        e2.ajouterSuccesseur(e3);
    }

    @Test
    void nbEtapes() {
        Assertions.assertEquals(1, this.entree.nbEtapes());
    }

    @Test
    void toC() {
        assertEquals("entrer(entree);\n" +
                "delai(5,5);\n" +
                "transfert(entree,bopp);\n" +
                "delai(5,5);\n" +
                "transfert(bopp,tonton);\n" +
                "delai(5,5);\n", this.entree.toC());
        System.out.printf(this.entree.toC());
    }
}