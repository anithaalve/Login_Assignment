package com.example.loginassignment.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Simple [SingleLiveEvent] is used to send events from fragment/activity to view model
 * which can observe multi Observers in a simple way.
 *
 * @param <T> generic type
</T> */
class SingleLiveEvent<T> : MutableLiveData<T>() {
  private val mPending = AtomicBoolean(false)
  
  /**
   * @param owner [LifecycleOwner].
   * @param observer [Observer].
   */
  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
    // Observe the internal MutableLiveData
    super.observe(owner,
      Observer<T> { t: T ->
        if (mPending.compareAndSet(true, false)) {
          observer.onChanged(t)
        }
      })
  }
  
  /**
   * Set the value.
   *
   * @param t generic type
   */
  @MainThread
  override fun setValue(t: T?) {
    mPending.set(true)
    super.setValue(t)
  }
  
  /**
   * Used for cases where T is Void, to make calls cleaner.
   */
  @MainThread
  fun call() {
    value = null
  }
}
