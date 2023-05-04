package com.example.loginassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Gets the value of a [MutableLiveData] or waits for it to have one, with a timeout.
 *
 * Use this extension from host-side (JVM) tests. It's recommended to use it alongside
 * `InstantTaskExecutorRule` or a similar mechanism to execute tasks synchronously.
 */
fun <T> MutableLiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
  var data: T? = null
  val latch = CountDownLatch(1)
  val observer = object : Observer<T> {
    override fun onChanged(o: T?) {
      data = o
      latch.countDown()
      this@getOrAwaitValue.removeObserver(this)
    }
  }
  this.observeForever(observer)

  afterObserve.invoke()

  // Don't wait indefinitely if the LiveData is not set.
  if (!latch.await(time, timeUnit)) {
    this.removeObserver(observer)
    throw TimeoutException("LiveData value was never set.")
  }

  @Suppress("UNCHECKED_CAST")
  return data as T
}

/**
 * Observes a [MutableLiveData] until the `block` is done executing.
 */
fun <T> MutableLiveData<T>.observeForTesting(block: () -> Unit) {
  val observer = Observer<T> { }
  try {
    observeForever(observer)
    block()
  } finally {
    removeObserver(observer)
  }
}

