package com.nikitabolshakov.libraries.presentation.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

// @AddToEndSingle - есть ещё такой алиас
@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : MvpView {
    fun setCounterOfDaysText(text: String)
    fun setCounterOfMinutesText(text: String)
    fun setCounterOfLikesText(text: String)
}