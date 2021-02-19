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

import com.ve.cxe.commons.io.LocalFileHelper;
import com.ve.cxe.commons.util.StringUtils;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author sathyasy
 */
public class LocalFileHelperTest
{

  public LocalFileHelperTest()
  {
  }

  @BeforeClass
  public static void setUpClass()
  {
  }

  @AfterClass
  public static void tearDownClass()
  {
  }

  @Before
  public void setUp()
  {
  }

  @After
  public void tearDown()
  {
  }

  @Test
  public void testSaveFile_YearMonth_Base62()
  {
    System.out.println( "------ SaveFile_Base62 ----" );
    String basePath = Paths.get( "." ).toAbsolutePath().normalize().toString();
    System.out.println( "Base Path: " + basePath );
    Path path = LocalFileHelper.createYearMonth_Base62Path( basePath, 6, "/", ".txt" );

    String exampleString = "Test Syam";
    InputStream stream = new ByteArrayInputStream( exampleString.getBytes( StandardCharsets.UTF_8 ) );

    long size = 0;
    try {
      size = LocalFileHelper.saveFile( stream, path );
      System.out.println( "File Created Size: " + size );
    } catch ( AccessDeniedException exception ) {
      System.out.println( "File Creation Denied by OS" );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    assertTrue( size > 0 );
  }

  @Test
  public void testSaveFile2_YearMonthDay_Base62()
  {
    System.out.println( "------ SaveFile2_Base62 ----" );
    String basePath = Paths.get( "." ).toAbsolutePath().normalize().toString();
    System.out.println( "Base Path: " + basePath );
    Path path = LocalFileHelper.createYearMonthDay_Base62Path( basePath, 6, "/", ".txt" );

    String exampleString = "Test Syam";
    InputStream stream = new ByteArrayInputStream( exampleString.getBytes( StandardCharsets.UTF_8 ) );

    String absolutePath = null;
    try {
      absolutePath = LocalFileHelper.saveFile2( stream, path );
      System.out.println( "File Created in: " + absolutePath );
    } catch ( AccessDeniedException exception ) {
      System.out.println( "File Creation Denied by OS" );
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    assertTrue( !StringUtils.isEmpty( absolutePath ) );
  }
}
