package com.nikitabolshakov.libraries.presentation.presenter.users

import com.github.terrakok.cicerone.Router
import com.nikitabolshakov.libraries.data.model.GithubUser
import com.nikitabolshakov.libraries.data.repository.GithubUsersRepo
import com.nikitabolshakov.libraries.presentation.view.fragment.users.IUserItemView
import com.nikitabolshakov.libraries.presentation.view.fragment.users.IUsersView
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<IUsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            // TODO: переход на экран пользователя c помощью router.navigateTo
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}