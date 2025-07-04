package twisk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.ActiviteIG;
import twisk.outils.FabriqueIdentifiant;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteIGTest {
    ActiviteIG act;
    FabriqueIdentifiant id ;

    ActiviteIGTest(){}

    @BeforeEach
    void setUp() {
        this.id = new FabriqueIdentifiant ();
        this.act = new ActiviteIG ("fireblue", id);
    }

    @Test
    void nbSuccesseurs() {
        Assertions.assertNotEquals( "2", this.act.toString());
    }

}