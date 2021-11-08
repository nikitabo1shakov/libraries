package com.nikitabolshakov.libraries.presentation.view.fragments.user_list.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitabolshakov.libraries.data.GlideImageLoader
import com.nikitabolshakov.libraries.data.app.App
import com.nikitabolshakov.libraries.databinding.FragmentUserListBinding
import com.nikitabolshakov.libraries.presentation.presenter.user_list.ScreenUserListPresenter
import com.nikitabolshakov.libraries.presentation.utils.IBackButtonListener
import com.nikitabolshakov.libraries.presentation.utils.screens.Screens
import com.nikitabolshakov.libraries.presentation.view.fragments.user_list.adapter.UserListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment : MvpAppCompatFragment(), IUserListView, IBackButtonListener {

    companion object {
        fun newInstance() = UserListFragment().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val screenUserListPresenter by moxyPresenter {
        ScreenUserListPresenter(
            AndroidSchedulers.mainThread(),
            Screens()
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var userListAdapter: UserListAdapter? = null

    private var binding: FragmentUserListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun init() {
        binding?.run {
            this.userList.layoutManager = LinearLayoutManager(context)
            userListAdapter =
                UserListAdapter(screenUserListPresenter.userListPresenter, GlideImageLoader())
            this.userList.adapter = userListAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        userListAdapter?.notifyDataSetChanged()
    }

    override fun backPressed() = screenUserListPresenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}