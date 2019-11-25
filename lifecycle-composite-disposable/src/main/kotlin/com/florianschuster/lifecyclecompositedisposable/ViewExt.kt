package com.florianschuster.lifecyclecompositedisposable

import android.view.View

/**
 * Lazily creates a [ViewCompositeDisposable] that calls [ViewCompositeDisposable.dispose]
 * when [View] is detached.
 */
fun View.viewDisposables(): Lazy<ViewCompositeDisposable> =
    lazy { ViewCompositeDisposable(this) }