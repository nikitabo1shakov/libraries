package com.nikitabolshakov.libraries.presentation.presenter.user_list

import com.nikitabolshakov.libraries.data.model.GithubUser
import com.nikitabolshakov.libraries.presentation.view.fragments.user_list.adapter.IUserItemView

class UserListPresenter : IUserListPresenter {

    val users = mutableListOf<GithubUser>()

    override var itemClickListener: ((IUserItemView) -> Unit)? = null

    override fun getCount() = users.size

    override fun bindView(view: IUserItemView) {
        val user = users[view.pos]
        user.login?.let { view.setLogin(it) }
    }
}