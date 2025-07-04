package twisk.monde.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireEtapes;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireEtapesTest {
    GestionnaireEtapes lesetapes;

    GestionnaireEtapesTest(){

    }
    @BeforeEach
    void setUp() {
        Activite e1=new Activite("perry");
        Activite e2=new Activite("bopp");
        Activite e3=new Activite("tonton");
        this.lesetapes=new GestionnaireEtapes();
        this.lesetapes.ajouter(e1,e2,e3);
        e1.ajouterSuccesseur(e2);
        e2.ajouterSuccesseur(e3);
    }

    @Test
    void nbEtapes() {
        assertEquals( 3, this.lesetapes.nbEtapes());
    }

    @Test
    void iterator() {
        Iterator<Etape> iterator = lesetapes.iterator();
        assertTrue (iterator.hasNext()); //vrai maintenant
        iterator.next();
        iterator.next();
        iterator.next();// ca passe tout
        assertFalse (iterator.hasNext());    // vide maintenant
    }

    @Test
    void toC() {
        // assertFalse (this.etape.toC().);
        assertEquals("#define entree 0\n" +
                        "#define perry 1\n" +
                        "#define bopp 2\n" +
                        "#define tonton 3\n" +
                        "#define sortie 4\n"
                , this.lesetapes.toC());
        System.out.printf(this.lesetapes.toC());
    }
}