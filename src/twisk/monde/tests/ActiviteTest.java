package twisk.monde.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActiviteTest {
    Activite a;
    ActiviteTest(){

    }

    @BeforeEach
    void setUp() {
        a = new Activite ("manege",5,2);
        Etape b=new Activite("sortie",5,10);
        a.ajouterSuccesseur(b);
    }

    @Test
    void testEstUneActivite() {
        assert(a.estUneActivite ()):"erreur dans boolean estUneActive";
    }

    @Test
    void toC(){
        assertEquals(
                "transfert(manege,sortie);\n" +
                        "delai(5,10);\n", this.a.toC());
        System.out.println(a.toC());
    }
}