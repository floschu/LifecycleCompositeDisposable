package com.florianschuster.lifecyclecompositedisposable

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * [DisposableContainer] += observable.subscribe()
 */
operator fun DisposableContainer.plusAssign(disposable: Disposable) {
    add(disposable)
}

/**
 * Add the disposable to a [DisposableContainer].
 */
fun Disposable.addTo(container: DisposableContainer): Disposable =
    apply { container.add(this) }