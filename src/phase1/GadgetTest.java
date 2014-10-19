package phase1;

import static org.junit.Assert.*;
import static phase1.Gadget.*;

import org.junit.Test;

public class GadgetTest {

    @Test
    public void testOuterWalls() {
//        Gadget walls = outerWalls(10,10); 
//        System.out.println(walls.toString(walls)); 
        Gadget walls2 = outerWalls(15,10); 
        System.out.println(walls2.toString(walls2)); 
//        Gadget walls3 = outerWalls(10,15); 
//        System.out.println(walls3.toString(walls3)); 
    }

}
