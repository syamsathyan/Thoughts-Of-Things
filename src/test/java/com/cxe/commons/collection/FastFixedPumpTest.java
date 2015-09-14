/*
 * Copyright 2015 sathyasy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cxe.commons.collection;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * @author sathyasy
 */
public class FastFixedPumpTest {

    public FastFixedPumpTest() {
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

    /**
     * Test of Add and Remove method
     */
    @Test
    public void testAddRemove() {
        System.out.println("FastFixedPumpTest Add");
        FastFixedPump fastFixedPump = new FastFixedPump(1, 1);
        String value = "V";
        fastFixedPump.add(value);
        assertTrue(fastFixedPump.size() == 1);
        assertTrue(fastFixedPump.remove(value));
        assertTrue(fastFixedPump.size() == 0);
    }


    @Test
    public void testAddALot() {
        System.out.println("FastFixedPumpTest AddALot");
        long begin = System.nanoTime();
        int count = 100000;
        FastFixedPump fastFixedPump = new FastFixedPump(count, 4);
        for (int i = 0; i < count; i++) {
            fastFixedPump.add(i);
        }
        long end = System.nanoTime();
        assertTrue(fastFixedPump.size() == count);
        System.out.println(fastFixedPump.toString());
        long timeTaken = end - begin;
        System.out.println("AddALot Time Taken:" + timeTaken);
    }

    //TODO work on why pump is not pumping out last remnansts issue
    @Test
    public void test_Pumping() {
        System.out.println("###### FastFixedPumpTest Pumping ######");
        int count = 4;
        FastFixedPump<Integer> fastFixedPump = new FastFixedPump<Integer>(count, 2);
        for (int i = 0; i < count; i++) {
            fastFixedPump.add(i);
        }
        assertTrue(fastFixedPump.size() == count);
        //First Pump Cannot be null
        Object[] pumped1 = fastFixedPump.pump();
        assertTrue(pumped1 != null);
        Object[] pumped2 = fastFixedPump.pump();
        assertTrue(pumped2 != null);
        Object[] pumped3 = fastFixedPump.pump();
        assertTrue(pumped3 == null);
    }
}
