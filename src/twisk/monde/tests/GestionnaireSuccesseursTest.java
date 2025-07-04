package twisk.monde.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireSuccesseurs;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireSuccesseursTest {
    private GestionnaireSuccesseurs gs;

    GestionnaireSuccesseursTest(){

    }
    @BeforeEach
    void setUp() {
        Activite g = new Activite (" 1");
        Activite act = new Activite ("grande roue");
        this.gs=new GestionnaireSuccesseurs();
        this.gs.ajouter(act,g);
    }

    @Test
    void nbEtapes() {
        Assertions.assertEquals( 2, this.gs.nbEtapes());
    }

    @Test
    void iterator() {
        Iterator<Etape> iterator = gs.iterator();
        assertTrue (iterator.hasNext()); //vrai maintenant
        iterator.next();
        iterator.next(); // ca passe tout
        assertFalse (iterator.hasNext());    // vide maintenant
    }

    @Test
    void toC() {
        // assertFalse (this.etape.toC().);
        assertEquals("grande roue);", this.gs.toC());
        System.out.printf(this.gs.toC()+"\n");
    }
}