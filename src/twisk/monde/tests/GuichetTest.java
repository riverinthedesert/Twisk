package twisk.monde.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuichetTest {
    Guichet g;

    @org.junit.jupiter.api.Test
    void estUnGuichet() {
    }

    @BeforeEach
    void setUp() {
        this.g = new Guichet ("guichet",6);
        Etape e1=new Activite("serveur");
        Etape e2=new Activite("sortie");
        this.g.ajouterSuccesseur(e1);
        e1.ajouterSuccesseur(e2);
    }

    @Test
    void testEstUnGuichet() {
        assert(g.estUnGuichet ()):"erreur dans boolean estUnGuichet";
    }

    @Test
    void toC(){
        assertEquals("P(ids,num_sem_guichet1);\n" +
                "transfert(guichet,serveur);\n" +
                "delai(5,5);\n" +
                "V(ids,num_sem_guichet1);\n" +
                "transfert(serveur,sortie);\n" +
                "delai(5,5);\n", this.g.toC());
        System.out.println(g.toC());
    }
}