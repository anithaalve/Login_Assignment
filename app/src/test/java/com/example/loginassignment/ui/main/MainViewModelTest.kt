package com.example.loginassignment.ui.main

import android.os.Build
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.loginassignment.data.repository.RegisterRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*
import javax.inject.Inject

/**
 * The test Class for {@link HelpViewModel}.
 */
@Config( sdk = [Build.VERSION_CODES.O])
@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

  @get:Rule
  var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

  private lateinit var mMainViewModel: MainViewModel

  @Inject
  lateinit var mRegisterRepository: RegisterRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    mMainViewModel = MainViewModel(mRegisterRepository)
  }

}
