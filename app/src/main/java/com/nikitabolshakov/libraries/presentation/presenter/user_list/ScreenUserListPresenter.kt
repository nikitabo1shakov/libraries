package com.nikitabolshakov.libraries.presentation.presenter.user_list

import com.github.terrakok.cicerone.Router
import com.nikitabolshakov.libraries.data.model.GithubUser
import com.nikitabolshakov.libraries.data.repository.GithubUsersRepository
import com.nikitabolshakov.libraries.presentation.utils.screens.IScreens
import com.nikitabolshakov.libraries.presentation.view.fragments.user_list.fragment.IUserListView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ScreenUserListPresenter(
    private val githubUsersRepository: GithubUsersRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<IUserListView>() {

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        Data().makeLoadData()
        viewState.updateList()

        userListPresenter.itemClickListener = { itemView ->
            val user = userListPresenter.users[itemView.pos]
            router.navigateTo(screens.openUserDetailsFragment(user))
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    inner class Data {
        fun makeLoadData() = Consumer(Producer()).execFromIterable()
    }

    inner class Producer {
        fun fromIterable(): Observable<GithubUser> =
            Observable.fromIterable(githubUsersRepository.getUsers())
    }

    inner class Consumer(private val producer: Producer) {
        private val githubUserObserver = object : Observer<GithubUser> {
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(s: GithubUser?) {
                println("onNext: $s")
                s?.let { userListPresenter.users.add(it) }
            }

            override fun onError(e: Throwable?) {
                println("onError: ${e?.message}")
            }

            override fun onComplete() {
                println("onComplete")
            }
        }

        fun execFromIterable() {
            producer.fromIterable().subscribe(githubUserObserver)
        }
    }
}