package com.gigigo.ruialonso.roompersistance.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import com.gigigo.ruialonso.roompersistance.AppDatabase
import com.gigigo.ruialonso.roompersistance.models.Project
import com.gigigo.ruialonso.roompersistance.models.Repository
import com.gigigo.ruialonso.roompersistance.models.User
import java.text.SimpleDateFormat
import java.util.Locale

class MainPresenter(private val context: Context, private var lifecycleOwner: LifecycleOwner) {

  private var view: MainActivity? = null
  private val database: AppDatabase by lazy { AppDatabase.getInstance(context) }

  private lateinit var usersObservable: LiveData<MutableList<User>>
  private lateinit var projectsObservable: LiveData<MutableList<Project>>
  private lateinit var repositoriesObservable: LiveData<MutableList<Repository>>

  fun attachView(view: MainActivity) {
    this.view = view

    onViewAttached()
  }

  private fun onViewAttached() {
    view?.initUi()
  }

  fun fillData() {
    database.clearData()
    database.fillData()
  }

  fun clearData() {
    database.clearData()
  }

  //region USERS
  private fun printUsers(users: MutableList<User>?) {
    var sb = StringBuilder()
    users?.map {
      sb.append(String.format(
          "(%s) %s: %s\n", it.id, it.name, it.email))
    }


    view?.fillUser(sb.toString())
  }

  fun fetchUsers() {
    usersObservable = database.userDao().findAllUsers()
    usersObservable.observe(lifecycleOwner,
        Observer { printUsers(it) })
  }

  fun stopFetchUsers() {
    usersObservable.removeObservers(view as LifecycleOwner)
  }
  //endregion

  //region PROJECTS
  private fun printProjects(projects: MutableList<Project>?) {
    var sb = StringBuilder()
    projects?.map {
      val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm",
          Locale.US)

      sb.append(String.format(
          "(%s) %s [%s] - %s\n", it.id, it.name, it.technology.toString(),
          simpleDateFormat.format(it.creationDate)))
    }

    view?.fillProjects(sb.toString())
  }

  fun fetchProjects() {
    //findAllProjects()
    //findAllProjectsInRepository("github")
    projectsObservable = database.projectDao().findAllProjectsInRepositoryDomain("%github%")
    projectsObservable.observe(lifecycleOwner,
        Observer { printProjects(it) })

  }

  fun stopFetchProjects() {
    projectsObservable.removeObservers(view as LifecycleOwner)
  }
  //endregion

  //region REPOSITORIES
  private fun printRepositories(repositories: MutableList<Repository>?) {
    var sb = StringBuilder()
    repositories?.map {
      val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm",
          Locale.US)
      sb.append(String.format(
          "(%s) %s [%s] - %s\n", it.id, it.name, it.url, simpleDateFormat.format(it.creationDate)))
    }

    view?.fillRepositories(sb.toString())
  }

  fun fetchRepositories() {
    //findAllRepositories()
    //findAllRepositoriesByUsername("Rui")
    //findAllRepositoriesByUsernamesCollection(mutableListOf("Alberto","Beni", "Alex"))
    repositoriesObservable = database.repositoryDao().findAllRepositoriesByUsernamesCollection(mutableListOf("Alberto","Beni", "Alex"))
    repositoriesObservable.observe(lifecycleOwner,
        Observer { printRepositories(it) })

  }

  fun stopFetchRepositories() {
    repositoriesObservable.removeObservers(view as LifecycleOwner)
  }
  //endregion
}