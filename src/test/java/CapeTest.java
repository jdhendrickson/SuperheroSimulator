import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CapeTest {
    Person testCape;

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
        assertEquals(4, testCape.getCurrentHealth());
        assertTrue(testCape.takeDamage(10));
    }

    //A test for changing the values of the cape via decorators
    @Test
    public void basicDecoratorTest() {
        testCape = new PowerDecorator(testCape, 2, 1, 1, 7);
        assertEquals(3, testCape.getDamageDealt());
        assertFalse(testCape.takeDamage(1));
        assertEquals(7, testCape.getCurrentHealth());
        assertFalse(testCape.takeDamage(3));
        assertEquals(6, testCape.getCurrentHealth());
        assertTrue(testCape.takeDamage(10));
    }
}