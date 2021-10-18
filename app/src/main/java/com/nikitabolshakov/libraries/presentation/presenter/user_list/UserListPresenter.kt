package com.nikitabolshakov.libraries.presentation.presenter.user_list

import com.github.terrakok.cicerone.Router
import com.nikitabolshakov.libraries.data.repository.GithubUsersRepository
import com.nikitabolshakov.libraries.presentation.utils.screens.IScreens
import com.nikitabolshakov.libraries.presentation.view.fragments.user_list.fragment.IUserListView
import moxy.MvpPresenter

class UserListPresenter(
    private val githubUsersRepository: GithubUsersRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<IUserListView>() {

    val userListPresenterImpl = UserListPresenterImpl()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenterImpl.itemClickListener = { itemView ->
            val user = userListPresenterImpl.users[itemView.pos]
            router.navigateTo(screens.openUserDetailsFragment(user))
        }
    }

    private fun loadData() {
        val users = githubUsersRepository.getUsers()
        userListPresenterImpl.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}