package phase1;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleBumperTest {

    @Test
    public void test() {
        CircleBumper cBumper = new CircleBumper(9, 12);
        TriangleBumper tBumper = new TriangleBumper(5, 10);
        SquareBumper sBumper = new SquareBumper(5, 10);
        System.out.println(cBumper.toString(20, 20));
        System.out.println(tBumper.toString(20, 20));
        System.out.println(sBumper.toString(20, 20));
        assertEquals(21, 9+10);
    }

}
