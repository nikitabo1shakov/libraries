package com.nikitabolshakov.libraries.presentation.view.fragment.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitabolshakov.libraries.data.app.App
import com.nikitabolshakov.libraries.data.repository.GithubUsersRepo
import com.nikitabolshakov.libraries.databinding.FragmentUsersBinding
import com.nikitabolshakov.libraries.presentation.presenter.users.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), IUsersView, IBackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val usersPresenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepo(),
            App.instance.router
        )
    }

    var adapter: UsersRVAdapter? = null

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        _binding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(usersPresenter.usersListPresenter)
        _binding?.rvUsers?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = usersPresenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}