package com.nikitabolshakov.libraries.presentation.utils.fragmentopener

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nikitabolshakov.libraries.presentation.view.fragment.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}