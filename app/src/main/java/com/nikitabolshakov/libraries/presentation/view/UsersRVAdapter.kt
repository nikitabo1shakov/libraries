package com.nikitabolshakov.libraries.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikitabolshakov.libraries.databinding.ItemUserBinding

class UsersRVAdapter(
    private val mainPresenter: IUserListPresenter
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root), UserItemView {
        override var pos = -1
        override fun setLogin(text: String) = with(binding) {
            tvLogin.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener { mainPresenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = mainPresenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        mainPresenter.bindView(holder.apply { pos = position })
}