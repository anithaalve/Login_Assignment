package com.example.loginassignment.ui.characterdetail;

import android.content.Context
import android.os.Build
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.platform.app.InstrumentationRegistry
import com.example.loginassignment.FragmentScenarioRule
import com.example.loginassignment.R
import com.example.loginassignment.data.repository.CharacterRepository
import com.example.loginassignment.data.repository.RegisterRepository
import com.example.loginassignment.ui.login.LoginFragment
import com.example.loginassignment.ui.login.LoginViewModel
import com.example.loginassignment.ui.main.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

/**
 * The test Class for {@link CharacterDetailViewModel}.
 */
@Config(sdk = [Build.VERSION_CODES.O])
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
  @Mock
  private lateinit var mNavController: NavController
  private lateinit var mLoginFragment: LoginFragment
  private lateinit var mLoginViewModel: LoginViewModel
  private lateinit var mContext: Context
  private lateinit var mView: View
  var mFragmentScenarioRule: FragmentScenarioRule<LoginFragment?, MainActivity?> = FragmentScenarioRule(LoginFragment::class.java, MainActivity::class.java)

  @Mock
  private lateinit var mRegisterRepository: RegisterRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    mContext = InstrumentationRegistry.getInstrumentation().context
    mView = View(mContext)
    Navigation.setViewNavController(mView, mNavController)
    mFragmentScenarioRule.onFragment { fragment: LoginFragment -> mLoginFragment = fragment }
    mLoginViewModel = LoginViewModel(mRegisterRepository)
  }

  @Test
  fun testOnClick() {
    mView.id = R.id.login_button

      mLoginViewModel.mUsername.value = "admin"
      mLoginViewModel.mPassword.value = "admin"
      mLoginViewModel.onClick(mView)
   // Mockito.verify(mNavController).navigate(R.id.action_about_to_lillyAnswerCenter)
  }


  @Test
  fun testOnClick1() {
    mView.id = R.id.signup_button
    mLoginViewModel.onClick(mView)
    Mockito.verify(mNavController).navigate(R.id.action_loginFragment_to_homeFragment)
  }

}
