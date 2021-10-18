package com.nikitabolshakov.libraries.presentation.view.fragments.user_details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IUserDetailsView : MvpView {
    fun init()
    fun showUserLogin(login: String)
}