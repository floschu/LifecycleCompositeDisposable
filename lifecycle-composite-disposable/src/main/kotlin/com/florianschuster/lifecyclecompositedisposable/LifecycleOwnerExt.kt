package com.florianschuster.lifecyclecompositedisposable

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Lazily creates a [LifecycleCompositeDisposable] that calls [LifecycleCompositeDisposable.LifecycleCompositeDisposable.dispose]
 * in [Lifecycle.Event.ON_DESTROY].
 */
fun LifecycleOwner.lifecycleDisposables(): Lazy<LifecycleCompositeDisposable> =
    lazy { LifecycleCompositeDisposable(lifecycle, Lifecycle.Event.ON_DESTROY) }

/**
 * Lazily creates a [LifecycleCompositeDisposable] that calls [LifecycleCompositeDisposable.LifecycleCompositeDisposable.dispose]
 * in [Lifecycle.Event.ON_STOP].
 */
fun LifecycleOwner.stopLifecycleDisposables(): Lazy<LifecycleCompositeDisposable> =
    lazy { LifecycleCompositeDisposable(lifecycle, Lifecycle.Event.ON_STOP) }

/**
 * Lazily creates a [LifecycleCompositeDisposable] that calls [LifecycleCompositeDisposable.LifecycleCompositeDisposable.dispose]
 * in [Lifecycle.Event.ON_PAUSE].
 */
fun LifecycleOwner.pauseLifecycleDisposables(): Lazy<LifecycleCompositeDisposable> =
    lazy { LifecycleCompositeDisposable(lifecycle, Lifecycle.Event.ON_PAUSE) }