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
package com.cxe.commons.http;

import com.cxe.commons.io.StreamHelper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Syam Sathyan
 */
public class HttpHelper
{

  private static final String GET = "GET";
  private static final String POST = "POST";

  public static InputStream get( String url ) throws MalformedURLException, IOException
  {
    HttpURLConnection con = (HttpURLConnection) new URL( url ).openConnection();
    con.setRequestMethod( GET );
    return con.getInputStream();
  }

  public static InputStream get( String url, HashMap<String, String> headers ) throws MalformedURLException, IOException
  {
    HttpURLConnection con = (HttpURLConnection) new URL( url ).openConnection();
    con.setRequestMethod( GET );
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      con.setRequestProperty( entry.getKey(), entry.getValue() );
    }
    return con.getInputStream();
  }

  public static String getString( String url ) throws MalformedURLException, IOException
  {
    HttpURLConnection con = (HttpURLConnection) new URL( url ).openConnection();
    con.setRequestMethod( GET );
    StringWriter sw = new StringWriter();
    InputStream ins = con.getInputStream();
    InputStreamReader in = new InputStreamReader( ins );
    StreamHelper.copy( in, sw );
    return sw.toString();
  }

  public static String getString( String url, HashMap<String, String> headers ) throws MalformedURLException, IOException
  {
    HttpURLConnection con = (HttpURLConnection) new URL( url ).openConnection();
    con.setRequestMethod( GET );
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      con.setRequestProperty( entry.getKey(), entry.getValue() );
    }
    StringWriter sw = new StringWriter();
    InputStream ins = con.getInputStream();
    InputStreamReader in = new InputStreamReader( ins );
    StreamHelper.copy( in, sw );
    return sw.toString();
  }

  public static String post( String url, String body ) throws IOException
  {
    HttpURLConnection connection = (HttpURLConnection) new URL( url ).openConnection();
    connection.setDoOutput( true );
    connection.setDoInput( true );
    connection.setInstanceFollowRedirects( false );
    connection.setRequestMethod( POST );
    connection.setRequestProperty( "Content-Type", "application/json" );
    connection.setRequestProperty( "charset", "utf-8" );
    connection.setRequestProperty( "Content-Length", "" + body.getBytes().length );
    connection.setUseCaches( false );

    DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
    wr.writeBytes( body );
    wr.flush();
    InputStream ins = connection.getInputStream();
    BufferedReader reader = new BufferedReader( new InputStreamReader( ins ) );
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      out.append( line );
    }

    ins.close();
    wr.close();
    connection.disconnect();
    return out.toString();
  }

  public static String post( String url, InputStream input, HashMap<String, String> headers ) throws IOException
  {
    HttpURLConnection con = (HttpURLConnection) new URL( url ).openConnection();
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      con.setRequestProperty( entry.getKey(), entry.getValue() );
    }
    con.setDoOutput( true );
    con.setDoInput( true );
    con.setInstanceFollowRedirects( false );
    con.setRequestMethod( POST );
    con.setRequestProperty( "Content-Type", "application/json" );
    con.setRequestProperty( "charset", "utf-8" );
    con.setRequestProperty( "Content-Length", "" + -1 );
    con.setUseCaches( false );

    OutputStream ous = con.getOutputStream();
    StreamHelper.copy( input, ous );
    ous.flush();
    InputStream ins = con.getInputStream();
    BufferedReader reader = new BufferedReader( new InputStreamReader( ins ) );
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      out.append( line );
    }

    StreamHelper.closeQuietly( input );
    StreamHelper.closeQuietly( ous );
    con.disconnect();
    return out.toString();
  }

  public static String post( String url, HashMap<String, String> headers ) throws IOException
  {
    HttpURLConnection con = (HttpURLConnection) new URL( url ).openConnection();
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      con.setRequestProperty( entry.getKey(), entry.getValue() );
    }
    con.setDoInput( true );
    con.setInstanceFollowRedirects( false );
    con.setRequestMethod( POST );
    con.setRequestProperty( "Content-Type", "application/json" );
    con.setRequestProperty( "charset", "utf-8" );
    con.setRequestProperty( "Content-Length", "" + -1 );
    con.setUseCaches( false );

    InputStream ins = con.getInputStream();
    BufferedReader reader = new BufferedReader( new InputStreamReader( ins ) );
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      out.append( line );
    }

    con.disconnect();
    return out.toString();
  }
}
