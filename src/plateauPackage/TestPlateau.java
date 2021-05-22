package plateauPackage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlateau{

    Plateau plateau;
    Position p=new Position(1,1);
    Position p2=new Position(1,2);
    Position p3=new Position(3,2);
    Position p4=new Position(3,8);
    Position p5=new Position(3,7);
    Position p6=new Position(2,8);

    @BeforeEach
    public void initialisation(){
        plateau=new Plateau(4);
    }

    @Test
    public void testArcBloque(){

        plateau.bloquer_arc(p,p2);  
        assertTrue(plateau.arc_bloque(p,p2));
        plateau.liberer_arc(p,p2);
        assertFalse(plateau.arc_bloque(p, p2));
        plateau.bloquer_arc(p3,p4);
        assertFalse(plateau.arc_bloque(p5,p6));

    }

    @Test
    public void testJoueur(){
        plateau.placer_pion(p4,1);
        assertFalse(plateau.case_libre(p4),"Position in array is free, it should not be");
        assertTrue(plateau.case_libre(p),"Position in array is not free, it should be");
        plateau.liberer_plateau(p4);
        assertTrue(plateau.case_libre(p4),"Position in array is not free, it should be");
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
    public void testPassagePossible(){
        assertTrue(p.passagePossible(p2));
        assertTrue(p4.passagePossible(p5));
        assertTrue(p5.passagePossible(p4));
        assertFalse(p.passagePossible(p6));
        assertTrue(p4.passagePossible(p6));
        assertTrue(p6.passagePossible(p4));
    }
    
    @Test
    public void testCreationArcs() {
    	Plateau p1 = new Plateau(3,3);
    	assertTrue(p1.getArcs(0, 1));
    	assertTrue(p1.getArcs(7, 6));
    	assertTrue(p1.getArcs(11, 5));
    	assertTrue(p1.getArcs(7, 13));
    	assertTrue(p1.getArcs(15, 9));
    	
    	assertFalse(p1.getArcs(0, 4));
    	assertFalse(p1.getArcs(4, 6));
    	assertFalse(p1.getArcs(7, 2));
    	assertFalse(p1.getArcs(12, 7));
    	assertFalse(p1.getArcs(13, 17));
    }
    
    @Test
    public void testcouperArc() {
    	Plateau p2 = new Plateau(3,3);
    	assertTrue(p2.getArcs(2, 3));
    	assertTrue(p2.getArcs(3, 2));
    	p2.couperArc(new Position(1,4), new Position(1,3)); //faire +1 à chaque si ça marche pas ^^
    	assertFalse(p2.getArcs(2, 3));
    	assertFalse(p2.getArcs(3, 2));
    }
    
    @Test
    public void testArcsExiste() {
    	Plateau p3 = new Plateau(3,3);
    	assertTrue(p3.arcExiste(new Position(2,2), new Position(2,1)));
    	assertTrue(p3.arcExiste(new Position(2,6), new Position(1,6)));
    	assertTrue(p3.arcExiste(new Position(3,4), new Position(2,4)));
    	assertFalse(p3.arcExiste(new Position(2,6), new Position(1,5)));
    	assertFalse(p3.arcExiste(new Position(3,2), new Position(2,3)));
    	assertFalse(p3.arcExiste(new Position(1,6), new Position(2,5)));
    }
    
    @Test
    public void testCreerArc() {
    	Plateau p4 = new Plateau(3,3);
    	assertFalse(p4.getArcs(0, 4));
    	p4.creerArc(new Position(1,1), new Position(1,5));
    	assertFalse(p4.getArcs(0, 4));
    }
}
