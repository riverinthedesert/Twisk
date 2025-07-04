package twisk.test;

import twisk.mondeIG.PointDeControleIG;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.EtapeIG;
import static org.junit.jupiter.api.Assertions.*;
import twisk.mondeIG.ArcIG;
import twisk.outils.FabriqueIdentifiant;

class ArcIGTest {
    ArcIG arcIG;
    FabriqueIdentifiant id;
    FabriqueIdentifiant ident ;
    @BeforeEach
    void setUp() {
        this.id = new FabriqueIdentifiant ();
        this.ident = new FabriqueIdentifiant ();
        EtapeIG etapeIG =new twisk.mondeIG.ActiviteIG("moi",id);
        EtapeIG etapeIG2 =new twisk.mondeIG.ActiviteIG("lui",ident);
        this.arcIG=new ArcIG(new PointDeControleIG(50,50,"1",etapeIG),new PointDeControleIG(100,100,"2",etapeIG2));
    }

    @Test
    void getpt1() {
        Assertions.assertNotEquals( 50, this.arcIG.getPts1 ().getAbcisseCentre ());
    }

}