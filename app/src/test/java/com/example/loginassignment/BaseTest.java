///*
// * BaseTest.java
// * PDS_Madelyne_Android
// *
// * Created by Mohan on 10/4/2019
// * Copyright © 2019 Eli Lilly and Company. All rights reserved.
// *
// */
//
//package com.lilly.ddcs.madelyne;
//
//import com.contentful.java.cda.CDAClient;
//import com.lilly.ddcs.madelyne.contentful.EnqueueResponseRule;
//import com.lilly.ddcs.madelyne.contentful.TestResponse;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.mockwebserver.MockResponse;
//import okhttp3.mockwebserver.MockWebServer;
//
//import static androidx.core.util.Preconditions.checkNotNull;
//
//public abstract class BaseTest {
//  private static final String DEFAULT_TOKEN = "test_token";
//  private static final String DEFAULT_SPACE = "test_space";
//
//  protected CDAClient client;
//
//  private MockWebServer server;
//
//  private List<TestResponse> responseQueue;
//
//  @Rule
//  public EnqueueResponseRule enqueueResponse = new EnqueueResponseRule();
//
//  @Before
//  public void baseSetUp() throws Exception {
//    server = createServer();
//    server.start();
//
//    client = createBuilder().build();
//    if (responseQueue != null) {
//      for (TestResponse response : responseQueue) {
//        enqueue(response);
//      }
//    }
//  }
//
//  @After
//  public void baseTearDown() throws IOException {
//    server.shutdown();
//  }
//
//  private CDAClient.Builder createBuilder() {
//    return CDAClient.builder()
//        .setSpace(DEFAULT_SPACE)
//        .setToken(DEFAULT_TOKEN)
//        .setEndpoint(serverUrl());
//  }
//
//  private String serverUrl() {
//    return "http://" + server.getHostName() + ":" + server.getPort();
//  }
//
//  private MockWebServer createServer() {
//    return new MockWebServer();
//  }
//
//  private void enqueue(TestResponse response) throws Exception {
//    URL resource = getClass().getClassLoader().getResource(response.getFileName());
//    checkNotNull(resource, "File not found: " + response.getFileName());
//    final MockResponse mock = new MockResponse()
//        .throttleBody(1024 * 4, 4, TimeUnit.SECONDS)
//        .setResponseCode(response.getCode())
//        .setBody(getJson(resource.getFile()));
//
//    if (response.headers().size() > 0) {
//      mock.setHeaders(response.headers());
//    }
//
//    server.enqueue(mock);
//  }
//
//  public BaseTest setResponseQueue(List<TestResponse> responseQueue) {
//    this.responseQueue = responseQueue;
//    return this;
//  }
//
//  private String getJson(String path) throws Exception {
//    File file = new File(path);
//    FileInputStream fin = new FileInputStream(file);
//    String ret = convertStreamToString(fin);
//    fin.close();
//    return ret;
//  }
//
//  private String convertStreamToString(InputStream is) throws Exception {
//    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//    StringBuilder sb = new StringBuilder();
//    String line = null;
//    while ((line = reader.readLine()) != null) {
//      sb.append(line).append("\n");
//    }
//    reader.close();
//    return sb.toString();
//  }
//}
