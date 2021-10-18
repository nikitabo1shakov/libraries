package com.nikitabolshakov.libraries.presentation.view.fragments.user_details

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUserDetailsView : MvpView {
    fun init()
    fun showUserLogin(login: String)
}