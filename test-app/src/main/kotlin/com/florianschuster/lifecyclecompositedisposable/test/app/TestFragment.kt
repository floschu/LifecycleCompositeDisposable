package com.florianschuster.lifecyclecompositedisposable.test.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.florianschuster.lifecyclecompositedisposable.*

class TestFragment : Fragment() {
    internal val disposables by lifecycleDisposables()
    internal val stopDisposables by stopLifecycleDisposables()
    internal val pauseDisposables by pauseLifecycleDisposables()

    internal val viewDisposables by viewLifecycleDisposables()
    internal val viewStopDisposables by viewStopLifecycleDisposables()
    internal val viewPauseDisposables by viewPauseLifecycleDisposables()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = View(requireContext())
}