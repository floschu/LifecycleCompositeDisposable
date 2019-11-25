package com.florianschuster.lifecyclecompositedisposable.test.app

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.florianschuster.lifecyclecompositedisposable.viewDisposables

class TestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    internal val disposables by viewDisposables()
}