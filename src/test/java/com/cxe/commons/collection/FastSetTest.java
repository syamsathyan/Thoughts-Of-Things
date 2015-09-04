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
        System.out.println("AddALot Time Taken:" + timeTaken);
    }

    @Test
    public void testAdd_Sequential_Compare_HashSet() {
        System.out.println("###### Add_Sequential_Compare_HashSet ######");
        long begin = System.nanoTime();
        int count = 100000;
        FastFixedSet set = new FastFixedSet(count);
        for (int i = 0; i < count; i++) {
            set.add(String.valueOf(i));
        }
        long timeTaken1 = System.nanoTime() - begin;
        double seconds1 = ((double)timeTaken1 / 1000000000);
        System.out.println("Time Taken by FastFixedSet(s):" + seconds1);

        begin = System.nanoTime();
        HashSet set2 = new HashSet(count);
        for (int i = 0; i < count; i++) {
            set2.add(String.valueOf(i));
        }
        long timeTaken2 = System.nanoTime() - begin;
        assertTrue(set.size() == count);
        double seconds2 = ((double)timeTaken2 / 1000000000);
        System.out.println("Time Taken by HashSet(s):" + seconds2);

        System.out.println("HashSet took :" + (seconds2 - seconds1) + " seconds more than FixedFastSet");
    }

    @Test
    public void testAdd_Random_Compare_HashSet() {
        System.out.println("###### Add_Random_Compare_HashSet #######");
        int count = 100000;
        FastFixedSet set = new FastFixedSet(count);
        //Warm up with Add
        for (int i = 0; i < count; i++) {
            set.add(String.valueOf(i));
        }
        //Odd position removal to make random gaps for next insertion
        for (int i = 0; i < count; i = i + 2) {
            set.remove(String.valueOf(i));
        }

        //Start Test1
        long begin = System.nanoTime();
        for (int i = 0; i < count; i++) {
            set.add(String.valueOf(i));
        }
        long timeTaken1 = System.nanoTime() - begin;
        double seconds1 = ((double)timeTaken1 / 1000000000);
        System.out.println("Time Taken by FastFixedSet(s):" + seconds1);
        assertTrue(set.size() == count);

        //Warm up with Add
        HashSet set2 = new HashSet(count);
        for (int i = 0; i < count; i++) {
            set2.add(String.valueOf(i));
        }
        //Odd position removal to make random gaps for next insertion
        for (int i = 0; i < count; i = i + 2) {
            set2.remove(String.valueOf(i));
        }
        //Start Test2
        begin = System.nanoTime();
        for (int i = 0; i < count; i++) {
            set2.add(String.valueOf(i));
        }
        long timeTaken2  = System.nanoTime() - begin;
        double seconds2 = ((double)timeTaken2 / 1000000000);
        System.out.println("Time Taken by HashSet(s):" + seconds2);
        System.out.println("HashSet took :" + (seconds2 - seconds1) + " seconds more than FixedFastSet");
        assertTrue(set2.size() == count);
    }

}
