package com.gigigo.ruialonso.roompersistance.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import com.gigigo.ruialonso.roompersistance.AppDatabase
import com.gigigo.ruialonso.roompersistance.db.ProjectAndUsers
import com.gigigo.ruialonso.roompersistance.db.RepositoryAndProject
import com.gigigo.ruialonso.roompersistance.db.UserAndProject
import com.gigigo.ruialonso.roompersistance.db.UserAndRepositories
import com.gigigo.ruialonso.roompersistance.db.UserAndProjects
import com.gigigo.ruialonso.roompersistance.db.entities.Project
import com.gigigo.ruialonso.roompersistance.db.entities.ProjectsUsersJoin
import com.gigigo.ruialonso.roompersistance.db.entities.Repository
import com.gigigo.ruialonso.roompersistance.db.entities.RepositoryProjectJoin
import com.gigigo.ruialonso.roompersistance.db.entities.User
import java.text.SimpleDateFormat
import java.util.Locale

class MainPresenter(private val context: Context, private var lifecycleOwner: LifecycleOwner) {

  private var view: MainActivity? = null
  private val database: AppDatabase by lazy { AppDatabase.getInstance(context) }

  private lateinit var usersObservable: LiveData<MutableList<User>>
  private lateinit var projectsObservable: LiveData<MutableList<Project>>
  private lateinit var repositoriesObservable: LiveData<MutableList<Repository>>

  private lateinit var usersAndRepositoriesObservable: LiveData<MutableList<UserAndRepositories>>
  private lateinit var projectAndUsersJoinObservable: LiveData<MutableList<ProjectsUsersJoin>>
  private lateinit var userAndProjectsObservable: LiveData<MutableList<UserAndProjects>>
  private lateinit var projectAndUsersObservable: LiveData<MutableList<ProjectAndUsers>>
  private lateinit var userAndProjectObservable: LiveData<MutableList<UserAndProject>>


