# LCD <LifecycleCompositeDisposable>

[![version](https://img.shields.io/github/v/tag/floschu/LifecycleCompositeDisposable?color=blue&label=version)](https://bintray.com/floschu/lifecyclecompositedisposable) [![build](https://github.com/floschu/LifecycleCompositeDisposable/workflows/build/badge.svg)](https://github.com/floschu/LifecycleCompositeDisposable/actions) [![license](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

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

The `LifecycleCompositeDisposable` is an RxJava2 `CompositeDisposable` that clears its `Disposable`'s on a specific lifecycle event.

The extension functions for `Activity`, `Fragment`, `LifecycleOwner` and `Application` already have default values for when to clear but can be adjusted accordingly.

### Activity

``` kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val disposables by lifecycleDisposables()
    private val stopDisposables by stopLifecycleDisposables()
    private val pauseDisposables by pauseLifecycleDisposables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val timer = Observable.timer(1, TimeUnit.SECONDS).share()

        timer.subscribe().addTo(disposables) // will dispose in onDestroy
        timer.subscribe().addTo(stopDisposables) // will dispose in onStop
        timer.subscribe().addTo(pauseDisposables) // will dispose in onPause
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
        
        val timer = Observable.timer(1, TimeUnit.SECONDS).share()
        
        timer.subscribe().addTo(disposables) // will dispose in onDestroy
        timer.subscribe().addTo(viewDisposables) // will dispose in onDestroyView
    }
}
```

## author

visit my [website](https://florianschuster.at/).