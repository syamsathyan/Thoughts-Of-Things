package com.cxe.commons.shuffle;

import com.cxe.commons.BaseGUIDGenerator;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sathyasy on 9/14/15.
 */
public class SattoloTest {

    public SattoloTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        BaseGUIDGenerator.TestRandomIdGenerator(5, 6);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of testBase62 method, of class Strings.
     */
    @Test
    public void testcycle() {
        String[] a = {"S", "O", "Y", "A"};
        String[]originalBackup = {"S", "O", "Y", "A"};
        int aLength = a.length;
        // shuffle the array
        Sattolo.cycle(a);
        assertTrue(a.length == aLength);
        assertFalse(Arrays.equals(originalBackup,a));
        System.out.println("Shuffled Outputs:" + Arrays.toString(a));
    }
}
