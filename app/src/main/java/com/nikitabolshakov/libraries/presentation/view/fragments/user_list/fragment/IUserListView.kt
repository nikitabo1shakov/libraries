package com.nikitabolshakov.libraries.presentation.view.fragments.user_list.fragment

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IUserListView : MvpView {
    fun init()
    fun updateList()
}