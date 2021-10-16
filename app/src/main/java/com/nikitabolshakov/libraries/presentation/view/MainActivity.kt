package com.nikitabolshakov.libraries.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitabolshakov.libraries.data.repository.GithubUsersRepo
import com.nikitabolshakov.libraries.databinding.ActivityMainBinding
import com.nikitabolshakov.libraries.presentation.presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    private val mainPresenter by moxyPresenter { MainPresenter(this, GithubUsersRepo()) }
    private var adapter: UsersRVAdapter? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter.onAttach(this)
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(mainPresenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDetach()
    }
}