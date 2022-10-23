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
package com.siot.commons.http;

import com.siot.commons.io.StreamHelper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
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

    public final static InputStream GET_Stream(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(GET);
        return con.getInputStream();
    }

    public final static InputStream GET_Stream(String url, HashMap<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(GET);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
        return con.getInputStream();
    }

    public final static String GET_String(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(GET);
        StringWriter sw = new StringWriter();
        InputStream ins = con.getInputStream();
        InputStreamReader in = new InputStreamReader(ins);
        StreamHelper.copy(in, sw);
        return sw.toString();
    }

    public final static String DELETE(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(DELETE);
        StringWriter sw = new StringWriter();
        InputStream ins = con.getInputStream();
        InputStreamReader in = new InputStreamReader(ins);
        StreamHelper.copy(in, sw);
        return sw.toString();
    }

    public final static String GET_String(String url, HashMap<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(GET);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
        StringWriter sw = new StringWriter();
        InputStream ins = con.getInputStream();
        InputStreamReader in = new InputStreamReader(ins);
        StreamHelper.copy(in, sw);
        return sw.toString();
    }

    public final static String POST_Json(String url, String body) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(POST);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", "" + body.getBytes().length);
        con.setUseCaches(false);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(body);
        wr.flush();
        //READ Response
        StringWriter sw = new StringWriter();
        InputStream ins = con.getInputStream();
        InputStreamReader in = new InputStreamReader(ins);
        StreamHelper.copy(in, sw);
        return sw.toString();
    }

    public final static String POST_Json(String url, InputStream input, HashMap<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(POST);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", "" + -1);
        con.setUseCaches(false);

        //Copy Streams
        return copyStream_Bidirectional(con, input);
    }

    public final static String POST_Raw(String url, InputStream input, HashMap<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(POST);
        con.setRequestProperty("Content-Length", "" + -1);
        con.setUseCaches(false);
        //Copy Streams
        return copyStream_Bidirectional(con, input);
    }

    public final static String POST_Raw(String url, InputStream input) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(POST);
        con.setRequestProperty("Content-Length", "" + -1);
        con.setUseCaches(false);

        //Copy Streams
        return copyStream_Bidirectional(con, input);
    }

    public final static String PUT_Raw(String url, InputStream input, HashMap<String, String> headers) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(PUT);
        con.setRequestProperty("Content-Length", "" + -1);
        con.setUseCaches(false);

        //Copy Streams
        return copyStream_Bidirectional(con, input);
    }

    public final static String PUT_Raw(String url, InputStream input) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod(PUT);
        con.setRequestProperty("Content-Length", "" + -1);
        con.setUseCaches(false);

        //Copy Streams
        return copyStream_Bidirectional(con, input);
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
