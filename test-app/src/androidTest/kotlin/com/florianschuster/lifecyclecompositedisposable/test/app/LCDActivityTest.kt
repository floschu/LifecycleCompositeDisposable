package com.florianschuster.lifecyclecompositedisposable.test.app

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.florianschuster.lifecyclecompositedisposable.LifecycleCompositeDisposable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
internal class LCDActivityTest {

    @get:Rule
    val activityRule = activityScenarioRule<TestActivity>()

    private lateinit var disposable: Disposable

    @Before
    fun setup() {
        disposable = Observable.interval(250, TimeUnit.MILLISECONDS).subscribe()
    }

    @Test
    fun pauseLifecycleCompositeDisposable() {
        test(TestActivity::pauseDisposables)
    }

    @Test
    fun stopLifecycleCompositeDisposable() {
        test(TestActivity::stopDisposables)
    }

    @Test
    fun destroyLifecycleCompositeDisposable() {
        test(TestActivity::disposables)
    }

    private fun test(block: (TestActivity) -> LifecycleCompositeDisposable) {
        activityRule.scenario.onActivity { activity ->
            block(activity).add(disposable)
            assertEquals(1, block(activity).size)
            assertFalse(disposable.isDisposed)
        }

        activityRule.scenario.moveToState(Lifecycle.State.DESTROYED)

        assertTrue(disposable.isDisposed)
    }
}