  private lateinit var repositoryAndProjectsJoinObservable: LiveData<MutableList<RepositoryProjectJoin>>
  private lateinit var repositoryAndProjectObservable: LiveData<MutableList<RepositoryAndProject>>
  private lateinit var projectsForRepositoryObservable: LiveData<MutableList<Project>>
  private lateinit var repositoriesForProjectObservable: LiveData<MutableList<Repository>>

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
    if (::usersObservable.isInitialized) usersObservable.removeObservers(view as LifecycleOwner)
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
    projectsObservable = database.projectDao().findAllProjects()
    projectsObservable.observe(lifecycleOwner,
        Observer { printProjects(it) })
  }

  fun stopFetchProjects() {
    if (::projectsObservable.isInitialized) projectsObservable.removeObservers(
        view as LifecycleOwner)
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
    repositoriesObservable = database.repositoryDao().findAllRepositoriesByUsernamesCollection(
        mutableListOf("Alberto", "Beni", "Alex"))
    repositoriesObservable.observe(lifecycleOwner,
        Observer { printRepositories(it) })
  }

  fun stopFetchRepositories() {
    if (::repositoriesObservable.isInitialized) repositoriesObservable.removeObservers(
        view as LifecycleOwner)
  }
  //endregion

  //region USER REPOS
  private fun printUsersAndRepositories(users: MutableList<UserAndRepositories>?) {
    var sb = StringBuilder()
    users?.map {
      sb.append(String.format(
          "(%s) %s: %s\n", it.user.id, it.user.name, it.repositories.map { it.name }))
    }


    view?.fillUserRepositories(sb.toString())
  }

  fun fetchUserRepositories() {
    usersAndRepositoriesObservable = database.userDao().findAllRepositoriesByUsername("Rui")
    usersAndRepositoriesObservable.observe(lifecycleOwner,
        Observer { printUsersAndRepositories(it) })
  }

  fun stopFetchUserRepositories() {
    if (::usersAndRepositoriesObservable.isInitialized) usersAndRepositoriesObservable.removeObservers(
        view as LifecycleOwner)
  }
  //endregion

  //region PROJECTS USERS
  private fun printProjectsUsersJoin(projects: MutableList<ProjectsUsersJoin>?) {
    var sb = StringBuilder()
    projects?.map {
      sb.append(String.format(
          "(%s - %s)\n", it.userId, it.projectId))
    }

    view?.fillProjectsUsers(sb.toString())
  }

  private fun printUserAndProject(projects: MutableList<UserAndProject>?) {
    var sb = StringBuilder()
    projects?.map {
      sb.append(String.format(
          "%s [%s]\n", it.user.name, it.project.name))
    }

    view?.fillProjectsUsers(sb.toString())
  }

  private fun printUserAndProjects(projects: MutableList<UserAndProjects>?) {
    var sb = StringBuilder()
    projects?.map {
      sb.append(String.format(
          "(%s) %s - %s\n", it.user.id, it.user.name, it.projects.map { it.projectId } ))
    }

    view?.fillProjectsUsers(sb.toString())
  }

  private fun printProjectAndUsers(projects: MutableList<ProjectAndUsers>?) {
    var sb = StringBuilder()
    projects?.map {
      sb.append(String.format(
          "(%s) %s - %s\n", it.project.id, it.project.name, it.users.map { it.userId } ))
    }

    view?.fillProjectsUsers(sb.toString())
  }

  fun fetchProjectsUsers() {
    /*
    projectAndUsersJoinObservable = database.projectsUsersJoinDao().findUsersForProjectId(5)
    projectAndUsersJoinObservable.observe(lifecycleOwner, Observer { printProjectsUsersJoin(it) })
    */


    userAndProjectObservable = database.projectsUsersJoinDao().findProjectsForUserId(1)
    userAndProjectObservable.observe(lifecycleOwner, Observer { printUserAndProject(it) })


    /*
    userAndProjectsObservable = database.projectsUsersJoinDao().findProjectsForUsers()
    userAndProjectsObservable.observe(lifecycleOwner, Observer { printUserAndProjects(it) })
    */

    /*
    projectAndUsersObservable = database.projectsUsersJoinDao().findUsersForProjects()
    projectAndUsersObservable.observe(lifecycleOwner, Observer { printProjectAndUsers(it) })
    */
  }

  fun stopFetchProjectsUsers() {
    if (::projectAndUsersJoinObservable.isInitialized) projectAndUsersJoinObservable.removeObservers(view as LifecycleOwner)
    if (::userAndProjectsObservable.isInitialized) userAndProjectsObservable.removeObservers(view as LifecycleOwner)
    if (::userAndProjectsObservable.isInitialized) userAndProjectsObservable.removeObservers(view as LifecycleOwner)
    if (::projectAndUsersObservable.isInitialized) projectAndUsersObservable.removeObservers(view as LifecycleOwner)
  }
  //endregion

  //region REPOSITORY PROJECTS
  private fun printRepositoriesAndProjectsJoin(repositories: MutableList<RepositoryProjectJoin>?) {
    var sb = StringBuilder()
    repositories?.map {
      sb.append(String.format(
          "(%s - %s)\n", it.projectId, it.repoId))
    }

    view?.fillRepositoryProjects(sb.toString())
  }

  private fun printRepositoriesAndProjects(repositories: MutableList<RepositoryAndProject>?) {
    var sb = StringBuilder()
    repositories?.map {
      sb.append(String.format(
          "(%s - %s)\n", it.project.name, it.repository.name))
    }

    view?.fillRepositoryProjects(sb.toString())
  }

  private fun printProjectsForRepository(projects: MutableList<Project>?) {
    var sb = StringBuilder()
    projects?.map {
      sb.append(String.format(
          "(%s) %s\n", it.id, it.name))
    }

    view?.fillRepositoryProjects(sb.toString())
  }

  private fun printRepositoriesForProjects(repositories: MutableList<Repository>?) {
    var sb = StringBuilder()
    repositories?.map {
      sb.append(String.format(
          "(%s) %s\n", it.id, it.name))
    }

    view?.fillRepositoryProjects(sb.toString())
  }

  fun fetchRepositoryProjects() {
    /*
    repositoriesForProjectObservable = database.repositoryDao().findRepositoriesForProjectId(5)
    repositoriesForProjectObservable.observe(lifecycleOwner,
        Observer { printRepositoriesForProjects(it) })
    */
    /*
    projectsForRepositoryObservable = database.projectDao().findProjectsForRepositoryId(1)
    projectsForRepositoryObservable.observe(lifecycleOwner,
        Observer { printProjectsForRepository(it) })
    */
    /*
    repositoryAndProjectsJoinObservable = database.repositoryProjectJoinDao().findAllProjectsRepositoriesByRepositoryName("%github%")
    repositoryAndProjectsJoinObservable.observe(lifecycleOwner,
        Observer { printRepositoriesAndProjectsJoin(it) })
    */

    repositoryAndProjectObservable = database.repositoryProjectJoinDao().findAllProjectAndRepositoryByRepositoryName("%github%")
    repositoryAndProjectObservable.observe(lifecycleOwner,
        Observer { printRepositoriesAndProjects(it) })

  }

  fun stopFetchRepositoryProjects() {
    if (::repositoriesForProjectObservable.isInitialized) repositoriesForProjectObservable.removeObservers(view as LifecycleOwner)
    if (::projectsForRepositoryObservable.isInitialized) projectsForRepositoryObservable.removeObservers(view as LifecycleOwner)
    if (::repositoryAndProjectsJoinObservable.isInitialized) repositoryAndProjectsJoinObservable.removeObservers(view as LifecycleOwner)
    if (::repositoryAndProjectObservable.isInitialized) repositoryAndProjectObservable.removeObservers(view as LifecycleOwner)
  }
  //endregion
}