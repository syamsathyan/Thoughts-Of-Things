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
package com.ve.cxe.commons;

import com.ve.cxe.commons.util.BaseGUIDGenerator;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author sathyasy
 */
public class BaseGUIDGeneratorTest {

    public BaseGUIDGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        BaseGUIDGenerator.test(5, 6);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of testBase62 method, of class Strings.
     */
    @Test
    public void testBase62() {
        int length = 6;
        int count = 5;
        System.out.println("-------------BASE 62-------------");
        // create 5 IDs of six letter, base 62 characters
        for (int i = 0; i < count; i++) {
            String base62 = BaseGUIDGenerator.GetBase62(length);
            System.out.println(base62);
            assertTrue(base62.length() == length);
        }
    }

    /**
     * Test of testBase36 method, of class Strings.
     */
    @Test
    public void testBase36() {
        int length = 6;
        int count = 5;
        System.out.println("-------------BASE 36-------------");
        // create 5 IDs of six letter, base 36 characters
        for (int i = 0; i < count; i++) {
            String base36 = BaseGUIDGenerator.GetBase36(length);
            System.out.println(base36);
            assertTrue(base36.length() == length);
        }
    }

}
