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

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Ignore;

/**
 * @author sathyasy
 */
public class StringsTest {

    public StringsTest() {
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
    public void testToSlug() {
        System.out.println("toSlug");
        String input = "test slug";
        String expResult = "test-slug";
        String result = Strings.toSlug(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAlphaNumeric method, of class Strings.
     */
    @Test
    public void testIsAlphaNumeric() {
        System.out.println("isAlphaNumeric");
        String string = "abc";
        boolean expResult = true;
        boolean result = Strings.isAlphaNumeric(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of isNumeric method, of class Strings.
     */
    @Ignore
    public void testIsNumeric() {
        System.out.println("isNumeric");
        String string = "1";
        boolean expResult = true;
        boolean result = Strings.isNumeric(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateEmail method, of class Strings.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("validateEmail");
        String email = "shamz@vuotolabs.com";
        boolean expResult = true;
        boolean result = Strings.validateEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of capitalizeFirstLetter method, of class Strings.
     */
    @Test
    public void testCapitalizeFirstLetter() {
        System.out.println("capitalizeFirstLetter");
        String variable = "email";
        String expResult = "Email";
        String result = Strings.capitalizeFirstLetter(variable);
        assertEquals(expResult, result);
    }

    /**
     * Test of concealEmail method, of class Strings.
     */
    @Test
    public void testConcealEmail() {
        System.out.println("concealEmail");
        String email = "shine@gmail.com";
        String expResult = "sxxxx@gmail.com";
        String result = Strings.concealEmail(email, null);
        assertEquals(expResult, result);
    }

    /**
     * Test of concealEmail method, of class Strings.
     */
    @Test
    public void testConcealEmail_longEmail() {
        System.out.println("concealEmail_longEmail");
        String email = "shine.sathyan.jason@finmastersails.co.uk";
        String expResult = "sxxxxxxxxxxxxxxxxxx@finmastersails.co.uk";
        String result = Strings.concealEmail(email, null);
        assertEquals(expResult, result);
    }

    /**
     * Test of concealEmail method, of class Strings.
     */
    @Test
    public void testConcealCharacters_Tail() {
        System.out.println("#### ConcealCharacters_Tail");
        String email = "U0001055UN6TYI";
        String expResult = "U0001055xxxxxx";
        String result = Strings.concealCharacters(email, 6, true, null);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of concealEmail method, of class Strings.
     */
    @Test
    public void testConcealCharacters_Front() {
        System.out.println("#### ConcealCharacters_Front");
        String email = "U0001055UN6TYI";
        String expResult = "xxxxxx55UN6TYI";
        String result = Strings.concealCharacters(email, 6, false, null);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of concealEmail method, of class Strings.
     */
    @Test
    public void testConcealCharacters_Front_With_Mask() {
        System.out.println("#### ConcealCharacters_Front_with_Mask");
        String email = "U0001055UN6TYI";
        String expResult = "XXXXXX55UN6TYI";
        String result = Strings.concealCharacters(email, 6, false, "X");
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeSymbolIfExists method, of class Strings.
     */
    @Test
    public void testRemoveSymbolIfExists() {
        System.out.println("removeSymbolIfExists");
        String symbol = "+";
        String phone = "+4046626413";
        String expResult = "4046626413";
        String result = Strings.removeSymbolIfExists(symbol, phone);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Ignore
    public void testJoin_Iterator_String() {
        System.out.println("join_Iterator_String");
        Iterator iterator = null;
        String separator = "";
        String expResult = "";
        String result = Strings.join(iterator, separator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoin_2args() {
        System.out.println("join_2args");
        String s1 = "a";
        String s2 = "b";
        String expResult = "ab";
        String result = Strings.join(s1, s2);
        assertEquals(expResult, result);
    }

    /**
     * Test of joinFast method, of class Strings.
     */
    @Test
    public void testJoinFast_2args() {
        System.out.println("joinFast_2args");
        String s1 = "a123";
        String s2 = "b456";
        String expResult = "a123b456";
        String result = Strings.joinFast(s1, s2);
        assertEquals(expResult, result);
    }

    /**
     * Test of joinFast method, of class Strings.
     */
    @Test
    public void testJoinFast_2args_FAIL() {
        System.out.println("joinFast_2args_FAIL");
        String s1 = "a123";
        String s2 = "b45";
        String expResult = "a123b456";
        String result = Strings.joinFast(s1, s2);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoin_3args() {
        System.out.println("join_3args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String expResult = "abc";
        String result = Strings.join(s1, s2, s3);
        assertEquals(expResult, result);
    }

    /**
     * Test of joinFast method, of class Strings.
     */
    @Test
    public void testJoinFast_3args() {
        System.out.println("joinFast_3args");
        String s1 = "a123";
        String s2 = "b456";
        String s3 = "C";
        String expResult = "a123b456C";
        String result = Strings.joinFast(s1, s2, s3);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoin_4args() {
        System.out.println("join_4args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String expResult = "abcd";
        String result = Strings.join(s1, s2, s3, s4);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoinFast_4args() {
        System.out.println("joinFast_4args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String expResult = "abcd";
        String result = Strings.joinFast(s1, s2, s3, s4);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoin_5args() {
        System.out.println("join_5args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String expResult = "abcde";
        String result = Strings.join(s1, s2, s3, s4, s5);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoinFast_5args() {
        System.out.println("joinFast_5args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String expResult = "abcde";
        String result = Strings.joinFast(s1, s2, s3, s4, s5);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoin_6args() {
        System.out.println("join_6args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String expResult = "abcdef";
        String result = Strings.join(s1, s2, s3, s4, s5, s6);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoinFast_6args() {
        System.out.println("joinFast_6args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String expResult = "abcdef";
        String result = Strings.joinFast(s1, s2, s3, s4, s5, s6);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoin_7args() {
        System.out.println("join_7args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String s7 = "g";
        String expResult = "abcdefg";
        String result = Strings.join(s1, s2, s3, s4, s5, s6, s7);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoinFast_7args() {
        System.out.println("joinFast_7args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String s7 = "g";
        String expResult = "abcdefg";
        String result = Strings.joinFast(s1, s2, s3, s4, s5, s6, s7);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoin_8args() {
        System.out.println("join_8args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String s7 = "g";
        String s8 = "h";
        String expResult = "abcdefgh";
        String result = Strings.join(s1, s2, s3, s4, s5, s6, s7, s8);
        assertEquals(expResult, result);
    }

    /**
     * Test of join method, of class Strings.
     */
    @Test
    public void testJoinFast_8args() {
        System.out.println("joinFast_8args");
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String s7 = "g";
        String s8 = "h";
        String expResult = "abcdefgh";
        String result = Strings.joinFast(s1, s2, s3, s4, s5, s6, s7, s8);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class Strings.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        String input = "";
        boolean expResult = true;
        boolean result = Strings.isEmpty(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class Strings.
     */
    @Test
    public void testIsEmpty_Fail() {
        System.out.println("isEmpty fail");
        String input = "a";
        boolean expResult = true;
        boolean result = Strings.isEmpty(input);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Strings.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Object obj = "Sim";
        String expResult = "Sim";
        String result = Strings.toString(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of joinFill method, of class Strings.
     */
    @Ignore
    public void testJoinFill_3args() {
        System.out.println("joinFill");
        String string = "";
        String fillCharacter = "";
        int count = 0;
        String expResult = "";
        String result = Strings.joinFill(string, fillCharacter, count);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of joinFill method, of class Strings.
     */
    @Test
    public void testJoinFill_Seperator() {
        System.out.println("JoinFill_Seperator");
        String startString = "Saramma-";
        String fillCharacter = "X";
        char seperator = ',';
        int count = 2;
        String endString = "";
        String expResult = "Saramma-X,X";
        String result = Strings.joinFill(startString, fillCharacter, seperator, count, endString);
        assertEquals(expResult, result);
    }

    /**
     * Test of joinFill method, of class Strings.
     */
    @Test
    public void testJoinFill() {
        System.out.println("joinFill");
        String startString = "Saramma";
        String fillCharacter = ".";
        int count = 1;
        String endString = "Sathyadas";
        String expResult = "Saramma.Sathyadas";
        String result = Strings.joinFill(startString, fillCharacter, count, endString);
        assertEquals(expResult, result);
    }

    /**
     * Test of fill method, of class Strings.
     */
    @Test
    public void testFill_3args_Single() {
        System.out.println("fill");
        String fillCharacter = "a";
        char seperator = ',';
        int count = 1;
        String expResult = "a";
        String result = Strings.fill(fillCharacter, seperator, count);
        assertEquals(expResult, result);
    }

    /**
     * Test of fill method, of class Strings.
     */
    @Test
    public void testFill_3args_Multi() {
        System.out.println("fill");
        String fillCharacter = "a";
        char seperator = ',';
        int count = 2;
        String expResult = "a,a";
        String result = Strings.fill(fillCharacter, seperator, count);
        assertEquals(expResult, result);
    }

    /**
     * Test of fill method, of class Strings.
     */
    @Ignore
    public void testFill_ObjectArr() {
        System.out.println("fill_ObjectArr");
        Object[] fillCharacters = null;
        char seperator = ' ';
        String expResult = "";
        String result = Strings.fill(fillCharacters, seperator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fill method, of class Strings.
     */
    @Test
    public void testFill() {
        System.out.println("fill");
        String fillCharacter = "X";
        int count = 3;
        String expResult = "XXX";
        String result = Strings.fill(fillCharacter, count);
        assertEquals(expResult, result);
    }

    /**
     * Test of fill method, of class Strings.
     */
    @Test
    public void testFill_Large() {
        System.out.println("#### fill Large Case");
        String fillCharacter = "X";
        int count = 100;
        String result = Strings.fill(fillCharacter, count);
        assertEquals(result.length(), 100);
        System.out.println(result);
    }

    /**
     * Test of implode method, of class Strings.
     */
    @Ignore
    public void testImplode() {
        System.out.println("implode");
        char seperator = ' ';
        String[] array = null;
        String expResult = "";
        String result = Strings.implode(seperator, array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
