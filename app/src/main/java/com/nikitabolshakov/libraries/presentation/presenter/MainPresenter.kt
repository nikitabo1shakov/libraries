package com.nikitabolshakov.libraries.presentation.presenter

import com.nikitabolshakov.libraries.data.model.Counters
import com.nikitabolshakov.libraries.presentation.view.IMainView
import moxy.MvpPresenter

class MainPresenter(
    private var view: IMainView?,
    private val counters: Counters
) : MvpPresenter<IMainView>() {

    fun clickCounterOfDays() {
        val nextValue = counters.next(0)
        viewState.setCounterOfDaysText(nextValue.toString())
    }

    fun clickCounterOfMinutes() {
        val nextValue = counters.next(1)
        viewState.setCounterOfMinutesText(nextValue.toString())
    }

    fun clickCounterOfLikes() {
        val nextValue = counters.next(2)
        viewState.setCounterOfLikesText(nextValue.toString())
    }

    fun onAttach(view: IMainView) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }
}