package com.nikitabolshakov.libraries.data.repository

import com.nikitabolshakov.libraries.data.IDataSource
import com.nikitabolshakov.libraries.data.model.GithubUser
import com.nikitabolshakov.libraries.data.room.User
import com.nikitabolshakov.libraries.data.room.UserDao
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val db: UserDao
) : IGithubUsersRepo {

    private val bs = BehaviorSubject.create<Unit>()

    override fun subscribeOnGithubUsersData(): Observable<List<GithubUser>> {
        return bs.switchMap {
            Observable.combineLatest(
                Observable.just(db.getAll().mapToUser()),
                api.getUsers().toObservable(),
                { fromDatabase, fromNetwork ->
                    if (fromNetwork.isEmpty()) {
                        return@combineLatest fromDatabase
                    } else {
                        saveUsersToDB(fromNetwork)
                        fromNetwork
                    }
                }
            )
        }
    }

    override fun loadUserData() {
        bs.onNext(Unit)
    }

    override fun getUsers(): Observable<List<GithubUser>> = api.getUsers()
        .toObservable()
        .doOnNext { saveUsersToDB(it) }

    private fun saveUsersToDB(list: List<GithubUser>) {
        db.insertAll(*(list.map {
            User(uid = it.id, firstName = it.login, lastName = null)
        }.toTypedArray()))
    }

    private fun List<User>.mapToUser(): List<GithubUser> {
        return this.map { GithubUser(login = it.firstName.orEmpty(), id = it.uid) }
    }
}