package com.florianschuster.lifecyclecompositedisposable

import android.util.Log
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * A [CompositeDisposable] that observes the [View] va [View.OnAttachStateChangeListener] and
 * calls [CompositeDisposable.dispose] when [onViewDetachedFromWindow] is called.
 *
 * [Disposable] and [DisposableContainer] implementations are delegated to [composite].
 */
class ViewCompositeDisposable(
    private val view: View,
    private val composite: CompositeDisposable = CompositeDisposable()
) : Disposable by composite, DisposableContainer by composite, View.OnAttachStateChangeListener {

    var tag: String = this::class.java.simpleName

    init {
        if (view.isAttachedToWindow || view.windowToken != null) {
            view.addOnAttachStateChangeListener(this)
        } else {
            "$view is not attached".let { Log.e(tag, it) }
        }
    }

    override fun onViewAttachedToWindow(v: View?) {}

    override fun onViewDetachedFromWindow(v: View?) {
        dispose()
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
        view.removeOnAttachStateChangeListener(this)
        composite.dispose()
    }
}