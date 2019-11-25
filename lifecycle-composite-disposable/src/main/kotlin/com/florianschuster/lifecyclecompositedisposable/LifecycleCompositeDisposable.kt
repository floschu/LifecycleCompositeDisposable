package com.florianschuster.lifecyclecompositedisposable

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * A [CompositeDisposable] that observes the [Lifecycle] of a [LifecycleOwner] and calls
 * [CompositeDisposable.dispose] when a certain [Lifecycle.Event] state is reached.
 *
 * By default [CompositeDisposable.dispose] will be called when [Lifecycle.Event.ON_DESTROY]
 * is reached but the exact destruction state can be set via the [disposeOnEvent] parameter.
 *
 * [Disposable] and [DisposableContainer] implementations are delegated to [composite].
 */
class LifecycleCompositeDisposable(
    private val lifecycle: Lifecycle,
    private val disposeOnEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    private val composite: CompositeDisposable = CompositeDisposable()
) : Disposable by composite, DisposableContainer by composite, LifecycleEventObserver {

    var tag: String = this::class.java.simpleName

    init {
        if (lifecycle.currentState >= Lifecycle.State.INITIALIZED) {
            lifecycle.addObserver(this)
        } else {
            "Cannot observe $lifecycle because it is not Lifecycle.State.INITIALIZED.".let {
                Log.e(tag, it)
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (disposeOnEvent == event) dispose()
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

    /**
     * Delegates to [CompositeDisposable.dispose].
     */
    override fun dispose() {
        lifecycle.removeObserver(this)
        composite.dispose()
    }
}
