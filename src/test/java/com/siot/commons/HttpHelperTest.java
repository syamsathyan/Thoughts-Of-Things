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
package com.siot.commons;

import com.siot.commons.util.StringUtils;
import com.siot.commons.http.HttpHelper;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * @author sathyasy
 */
public class HttpHelperTest {

    public HttpHelperTest() {
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
    public void testGET_String() {
        System.out.println("GET_String");
        String reponse = null;
        try {
            reponse = HttpHelper.GET_String("http://google.com");
            System.out.println(reponse);
        } catch (IOException e) {
            reponse = null;
            e.printStackTrace();
        }
        assertTrue(!StringUtils.isEmpty(reponse));
    }

}
