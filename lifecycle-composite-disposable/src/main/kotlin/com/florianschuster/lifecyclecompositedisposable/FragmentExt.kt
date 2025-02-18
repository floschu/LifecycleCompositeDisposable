package com.florianschuster.lifecyclecompositedisposable

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

/**
 * Lazily creates a [LifecycleCompositeDisposable] that calls [LifecycleCompositeDisposable.LifecycleCompositeDisposable.dispose]
 * in [Fragment.getViewLifecycleOwner] [Lifecycle.Event.ON_DESTROY].
 */
fun Fragment.viewLifecycleDisposables(): Lazy<LifecycleCompositeDisposable> =
    lazy { LifecycleCompositeDisposable(viewLifecycleOwner.lifecycle, Lifecycle.Event.ON_DESTROY) }

/**
 * Lazily creates a [LifecycleCompositeDisposable] that calls [LifecycleCompositeDisposable.LifecycleCompositeDisposable.dispose]
 * in [Fragment.getViewLifecycleOwner] [Lifecycle.Event.ON_STOP].
 */
fun Fragment.viewStopLifecycleDisposables(): Lazy<LifecycleCompositeDisposable> =
    lazy { LifecycleCompositeDisposable(viewLifecycleOwner.lifecycle, Lifecycle.Event.ON_STOP) }

/**
 * Lazily creates a [LifecycleCompositeDisposable] that calls [LifecycleCompositeDisposable.LifecycleCompositeDisposable.dispose]
 * in [Fragment.getViewLifecycleOwner] [Lifecycle.Event.ON_PAUSE].
 */
fun Fragment.viewPauseLifecycleDisposables(): Lazy<LifecycleCompositeDisposable> =
    lazy { LifecycleCompositeDisposable(viewLifecycleOwner.lifecycle, Lifecycle.Event.ON_PAUSE) }