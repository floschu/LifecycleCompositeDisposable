package com.florianschuster.lifecyclecompositedisposable.test.app

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
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
internal class LCDViewTest {

    private lateinit var disposable: Disposable

    @Before
    fun setup() {
        disposable = Observable.interval(250, TimeUnit.MILLISECONDS).subscribe()
    }

    @Test
    fun viewDisposables() {
        launchActivity<TestActivity>().onActivity { activity ->
            val view = TestView(activity)
            activity.frameLayout.addView(view)

            view.disposables.add(disposable)
            assertEquals(1, view.disposables.size)
            assertFalse(disposable.isDisposed)

            activity.frameLayout.removeView(view)
            assertTrue(disposable.isDisposed)

        }
    }
}