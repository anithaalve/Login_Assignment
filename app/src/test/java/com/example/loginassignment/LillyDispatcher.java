package com.example.loginassignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;


public class LillyDispatcher extends Dispatcher {

  final static String ID = "temp-id";
  final static String DATA_SHARE_ID = "b177464b-455e-4ab9-a136-2e7f46505577";

  @Override
  public MockResponse dispatch(RecordedRequest request) {
    try {
      String path = request.getPath();
      if (path.contains("?")) {
        path = path.substring(0, path.indexOf("?"));
      }
      switch (path) {
       case "/user/profile":
          return new MockResponse().setResponseCode(204);
        case "/user":
          if (request.getMethod().toLowerCase().equals("get")) {
            return new MockResponse().setResponseCode(200).setBody(getJson("json/get_user_entity_response.json"));
          }
        default:
          return new MockResponse().setResponseCode(404).setBody(getJson("json/error_response_404.json"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      return new MockResponse().setResponseCode(500).setBody(getJson("json/error_response_500.json"));
    } catch (Exception e) {
      return new MockResponse().setResponseCode(500);
    }
  }

  String getJson(String path) throws IOException {
    URL uri = this.getClass().getClassLoader().getResource(path);
    File file = new File(uri.getPath());
    FileInputStream fin = new FileInputStream(file);
    String ret = convertStreamToString(fin);
    fin.close();
    return ret;
  }

  private String convertStreamToString(InputStream is) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line).append("\n");
    }
    reader.close();
    return sb.toString();
  }
}
