# LCD > LifecycleCompositeDisposable

[![version](https://img.shields.io/github/v/tag/floschu/LifecycleCompositeDisposable?color=blue&label=version)](https://bintray.com/flosch/lifecyclecompositedisposable) [![build](https://github.com/floschu/LifecycleCompositeDisposable/workflows/build/badge.svg)](https://github.com/floschu/LifecycleCompositeDisposable/actions) [![license](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

A lifecycle aware RxJava2 CompositeDisposable

## installation

``` groovy
repositories {
    jcenter()
}

dependencies {
    implementation("at.florianschuster.lifecyclecompositedisposable:lifecyclecompositedisposable-core:$version")
}
```

## Concept

A `LifecycleCompositeDisposable` is a RxJava2 `CompositeDisposable` that disposes its `Disposable`'s on a specific lifecycle event.

This library contains extension functions for `LifecycleOwner` (-> meaning also `FragmentActivity` and `Fragment`) to lazily create such a `LifecycleCompositeDisposable`.

### Activity

``` kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val disposables by lifecycleDisposables()
    private val stopDisposables by stopLifecycleDisposables()
    private val pauseDisposables by pauseLifecycleDisposables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val interval = Observable.interval(1, TimeUnit.SECONDS).share()

        interval.subscribe().addTo(disposables) // will dispose in onDestroy
        interval.subscribe().addTo(stopDisposables) // will dispose in onStop
        interval.subscribe().addTo(pauseDisposables) // will dispose in onPause
    }
}
```

### Fragment 

``` kotlin
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val disposables by lifecycleDisposables()
    private val viewDisposables by viewLifecycleDisposables()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val interval = Observable.interval(1, TimeUnit.SECONDS).share()
        
        interval.subscribe().addTo(disposables) // will dispose in onDestroy
        interval.subscribe().addTo(viewDisposables) // will dispose in onDestroyView
    }
}
```

### View

``` kotlin
class TestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val disposables by viewDisposables()
    
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Observable.interval(1, TimeUnit.SECONDS).subscribe().addTo(disposables) // will dispose in onDetachedFromWindow
    }
}
```

### Manual Creation

``` kotlin
val disposables = LifecycleCompositeDisposable(lifecycle, Lifecycle.Event.ON_DESTROY)
Observable.interval(1, TimeUnit.SECONDS).subscribe().addTo(disposables)
```

## author

visit my [website](https://florianschuster.at/).
