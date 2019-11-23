package com.florianschuster.lifecyclecompositedisposable.test.app

import androidx.appcompat.app.AppCompatActivity
import com.florianschuster.lifecyclecompositedisposable.lifecycleDisposables
import com.florianschuster.lifecyclecompositedisposable.pauseLifecycleDisposables
import com.florianschuster.lifecyclecompositedisposable.stopLifecycleDisposables

class TestActivity : AppCompatActivity() {
    internal val disposables by lifecycleDisposables()
    internal val stopDisposables by stopLifecycleDisposables()
    internal val pauseDisposables by pauseLifecycleDisposables()
}