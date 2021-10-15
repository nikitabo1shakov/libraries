package com.nikitabolshakov.libraries.presentation.presenter

import com.nikitabolshakov.libraries.data.model.Counters
import com.nikitabolshakov.libraries.presentation.utils.CounterType
import com.nikitabolshakov.libraries.presentation.view.IMainView

class MainPresenter(
    private var view: IMainView?
) {

    private val counters = Counters()

    fun counterClick(type: CounterType) {
        val nextValue = when (type) {
            CounterType.COUNTER_OF_DAYS -> counters.next(0)
            CounterType.COUNTER_OF_MINUTES -> counters.next(1)
            CounterType.COUNTER_OF_LIKES -> counters.next(2)
        }
        view!!.setButtonText(type, nextValue.toString())
    }

    fun onAttach(view: IMainView) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }
}