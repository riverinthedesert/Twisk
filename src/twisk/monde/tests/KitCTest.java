package twisk.monde.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class KitCTest {
    KitC kc;

    @BeforeEach
    void setUp() {
        kc=new KitC();
    }

    @Test
    void TestcreerEnvironnement() {
        kc.creerEnvironnement();
    }

    @Test
    void TestcreerFichier() {
        kc.creerFichier("84");
    }

    @Test
    void Testcomplier() {
        kc.compiler();
    }
}