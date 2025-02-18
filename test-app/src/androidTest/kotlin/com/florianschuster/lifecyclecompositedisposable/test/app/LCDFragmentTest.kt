package com.florianschuster.lifecyclecompositedisposable.test.app

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.florianschuster.lifecyclecompositedisposable.LifecycleCompositeDisposable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
internal class LCDFragmentTest {

    private lateinit var disposable: Disposable

    @Before
    fun setup() {
        disposable = Observable.interval(250, TimeUnit.MILLISECONDS).subscribe()
    }

    @Test
    fun pauseLifecycleCompositeDisposable() {
        testLifecycle(TestFragment::pauseDisposables)
    }

    @Test
    fun stopLifecycleCompositeDisposable() {
        testLifecycle(TestFragment::stopDisposables)
    }

    @Test
    fun destroyLifecycleCompositeDisposable() {
        testLifecycle(TestFragment::disposables)
    }

    @Test
    fun pauseViewLifecycleCompositeDisposable() {
        testLifecycle(TestFragment::viewPauseDisposables)
    }

    @Test
    fun stopViewLifecycleCompositeDisposable() {
        testLifecycle(TestFragment::viewStopDisposables)
    }

    @Test
    fun destroyViewLifecycleCompositeDisposable() {
        testLifecycle(TestFragment::viewDisposables)
    }

    private fun testLifecycle(block: (TestFragment) -> LifecycleCompositeDisposable) {
        val scenario = launchFragment<TestFragment>()

        scenario.onFragment { fragment ->
            block(fragment).add(disposable)
            assertEquals(1, block(fragment).size)
            assertFalse(disposable.isDisposed)
        }

        scenario.moveToState(Lifecycle.State.DESTROYED)

        assertTrue(disposable.isDisposed)
    }
}