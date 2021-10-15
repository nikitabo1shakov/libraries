package com.nikitabolshakov.libraries.presentation.view

import android.os.Bundle
import com.nikitabolshakov.libraries.data.model.Counters
import com.nikitabolshakov.libraries.databinding.ActivityMainBinding
import com.nikitabolshakov.libraries.presentation.presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    private val mainPresenter by moxyPresenter { MainPresenter(this, Counters()) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter.onAttach(this)

        with(binding) {
            counterOfDays.setOnClickListener { mainPresenter.clickCounterOfDays() }
            counterOfMinutes.setOnClickListener { mainPresenter.clickCounterOfMinutes() }
            counterOfLikes.setOnClickListener { mainPresenter.clickCounterOfLikes() }
        }
    }

    override fun setCounterOfDaysText(text: String) {
        binding.counterOfDays.text = text
    }

    override fun setCounterOfMinutesText(text: String) {
        binding.counterOfMinutes.text = text
    }

    override fun setCounterOfLikesText(text: String) {
        binding.counterOfLikes.text = text
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDetach()
    }
}