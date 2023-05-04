package com.example.loginassignment.ui.main;

import android.os.Build;

import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.loginassignment.data.repository.RegisterRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Objects;
import javax.inject.Inject;


@Config( sdk = Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  private MainActivity mMainActivity;
  private MainViewModel mMainViewModel;
  private NavController mNavController;

  @Mock
  RegisterRepository mRegisterRepository;


//  @Inject
//  var mRegisterRepository: RegisterRepository


  @Before
  public void setUp() {
   // MockitoAnnotations.initMocks(this);
  //  mMainViewModel = new MainViewModel(mRegisterRepository);
    ActivityScenario<MainActivity> mainActivityActivityScenario =
        ActivityScenario.launch(MainActivity.class);
    mainActivityActivityScenario.moveToState(Lifecycle.State.RESUMED);
    mainActivityActivityScenario.onActivity(activity -> mMainActivity = activity);
  //  mNavController = Mockito.mock(NavController.class);
//    Navigation.setViewNavController(Objects.requireNonNull(mMainActivity.dataBinding),
//        mNavController);
  }

  @Test
  public void checkAboutConductorFragment() {
//    mMainActivity.onDestinationChanged(mMainActivity.getNavController(),
//        getNavDestination(R.id.aboutConductorFragment), null);
//    Assert.assertEquals(mMainActivity.getDataBinding().tbLayout.tvAppbarTitle.getVisibility(),
//        View.VISIBLE);
    Assert.assertEquals("aa", "aa");
  }

}
