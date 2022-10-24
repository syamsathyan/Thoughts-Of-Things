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
package com.flifi.tot.commons;

import java.util.Iterator;

import com.flifi.tot.commons.util.StringUtils;
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
public class StringUtilsTest {

    public StringUtilsTest() {
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
        String result = StringUtils.toSlug(input);
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
        boolean result = StringUtils.isAlphaNumeric(string);
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
        boolean result = StringUtils.isNumeric(string);
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
        boolean result = StringUtils.validateEmail(email);
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
        String result = StringUtils.capitalizeFirstLetter(variable);
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
        String result = StringUtils.concealEmail(email, null);
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
        String result = StringUtils.concealEmail(email, null);
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
        String result = StringUtils.concealCharacters(email, 6, true, null);
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
        String result = StringUtils.concealCharacters(email, 6, false, null);
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
        String result = StringUtils.concealCharacters(email, 6, false, "X");
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
        String result = StringUtils.removeSymbolIfExists(symbol, phone);
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
        String result = StringUtils.join(iterator, separator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = StringUtils.joinFast(s1, s2);
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
        String result = StringUtils.joinFast(s1, s2, s3);
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
        String result = StringUtils.joinFast(s1, s2, s3, s4);
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
        String result = StringUtils.joinFast(s1, s2, s3, s4, s5);
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
        String result = StringUtils.joinFast(s1, s2, s3, s4, s5, s6);
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
        String result = StringUtils.joinFast(s1, s2, s3, s4, s5, s6, s7);
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
        String result = StringUtils.joinFast(s1, s2, s3, s4, s5, s6, s7, s8);
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
        boolean result = StringUtils.isEmpty(input);
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
        boolean result = StringUtils.isEmpty(input);
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
        String result = StringUtils.toString(obj);
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
        String result = StringUtils.joinFill(string, fillCharacter, count);
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
        String result = StringUtils.joinFill(startString, fillCharacter, seperator, count, endString);
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
        String result = StringUtils.joinFill(startString, fillCharacter, count, endString);
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
        String result = StringUtils.fill(fillCharacter, seperator, count);
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
        String result = StringUtils.fill(fillCharacter, seperator, count);
        assertEquals(expResult, result);
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
        String result = StringUtils.fill(fillCharacter, count);
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
        String result = StringUtils.fill(fillCharacter, count);
        assertEquals(result.length(), 100);
        System.out.println(result);
    }

    /**
     * Test of implode method, of class Strings.
     */
    @Test
    public void testImplode() {
        System.out.println("#### implode");
        String separator = ".";
        String[] array = {"A", "B", "C", "D"};
        String expResult = "A.B.C.D";
        String result = StringUtils.implode(separator, array);
        assertEquals(expResult, result);
        System.out.println(result);
    }

}
