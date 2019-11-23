package com.florianschuster.lifecyclecompositedisposable

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * A [CompositeDisposable] that observes the [Lifecycle] of a [LifecycleOwner] and clears its
 * [Disposable]'s when a certain [Lifecycle.Event] state is reached.
 *
 * By default [CompositeDisposable.clear] will be called when [Lifecycle.Event.ON_DESTROY]
 * is reached but the exact destruction state can be set via the [clearOnEvent] parameter.
 *
 * [Disposable] and [DisposableContainer] implementations are delegated to [composite].
 */
class LifecycleCompositeDisposable(
    private val lifecycle: Lifecycle,
    private val clearOnEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    private val composite: CompositeDisposable = CompositeDisposable()
) : Disposable by composite, DisposableContainer by composite, LifecycleEventObserver {

    init {
        if (lifecycle.currentState >= Lifecycle.State.INITIALIZED) {
            lifecycle.addObserver(this)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (clearOnEvent == event) {
            lifecycle.removeObserver(this)
            clear()
        }
    }

    /**
     * Delegates to [CompositeDisposable.size].
     */
    val size: Int get() = composite.size()

    /**
     * Delegates to [CompositeDisposable.addAll].
     */
    fun addAll(vararg disposables: Disposable): Boolean = composite.addAll(*disposables)

    /**
     * Delegates to [CompositeDisposable.clear].
     */
    fun clear() = composite.clear()
}