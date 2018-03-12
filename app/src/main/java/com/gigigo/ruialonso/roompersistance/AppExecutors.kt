package com.gigigo.ruialonso.roompersistance

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

private val uiContext: CoroutineContext = UI

private val bgContext: CoroutineContext = CommonPool

class AppExecutors {

  fun diskIO(command: () -> Unit) {
    execute(DISK, command)
  }

  fun networkIO(command: () -> Unit) {
    execute(NETWORK, command)
  }

  fun mainThread(command: () -> Unit) {
    execute(UI, command)
  }

  fun execute(context: CoroutineContext, command: () -> Unit) {
    launch(bgContext) {
      command.invoke()
    }
  }

  companion object {
    @JvmField
    val DISK: CoroutineContext = bgContext
    @JvmField
    val NETWORK: CoroutineContext = bgContext
    @JvmField
    val MAIN: CoroutineContext = uiContext
  }
}
