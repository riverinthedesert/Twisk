package twisk.monde.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {
    Monde monde;
    Monde monde2;
    Monde monde3;

    MondeTest(){

    }
    @BeforeEach
    void setUp() {

        Etape e1=new Activite("perry") ;
        Etape e2=new Activite("bopp");
        Etape e3=new Activite("tonton");
        this.monde=new Monde();
        this.monde.aCommeEntree(e2);
        this.monde.aCommeSortie(e3);
        this.monde.ajouter(e1,e2,e3);
        e1.ajouterSuccesseur(e2);


        //SasEntree entree=new SasEntree();
        //SasSortie sortie=new SasSortie();
        Etape activite1=new Activite("entree",5,3);
        Etape activite2=new Activite("roue",5,10);
        Etape activite3=new Activite("sortie");
        this.monde2=new Monde();
        this.monde2.aCommeEntree(activite1);
        this.monde2.aCommeSortie(activite3);
        this.monde2.ajouter(activite1,activite2,activite3);
        activite1.ajouterSuccesseur(activite2);
        activite2.ajouterSuccesseur(activite3);

        Etape act1=new Activite("brick",5,3);
        Etape act2=new Guichet("mur",2);
        Etape act3=new Activite("roue",5,10);
        Etape act4=new Activite("lit");
        this.monde3=new Monde();
        this.monde3.aCommeEntree(act1);
        this.monde3.aCommeSortie(act4);
        this.monde3.ajouter(act1,act2,act3,act4);
        act1.ajouterSuccesseur(act2);
        act2.ajouterSuccesseur(act3);
        act3.ajouterSuccesseur(act4);
    }

    @Test
    void nbEtapes() {
        Assertions.assertEquals(5, this.monde.nbEtapes());
    }

    @Test
    void iterator() {
        Iterator<Etape> iterator = monde.iterator();
        assertTrue (iterator.hasNext()); //vrai maintenant
        iterator.next();
        iterator.next();
        iterator.next(); // ca passe tout
        assertFalse (iterator.hasNext());    // vide maintenant
    }

    @Test
    void TestToString() {
      /*  assertEquals("#include \"def.h\" \n" +
                "\n" +
                "#define brick 0\n" +
                "#define mur 1\n" +
                "#define roue 2\n" +
                "#define lit 3\n" +
                "#define num_sem_guichet1 1\n" +
                "\n" +
                "void simulation(int ids)\n" +
                "{\n" +
                "entrer(entree);\n" +
                "delai(5,5);\n" +
                "transfert(entree,brick);\n" +
                "delai(5,3);\n" +
                "transfert(brick,mur);\n" +
                "P(ids,num_sem_guichet1);\n" +
                "transfert(mur,roue);\n" +
                "delai(5,10);\n" +
                "V(ids,num_sem_guichet1);\n" +
                "transfert(roue,lit);\n" +
                "delai(5,5);\n" +
                "transfert(lit,sortie);\n" +
                "}", this.monde3.toString());*/
        System.out.print(this.monde3.toString());
    }

    @Test
    void toC() {
        assertEquals("#include \"def.h\" \n" +
                "\n" +
                "#define entree 0\n" +
                "#define brick 1\n" +
                "#define mur 2\n" +
                "#define roue 3\n" +
                "#define lit 4\n" +
                "#define sortie 5\n"+
                "#define num_sem_guichet1 1\n" +
                "\n" +
                "void simulation(int ids)\n" +
                "{\n" +
                "entrer(entree);\n" +
                "delai(5,5);\n" +
                "transfert(entree,brick);\n" +
                "delai(5,3);\n" +
                "transfert(brick,mur);\n" +
                "P(ids,num_sem_guichet1);\n" +
                "transfert(mur,roue);\n" +
                "delai(5,10);\n" +
                "V(ids,num_sem_guichet1);\n" +
                "transfert(roue,lit);\n" +
                "delai(5,5);\n" +
                "transfert(lit,sortie);\n" +
                "}", this.monde3.toC());
        System.out.print(this.monde3.toC());
    }


}