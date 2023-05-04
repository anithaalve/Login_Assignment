package com.example.loginassignment.ui.login;

import android.content.Context;
import android.os.Build;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.loginassignment.FragmentScenarioRule;
import com.example.loginassignment.R;
import com.example.loginassignment.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

import java.util.Objects;

@Config(sdk = Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {

  private LoginFragment mLoginFragment;
  private NavController mNavController;
  @Rule
  public FragmentScenarioRule<LoginFragment, MainActivity> mFragmentScenarioRule =
      new FragmentScenarioRule<>(LoginFragment.class, MainActivity.class);
  private Context mContext;

  @Before
  public void setUp() {
    mContext = InstrumentationRegistry.getInstrumentation().getContext();
    mFragmentScenarioRule.onFragment(fragment -> mLoginFragment = fragment);

    mNavController = Mockito.mock(NavController.class);
    Navigation.setViewNavController(Objects.requireNonNull(mLoginFragment.getView()),
        mNavController);
  }

  @Test
  public void testOnViewCreated() {
    mLoginFragment.onViewCreated();
    //Mockito.verify(mNavController).navigate(R.id.action_loginFragment_to_homeFragment);
  }
}