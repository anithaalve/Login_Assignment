package com.example.loginassignment.ui.characterdetail;

import android.content.Context;
import android.os.Build;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

import java.io.File;
import java.util.Objects;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.loginassignment.FragmentScenarioRule;
import com.example.loginassignment.ui.main.MainActivity;

@Config(sdk = Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4.class)
public class CharacterDetailFragmentTest {

  private CharacterDetailFragment mCharacterDetailFragment;
  private NavController mNavController;
  @Rule
  public FragmentScenarioRule<CharacterDetailFragment, MainActivity> mFragmentScenarioRule =
      new FragmentScenarioRule<>(CharacterDetailFragment.class, MainActivity.class);
  private Context mContext;

  @Before
  public void setUp() {
    mContext = InstrumentationRegistry.getInstrumentation().getContext();
    mFragmentScenarioRule.onFragment(fragment -> mCharacterDetailFragment = fragment);

    mNavController = Mockito.mock(NavController.class);
    Navigation.setViewNavController(Objects.requireNonNull(mCharacterDetailFragment.getView()),
        mNavController);
  }

//  @Test public void testLoginInstructionsClick() {
//    mCharacterDetailFragment.getDataBinding().tvLoginInstructions.performClick();
//    Mockito.verify(mNavController).navigate(R.id.action_about_to_login);
//  }

}
