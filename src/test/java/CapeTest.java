import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import main.java.Cape;
import main.java.Person;
import main.java.PowerDecorator;
import org.junit.Test;

public class CapeTest {
    Person testCape;

    /**
     * Set up the basic Cape class.
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
        testCape = new PowerDecorator(testCape, 2, 1, 1, 2);
        assertEquals(3, testCape.getDamageDealt());
        assertFalse(testCape.takeDamage(1));
        assertEquals(7, testCape.getCurrentHealth());
        assertFalse(testCape.takeDamage(3));
        assertEquals(6, testCape.getCurrentHealth());
        assertTrue(testCape.takeDamage(10));
    }

    //A test for testing healing and regens
    @Test
    public void testHealing() {
        testCape.setCurrentHealth(100);
        assertEquals(5, testCape.getCurrentHealth());
        testCape.takeDamage(50);
        testCape.setCurrentHealth(100);
        assertEquals(5, testCape.getCurrentHealth());
        testCape.setCurrentHealth(5);
        assertEquals(5, testCape.getCurrentHealth());
    }

    //A test for testing the fighting functions
    @Test
    public void testFighting() {
        Person opponent = new Cape();
        assertTrue(testCape.fight(opponent));
        opponent = new PowerDecorator(opponent, 10, 10, 0, 10);
        assertFalse(testCape.fight(opponent));
        testCape = new PowerDecorator(testCape, 50, 50, 50, 50);
        assertTrue(testCape.fight(opponent));

    }
}