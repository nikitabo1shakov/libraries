package com.nikitabolshakov.libraries.presentation.presenter.users

import com.nikitabolshakov.libraries.presentation.view.fragment.users.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}