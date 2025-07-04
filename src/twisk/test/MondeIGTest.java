package twisk.test;

import twisk.exceptions.ArcException;
import twisk.mondeIG.PointDeControleIG;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.EtapeIG;
import twisk.outils.FabriqueIdentifiant;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MondeIGTest {
    MondeIG mondeIG;
    FabriqueIdentifiant id;

    MondeIGTest(){}

    @BeforeEach
    void setUp() {
        this.mondeIG=new MondeIG();
        this.id = new FabriqueIdentifiant ();
    }

    @Test
    void ajouter() {
        this.mondeIG.ajouter("activite");
        System.out.println(this.mondeIG.toString());
    }

    @Test
    void iterator() {
        this.mondeIG.ajouter("activite");
        Iterator iterator=this.mondeIG.iterator();
        assertTrue (iterator.hasNext());
        iterator.next();
        assertTrue (iterator.hasNext());
    }

    @Test
    void Testajouter() throws ArcException {
        this.mondeIG.ajouter("activite");
        EtapeIG etape=new ActiviteIG("test",id);
        PointDeControleIG pt1=new PointDeControleIG(50,50,"1", etape);
        PointDeControleIG pt2=new PointDeControleIG(50,50,"2", etape);
        this.mondeIG.addPointArc (pt1);
        assertFalse (this.mondeIG.iterateurArc ().hasNext());
    }
}