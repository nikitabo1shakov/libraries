package com.nikitabolshakov.libraries.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nikitabolshakov.libraries.R
import com.nikitabolshakov.libraries.databinding.ActivityMainBinding
import com.nikitabolshakov.libraries.presentation.presenter.MainPresenter
import com.nikitabolshakov.libraries.presentation.utils.CounterType

class MainActivity : AppCompatActivity(), IMainView {

    private val mainPresenter = MainPresenter(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter.onAttach(this)

        val listener = View.OnClickListener {
            val type = when (it.id) {
                R.id.counter_of_days -> CounterType.COUNTER_OF_DAYS
                R.id.counter_of_minutes -> CounterType.COUNTER_OF_MINUTES
                R.id.counter_of_likes -> CounterType.COUNTER_OF_LIKES
                else -> throw IllegalStateException(getString(R.string.text_about_error))
            }
            mainPresenter.counterClick(type)
        }

        with(binding) {
            counterOfDays.setOnClickListener(listener)
            counterOfMinutes.setOnClickListener(listener)
            counterOfLikes.setOnClickListener(listener)
        }
    }

    override fun setButtonText(type: CounterType, text: String) {
        when (type) {
            CounterType.COUNTER_OF_DAYS -> binding.counterOfDays.text = text
            CounterType.COUNTER_OF_MINUTES -> binding.counterOfMinutes.text = text
            CounterType.COUNTER_OF_LIKES -> binding.counterOfLikes.text = text
        }
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