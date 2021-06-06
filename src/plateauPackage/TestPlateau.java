package plateauPackage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import joueurPackage.Joueur;

/**
 * Classe de test
 */
public class TestPlateau{

    Plateau plateau;
    Plateau pl1;
    Position p=new Position(1,1);
    Position p6=new Position(2,8);
    Position p7=new Position(4,8);
    static Joueur j1;
    static Joueur j2;

    @BeforeAll //Joueur a un id automatique donc on doit faire une beforeAll
    public static void joueurs(){
        j1=new Joueur(null,9);
        j2=new Joueur(null,9);
    }

    @BeforeEach
    public void initialisation(){
        plateau=new Plateau(3);
        pl1 = new Plateau(3,3);
        System.out.println("Joueur 1 "+j1.getJoueurId());
    }


    @Test
    public void testJoueur(){

        plateau.placer_pion(j1.getPion(0),p);
        assertFalse(plateau.case_libre(p),"Position in array is free, it should not be");
        assertTrue(plateau.case_libre(p6),"Position in array is not free, it should be");
        plateau.liberer_plateau(p);
        assertTrue(plateau.case_libre(p),"Position in array is not free, it should be");
    }

    @Test
    public void testgetArray(){

        assertArrayEquals(new int[]{23,2},plateau.getArray("23.2"));
        assertArrayEquals(new int[]{8,1},plateau.getArray("8.1.-1.3"));
        assertArrayEquals(new int[]{12,4},plateau.getArray("12.4.13.42.19"));
        assertArrayEquals(null, plateau.getArray("-1.4"));
        assertArrayEquals(null,plateau.getArray("Test.23"));
    }
    
    @Test
    public void testCreationArcs() {

    	Plateau pl1 = new Plateau(3,3);
    	assertTrue(pl1.getArcs(0, 1));
    	assertTrue(pl1.getArcs(7, 6));
    	assertTrue(pl1.getArcs(11, 5));
    	assertTrue(pl1.getArcs(7, 13));
    	assertTrue(pl1.getArcs(15, 9));
    	
    	assertFalse(pl1.getArcs(0, 4));
    	assertFalse(pl1.getArcs(4, 6));
    	assertFalse(pl1.getArcs(7, 2));
    	assertFalse(pl1.getArcs(12, 7));
    	assertFalse(pl1.getArcs(13, 17));
    }
    
    @Test
    public void testcouperArc() {

    	assertTrue(pl1.getArcs(2, 3));
    	assertTrue(pl1.getArcs(3, 2));
    	pl1.couperArc(new Position(1,4), new Position(1,3)); //faire +1 à chaque si ça marche pas ^^
    	assertFalse(pl1.getArcs(2, 3));
    	assertFalse(pl1.getArcs(3, 2));
    }
    
    @Test
    public void testArcsExiste() {

    	assertTrue(pl1.arcExiste(new Position(2,2), new Position(2,1)));
    	assertTrue(pl1.arcExiste(new Position(2,6), new Position(1,6)));
    	assertTrue(pl1.arcExiste(new Position(3,4), new Position(2,4)));
    	assertFalse(pl1.arcExiste(new Position(2,6), new Position(1,5)));
    	assertFalse(pl1.arcExiste(new Position(3,2), new Position(2,3)));
    	assertFalse(pl1.arcExiste(new Position(1,6), new Position(2,5)));
    }
    
    @Test
    public void testCreerArc() {

    	assertFalse(pl1.getArcs(0, 4));
    	pl1.creerArc(new Position(1,1), new Position(1,5));
    	assertTrue(pl1.getArcs(0, 4));
    }

    @Test
    public void testPositions(){
        assertTrue(plateau.placementPossible(p));
        assertTrue(plateau.placementPossible(p6));
        assertFalse(plateau.placementPossible(p7));
        plateau.placer_pion(j1.getPion(0),p6);
        assertFalse(plateau.placementPossible(p6));

    }

   /* @Deprecated
    @Test
    public void testArcBloque(){

        plateau.bloquer_arc(p,p2);  
        assertTrue(plateau.arc_bloque(p,p2));
        plateau.liberer_arc(p,p2);
        assertFalse(plateau.arc_bloque(p, p2));
        plateau.bloquer_arc(p3,p4);
        assertFalse(plateau.arc_bloque(p5,p6));

    }

    @Deprecated
    @Test
    public void testPassagePossible(){
        assertTrue(p.passagePossible(p2));
        assertTrue(p4.passagePossible(p5));
        assertTrue(p5.passagePossible(p4));
        assertFalse(p.passagePossible(p6));
        assertTrue(p4.passagePossible(p6));
        assertTrue(p6.passagePossible(p4));
    }*/
}
