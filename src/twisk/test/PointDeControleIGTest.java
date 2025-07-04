package twisk.test;

import twisk.mondeIG.PointDeControleIG;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.ActiviteIG;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.Iterator;

class PointDeControleIGTest {
    PointDeControleIG pointDeControleIG;
    @BeforeEach
    void setUp() {
        FabriqueIdentifiant identi= new FabriqueIdentifiant ();
        EtapeIG etapeIG=new ActiviteIG("default" ,identi);
        this.pointDeControleIG=new PointDeControleIG(500,500,"left",etapeIG);
    }

    @Test
    void TestgetPosY() {
        Assertions.assertEquals( 425, this.pointDeControleIG.getAbcisseCentre ());
    }
}