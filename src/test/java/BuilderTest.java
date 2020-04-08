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
    }
}
