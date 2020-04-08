import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BuilderTest {
    Hideout hideout;
    /**
     * Set up the basic Cape class
     * @throws Exception There was an error with setting up Junit
     */
    @org.junit.Before
    public void setUp() throws Exception {
        hideout = new UndergroundCaveBuilder("Testing", "Hero").getHideout();
    }

    //Check to make sure the parameters are correct
    @Test
    public void testBasicHideout() {
        assertEquals(10, hideout.getDurability());
        assertEquals( 10, hideout.getHiddenness());
        assertTrue("Testing".equals(hideout.getName()));
        assertTrue("Hero".equals(hideout.getAllegiance()));
    }

    //Test adding a person
    @Test
    public void testAddAndRemoveTeammates() {
        Person temp = new Cape();
        hideout.addPerson(temp);
        assertEquals(1, hideout.getTeamSize());
        hideout.removePerson(temp);
        assertEquals(0, hideout.getTeamSize());
        hideout.addPerson(temp);
        hideout.addPerson(new Cape());
        assertEquals(2, hideout.getTeamSize());
    }
}
