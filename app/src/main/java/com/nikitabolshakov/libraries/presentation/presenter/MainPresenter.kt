package com.nikitabolshakov.libraries.presentation.presenter

import com.nikitabolshakov.libraries.data.model.GithubUser
import com.nikitabolshakov.libraries.data.repository.GithubUsersRepo
import com.nikitabolshakov.libraries.presentation.view.IMainView
import com.nikitabolshakov.libraries.presentation.view.IUserListPresenter
import com.nikitabolshakov.libraries.presentation.view.UserItemView
import moxy.MvpPresenter

class MainPresenter(
    private var view: IMainView?,
    private val usersRepo: GithubUsersRepo
) : MvpPresenter<IMainView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
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
            // TODO: переход на экран пользователя
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun onAttach(view: IMainView) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }
}