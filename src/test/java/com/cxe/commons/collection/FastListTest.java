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
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * @author sathyasy
 */
public class FastListTest {

    public FastListTest() {
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
    }


    @Test
    public void testAddALot() {
        System.out.println("AddALot");
        long begin = System.nanoTime();
        int count = 100000;
        FastFixedSet set = new FastFixedSet(count);
        for (int i = 0; i < count; i++) {
            set.add(i);
        }
        long end = System.nanoTime();
        assertTrue(set.size() == count);
        System.out.println(set.toString());
        long timeTaken = end - begin;
        System.out.println("AddALot Time Taken:" + timeTaken);
    }

    @Test
    public void testAdd_Compare_ArrayList() {
        System.out.println("###### Add_Sequential_Compare_ArrayList ######");
        int count = 10000000;
        FastFixedList set = new FastFixedList(count);
        long begin = System.nanoTime();
        for (int i = 0; i < count; i++) {
            set.add(i);
        }
        long timeTaken1 = System.nanoTime() - begin;
        double seconds1 = ((double)timeTaken1 / 1000000000);
        System.out.println("Time Taken by FastFixedList(s):" + seconds1);

        ArrayList set2 = new ArrayList(count);
        begin = System.nanoTime();
        for (int i = 0; i < count; i++) {
            set2.add(i);
        }
        long timeTaken2 = System.nanoTime() - begin;
        double seconds2 = ((double)timeTaken2 / 1000000000);
        System.out.println("Time Taken by ArrayList(s):" + seconds2);
        System.out.println("ArrayList took :" + (seconds2 - seconds1) + " seconds more than FixedFastSet");
        assertTrue(set.size() == count);
    }
}
