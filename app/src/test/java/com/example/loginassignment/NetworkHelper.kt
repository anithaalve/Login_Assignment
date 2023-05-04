package com.example.loginassignment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowNetworkCapabilities

/**
 * Set Network availability for Test Classes
 */
class NetworkHelper {

  companion object {

    fun setNetworkAvailable(context: Context, isNetwork: Boolean) {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val networkCapabilities = ShadowNetworkCapabilities.newInstance()
      if (isNetwork) {
        Shadows.shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
      } else {
        Shadows.shadowOf(networkCapabilities).removeTransportType(NetworkCapabilities.TRANSPORT_WIFI)
      }
      Shadows.shadowOf(connectivityManager).setNetworkCapabilities(connectivityManager.activeNetwork,
          networkCapabilities)
    }
  }
}