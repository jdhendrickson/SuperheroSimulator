import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import main.java.Mediator;
import main.java.Student;
import main.java.Teacher;

import org.junit.Test;

public class CapeTest {
    Cape testCape;

    /**
     * Set up the basic Cape class
     * @throws Exception There was an error with setting up Junit
     */
    @org.junit.Before
    public void setUp() throws Exception {
        testCape = new Cape();
    }

    //A test for getting and dealing damage from a basic cape
    @Test
    public void testDamage() {
        assertEquals(1, testCape.getDamageDealt());
        assertFalse(testCape.takeDamage(1));
        assertEquals(4, testCape.getHealth());
        assertTrue(testCape.takeDamage(10));
    }
}