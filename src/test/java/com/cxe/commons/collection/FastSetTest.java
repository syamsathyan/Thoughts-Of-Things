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

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * @author sathyasy
 */
public class FastSetTest {

    public FastSetTest() {
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
        System.out.println("Add");
        FastFixedSet set = new FastFixedSet(1);
        String value = "V";
        set.add(value);
        assertTrue(set.size() == 1);
        assertTrue(set.remove(value));
        assertTrue(set.size() == 0);
        System.out.println(set.toString());
    }


    @Test
    public void testAddALot() {
        System.out.println("AddALot");
        long begin = System.nanoTime();
        int count = 100000;
        FastFixedSet set = new FastFixedSet(count);
        for (int i = 0; i < count; i++) {
            set.add(String.valueOf(i));
        }
        long end = System.nanoTime();
        assertTrue(set.size() == count);
        System.out.println(set.toString());
        long timeTaken = end - begin;
        System.out.println("Time Taken:" + timeTaken);
    }

    @Test
    public void testAddALot_Compare_HashSet() {
        System.out.println("AddALot_Compare_HashSet");
        long begin = System.nanoTime();
        int count = 100000;
        FastFixedSet set = new FastFixedSet(count);
        for (int i = 0; i < count; i++) {
            set.add(String.valueOf(i));
        }
        long end = System.nanoTime();
        long timeTaken1 = end - begin;
        System.out.println("Time Taken by FastFixedSet:" + timeTaken1);

        begin = System.nanoTime();
        HashSet set2 = new HashSet(count);
        for (int i = 0; i < count; i++) {
            set2.add(String.valueOf(i));
        }
        end = System.nanoTime();
        assertTrue(set.size() == count);
        long timeTaken2 = end - begin;
        System.out.println("Time Taken by HashSet:" + timeTaken2);

        System.out.println("Time Taken FixedFastSet vs HashSet:" + (timeTaken2 - timeTaken1));
    }

}
