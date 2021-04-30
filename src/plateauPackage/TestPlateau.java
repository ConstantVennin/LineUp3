package plateauPackage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlateau{

    Plateau plateau;

    @BeforeEach
    public void initialisation(){
        plateau=new Plateau(20, 20);
    }

    @Test
    public void testArcBloque(){

        plateau.bloquer_arc("1.1");
        assertTrue(plateau.arc_bloque("1.1"),"Arc is not blocked, it should be");
        assertFalse(plateau.arc_bloque("1.2"),"Arc is blocked, it should not be");
        plateau.bloquer_arc("1.3");
        assertTrue(plateau.arc_bloque("1.3"),"Arc is not blocked, it should be");
        plateau.liberer_arc("1.1");
        assertFalse(plateau.arc_bloque("1.1"),"Arc is blocked, it should not be");

    }

    @Test
    public void testJoueur(){
        plateau.placer_pion("9.1", 1);
        assertFalse(plateau.case_libre("9.1"),"Position in array is free, it should not be");
        assertTrue(plateau.case_libre("9.2"),"Position in array is not free, it should be");
        plateau.liberer_plateau("9.1");
        assertTrue(plateau.case_libre("9.1"),"Position in array is not free, it should be");
    }

    @Test
    public void testgetArray(){
        assertArrayEquals(new int[]{23,2},plateau.getArray("23.2"));
        assertArrayEquals(new int[]{8,1},plateau.getArray("8.1.-1.3"));
        assertArrayEquals(new int[]{12,4},plateau.getArray("12.4.13.42.19"));
        assertArrayEquals(null, plateau.getArray("-1.4"));
        assertArrayEquals(null,plateau.getArray("Test.23"));
    }
}
