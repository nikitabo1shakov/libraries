package com.nikitabolshakov.libraries.presentation.presenter.user_list

import com.github.terrakok.cicerone.Router
import com.nikitabolshakov.libraries.data.repository.IGithubUsersRepo
import com.nikitabolshakov.libraries.presentation.utils.screens.IScreens
import com.nikitabolshakov.libraries.presentation.view.fragments.user_list.fragment.IUserListView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class ScreenUserListPresenter(
    private val uiScheduler: Scheduler,
    private val screens: IScreens
) : MvpPresenter<IUserListView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersRepo.loadUserData()

        userListPresenter.itemClickListener = { itemView ->
            val user = userListPresenter.users[itemView.pos]
            router.navigateTo(screens.openUserDetailsFragment(user))
        }
    }

    private fun loadData() {
        usersRepo.subscribeOnGithubUsersData()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                userListPresenter.users.clear()
                userListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}