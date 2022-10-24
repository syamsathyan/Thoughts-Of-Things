package com.flifi.tot.commons.io;

import java.io.*;

/**
 * @Author :sathyasy on 6/29/15.
 */
public class StreamHelper
{
  private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

  public static int copy( Reader input, Writer output ) throws IOException
  {
    long count = copyLarge( input, output );
    if (count > Integer.MAX_VALUE) {
      return -1;
    }
    return (int) count;
  }

  public static void closeQuietly( InputStream input )
  {
    try {
      if (input != null) {
        input.close();
      }
    } catch ( IOException ioe ) {
      // ignore
    }
  }

  public static void closeQuietly( OutputStream out )
  {
    try {
      if (out != null) {
        out.close();
      }
    } catch ( IOException ioe ) {
      // ignore
    }
  }

  public static int copy( InputStream input, OutputStream output ) throws IOException
  {
    long count = copyLarge( input, output );
    if (count > Integer.MAX_VALUE) {
      return -1;
    }
    return (int) count;
  }

  private static long copyLarge( Reader input, Writer output ) throws IOException
  {
    char[] buffer = new char[DEFAULT_BUFFER_SIZE];
    long count = 0;
    int n = 0;
    while (-1 != (n = input.read( buffer ))) {
      output.write( buffer, 0, n );
      count += n;
    }
    return count;
  }

  private static long copyLarge( InputStream input, OutputStream output )
    throws IOException
  {
    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    long count = 0;
    int n = 0;
    while (-1 != (n = input.read( buffer ))) {
      output.write( buffer, 0, n );
      count += n;
    }
    return count;
  }
}
