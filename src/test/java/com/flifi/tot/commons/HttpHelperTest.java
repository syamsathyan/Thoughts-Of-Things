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

import com.flifi.tot.commons.util.StringUtils;
import com.flifi.tot.commons.http.HttpHelper;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static org.junit.Assert.*;

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
    public void testGETContent() throws IOException {
        System.out.println("GETContent");
        String response = HttpHelper.GETContent("http://google.com", null);
        assertNotNull(response);
        System.out.println(response);
        assertTrue(!StringUtils.isEmpty(response));
    }

    @Test
    public void testGET() throws IOException {
        System.out.println("GET");
        int responseCode = HttpHelper.GET("https://httpbin.org/get", null);
        assertEquals(responseCode, 200);
        System.out.println(responseCode);
    }

    @Test
    public void testGET_HttpStatusCode_Custom() throws IOException {
        System.out.println("GET StatusCode Custom");
        int responseCode = HttpHelper.GET("https://httpbin.org/status/500", null);
        assertEquals(responseCode, 500);
        System.out.println(responseCode);
    }

    @Test
    public void testPOST() throws IOException {
        System.out.println("POST");
        String content = "Hello";
        InputStream targetStream = new ByteArrayInputStream(content.getBytes());
        String response = HttpHelper.POST("https://httpbin.org/post", targetStream, null);
        assertNotNull(response);
    }

    @Test
    public void testPOSTJson() throws IOException {
        System.out.println("POSTJson");
        String content = "Hello";
        InputStream targetStream = new ByteArrayInputStream(content.getBytes());
        String response = HttpHelper.POST_Json("https://httpbin.org/post", targetStream, null);
        assertNotNull(response);
    }

    @Test
    public void testPUT() throws IOException {
        System.out.println("PUT");
        String content = "Hello";
        int responseCode = HttpHelper.PUT("https://httpbin.org/put", content, null);
        assertEquals(responseCode, 200);
    }

    @Test
    public void testDELETE() throws IOException {
        System.out.println("DELETE");
        String content = "Hello";
        int responseCode = HttpHelper.DELETE("https://httpbin.org/delete", null);
        assertEquals(responseCode, 200);
    }

}
