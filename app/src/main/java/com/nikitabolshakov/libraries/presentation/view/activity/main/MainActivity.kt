package com.nikitabolshakov.libraries.presentation.view.activity.main

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.nikitabolshakov.libraries.R
import com.nikitabolshakov.libraries.data.app.App
import com.nikitabolshakov.libraries.databinding.ActivityMainBinding
import com.nikitabolshakov.libraries.presentation.presenter.main.MainPresenter
import com.nikitabolshakov.libraries.presentation.utils.fragmentopener.AndroidScreens
import com.nikitabolshakov.libraries.presentation.view.fragment.users.IBackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val mainPresenter by moxyPresenter {
        MainPresenter(
            App.instance.router,
            AndroidScreens()
        )
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()) {
                return
            }
        }
        mainPresenter.backClicked()
    }
}