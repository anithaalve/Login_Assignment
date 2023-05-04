package com.lilly.ddcs.madelyne.app

import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.example.loginassignment.app.base.MainApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * The Class for Testing {@link MadelyneApplication}.
 */
@Config(sdk = [Build.VERSION_CODES.O])
@RunWith(RobolectricTestRunner::class)
class MadelyneApplicationTest {

  private lateinit var mMainApplication: MainApplication
  private lateinit var mContext: Context

  @Before
  fun setUp() {
    mContext = InstrumentationRegistry.getInstrumentation().context
    mMainApplication = MainApplication()
  }
}
