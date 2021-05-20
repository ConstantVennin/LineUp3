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
}
