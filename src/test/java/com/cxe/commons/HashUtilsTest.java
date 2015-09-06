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
package com.cxe.commons;

import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * @author sathyasy
 */
public class HashUtilsTest {

    public HashUtilsTest() {
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
     * Test of toSlug method, of class Strings.
     */
    @Test
    public void testHash_Unique_For_Different_Objects() {
        System.out.println("#### testHash_Unique_For_Different_Objects ####");
        String axgeorge = "AXGEORGE";
        int hash1 = HashUtils.hash(axgeorge);
        System.out.println("Original Object HashCode=" + axgeorge.hashCode());
        System.out.println("Hash1=" + hash1);
        assertTrue(hash1 != 0);

        String jeanj = "JeanJohny";
        System.out.println("Original Object HashCode=" + jeanj.hashCode());
        int hash2 = HashUtils.hash(jeanj);
        System.out.println("Hash=" + hash2);

        assertTrue(hash1 != hash2);
    }

    @Test
    public void testHash_NoChange_For_Same_Object() {
        System.out.println("#### testHash_NoChange_For_Same_Object ####");
        String axgeorge = "AXGEORGE";
        int hash1 = HashUtils.hash(axgeorge);
        int hash2 = HashUtils.hash(axgeorge);
        assertTrue(hash1 == hash2);
    }

}
