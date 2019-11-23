package com.florianschuster.lifecyclecompositedisposable

import io.reactivex.disposables.Disposable

/**
 * [LifecycleCompositeDisposable] += observable.subscribe()
 */
operator fun LifecycleCompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

/**
 * Add the disposable to a [LifecycleCompositeDisposable].
 */
fun Disposable.addTo(lifecycleCompositeDisposable: LifecycleCompositeDisposable): Disposable =
    apply { lifecycleCompositeDisposable.add(this) }