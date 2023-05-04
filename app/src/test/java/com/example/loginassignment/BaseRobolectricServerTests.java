package com.example.loginassignment;

import android.content.Context;
import com.example.loginassignment.data.local.AppDatabase;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class BaseRobolectricServerTests {

  protected Context mContext;
  protected Gson mGson;
  protected AppDatabase mDatabase;
  protected MockWebServer mMockWebServer;
  protected Dispatcher dispatcher = new LillyDispatcher();


  private void baseSetUp() throws IOException {
    MockitoAnnotations.initMocks(this);
    mContext = ApplicationProvider.getApplicationContext();
    mMockWebServer = new MockWebServer();
    mMockWebServer.start();
  }

  @Before
  public void setUp() throws Exception {
    baseSetUp();
    mDatabase = Room.inMemoryDatabaseBuilder(mContext, AppDatabase.class)
        .allowMainThreadQueries()
        .build();
    mGson = new Gson();
    mMockWebServer.setDispatcher(dispatcher);
  }

  @After
  public void tearDown() throws IOException {
    mMockWebServer.shutdown();
  }

}
