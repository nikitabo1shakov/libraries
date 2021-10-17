package com.nikitabolshakov.libraries.presentation.presenter.main

import com.github.terrakok.cicerone.Router
import com.nikitabolshakov.libraries.presentation.view.activity.main.IMainView
import com.nikitabolshakov.libraries.presentation.utils.fragmentopener.IScreens
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}