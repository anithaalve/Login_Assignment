package com.example.loginassignment.ui.characterdetail;

import android.content.Context
import android.os.Build
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.platform.app.InstrumentationRegistry
import com.example.loginassignment.FragmentScenarioRule
import com.example.loginassignment.data.repository.CharacterRepository
import com.example.loginassignment.ui.main.MainActivity
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

/**
 * The test Class for {@link CharacterDetailViewModel}.
 */
@Config(sdk = [Build.VERSION_CODES.O])
@RunWith(RobolectricTestRunner::class)
class CharacterDetailViewModelTest {
  @Mock
  private lateinit var mNavController: NavController
  private lateinit var mCharacterDetailFragment: CharacterDetailFragment
  private lateinit var mCharacterDetailViewModel: CharacterDetailViewModel
  private lateinit var mContext: Context
  private lateinit var mView: View
  var mFragmentScenarioRule: FragmentScenarioRule<CharacterDetailFragment?, MainActivity?> = FragmentScenarioRule(CharacterDetailFragment::class.java, MainActivity::class.java)

  @Inject
  lateinit var mCharacterRepository: CharacterRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    mContext = InstrumentationRegistry.getInstrumentation().context
    mView = View(mContext)
    Navigation.setViewNavController(mView, mNavController)
    mFragmentScenarioRule.onFragment { fragment: CharacterDetailFragment -> mCharacterDetailFragment = fragment }
    mCharacterDetailViewModel = CharacterDetailViewModel(mCharacterRepository)
  }

//  @Test
//  fun testOnClick() {
//    mView.id = R.id.tv_lilly_answer_center
//    mAboutConductorViewModel.onClick(mView)
//    Mockito.verify(mNavController).navigate(R.id.action_about_to_lillyAnswerCenter)
//
//  }

}
