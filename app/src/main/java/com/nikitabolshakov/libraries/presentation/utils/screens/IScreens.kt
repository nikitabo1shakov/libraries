package com.nikitabolshakov.libraries.presentation.utils.screens

import com.github.terrakok.cicerone.Screen
import com.nikitabolshakov.libraries.data.model.GithubUser

interface IScreens {
    fun openUserListFragment(): Screen
    fun openUserDetailsFragment(user: GithubUser): Screen
}