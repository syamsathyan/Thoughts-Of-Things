/*
 * Copyright 2014 Syam Sathyan.
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
package com.flifi.tot.commons.http;

import com.flifi.tot.commons.io.StreamHelper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Syam Sathyan
 */
public class HttpHelper {

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
    private static final String USER_AGENT = "Mozilla/5.0";

    public static int GET(String url, Map<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return con.getResponseCode();
    }

    public static String GETContent(String url, Map<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(GET);
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new IOException("Invalid Response");
        }
    }

    public final static InputStream GETStream(String url, Map<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(GET);
        con.setRequestProperty("User-Agent", USER_AGENT);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return con.getInputStream();
    }

    public final static String POST_Json(String url, InputStream input, Map<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(POST);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", "" + -1);
        con.setUseCaches(false);

        //Copy Streams
        return copyStream_Bidirectional(con, input);
    }

    public final static String POST(String url, InputStream input, Map<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(POST);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Length", "" + -1);
        con.setUseCaches(false);
        //Copy Streams
        return copyStream_Bidirectional(con, input);
    }

    public static int PUT(String url, String content, Map<String, String> headers) throws IOException {
        // Create the connection and use it to upload the new object using the pre-signed URL
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        con.setDoOutput(true);
        con.setRequestMethod(PUT);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setUseCaches(false);
        OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
        out.write(content);
        out.close();
        // Check the HTTP response code. To complete the upload and make the object available,
        // you must interact with the connection object in some way.
        return con.getResponseCode();
    }

    public final static int DELETE(String url, Map<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(DELETE);
        con.setRequestProperty("User-Agent", USER_AGENT);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        // Check the HTTP response code. To complete the upload and make the object available,
        // you must interact with the connection object in some way.
        return con.getResponseCode();
    }

    //########### PRIVATE STREAM Copiers #############
    private final static String copyStream_Bidirectional(HttpURLConnection con, InputStream ins) throws IOException {
        //Write Body
        OutputStream ous = con.getOutputStream();
        StreamHelper.copy(ins, ous);
        ous.flush();
        //Read response
        StringWriter sw = new StringWriter();
        InputStream responseStream = con.getInputStream();
        InputStreamReader in = new InputStreamReader(responseStream);
        StreamHelper.copy(in, sw);
        return sw.toString();
    }
}
