package com.nikitabolshakov.libraries.presentation.view

import com.nikitabolshakov.libraries.presentation.utils.CounterType

interface IMainView {
    fun setButtonText(type: CounterType, text: String)
}