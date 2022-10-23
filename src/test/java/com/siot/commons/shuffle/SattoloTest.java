package com.siot.commons.shuffle;

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

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testcycle() {
        System.out.println("#### SattoloTest Test_Cycle");
        String[] a = {"S", "O", "Y", "A"};
        System.out.println("Seed:" + Arrays.toString(a));
        String[] originalBackup = {"S", "O", "Y", "A"};
        // shuffle the array
        System.out.println("-------------------------");
        System.out.println("#Going To loop until one of the outcome matches the original");
        Sattolo.cycle(a);
        while(!Arrays.deepEquals(originalBackup, a))
        {
            System.out.println("Shuffled Output:" + Arrays.toString(a));
            Sattolo.cycle(a);
        }

        assertTrue(a.length == originalBackup.length);
    }
}
