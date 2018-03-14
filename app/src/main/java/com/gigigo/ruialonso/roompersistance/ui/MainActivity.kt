package com.gigigo.ruialonso.roompersistance.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.gigigo.ruialonso.roompersistance.R

class MainActivity : AppCompatActivity() {
  private val usersText: TextView by lazy { findViewById<TextView>(R.id.users_text) }
  private val projectsText: TextView by lazy { findViewById<TextView>(R.id.projects_text) }
  private val repositoriesText: TextView by lazy { findViewById<TextView>(R.id.repositories_text) }
  private val userRepositoriesText: TextView by lazy { findViewById<TextView>(R.id.user_repositories_text) }
  private val projectsUsersText: TextView by lazy { findViewById<TextView>(R.id.projects_users_text) }
  private val repositoryProjectsText: TextView by lazy { findViewById<TextView>(R.id.repository_projects_text) }

  private val clearButton: Button by lazy { findViewById<Button>(R.id.clear_button) }
  private val fillButton: Button by lazy { findViewById<Button>(R.id.fill_button) }

  private val fetchUsersButton: Button by lazy { findViewById<Button>(R.id.fetch_users_button) }
  private val stopFetchUsersButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_users_button) }
  private val fetchProjectsButton: Button by lazy { findViewById<Button>(R.id.fetch_projects_button) }
  private val stopFetchProjectsButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_projects_button) }
  private val fetchRepositoriesButton: Button by lazy { findViewById<Button>(R.id.fetch_repositories_button) }
  private val stopFetchRepositoriesButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_repositories_button) }
  private val fetchUserRepositoriesButton: Button by lazy { findViewById<Button>(R.id.fetch_user_repositories_button) }
  private val stopFetchUserRepositoriesButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_user_repositories_button) }
  private val fetchProjectsUsersButton: Button by lazy { findViewById<Button>(R.id.fetch_projects_users_button) }
  private val stopFetchProjectsUsersButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_projects_users_button) }
  private val fetchRepositoryProjectsButton: Button by lazy { findViewById<Button>(R.id.fetch_repository_projects_button) }
  private val stopFetchRepositoryProjectsButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_repository_projects_button) }

  private val presenter: MainPresenter by lazy {
    MainPresenter(applicationContext, this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    presenter.attachView(this)
  }

  fun initUi() {
    clearButton.setOnClickListener { presenter.clearData() }
    fillButton.setOnClickListener { presenter.fillData() }

    fetchUsersButton.setOnClickListener { presenter.fetchUsers() }
    stopFetchUsersButton.setOnClickListener { presenter.stopFetchUsers() }

    fetchProjectsButton.setOnClickListener { presenter.fetchProjects() }
    stopFetchProjectsButton.setOnClickListener { presenter.stopFetchProjects() }

    fetchRepositoriesButton.setOnClickListener { presenter.fetchRepositories() }
    stopFetchRepositoriesButton.setOnClickListener { presenter.stopFetchRepositories() }

    fetchUserRepositoriesButton.setOnClickListener { presenter.fetchUserRepositories() }
    stopFetchUserRepositoriesButton.setOnClickListener { presenter.stopFetchUserRepositories() }

    fetchProjectsUsersButton.setOnClickListener { presenter.fetchProjectsUsers() }
    stopFetchProjectsUsersButton.setOnClickListener { presenter.stopFetchProjectsUsers() }

    fetchRepositoryProjectsButton.setOnClickListener { presenter.fetchRepositoryProjects() }
    stopFetchRepositoryProjectsButton.setOnClickListener { presenter.stopFetchRepositoryProjects() }
  }

  fun fillUser(users: String) {
    usersText.text = users
  }

  fun fillProjects(projects: String) {
    projectsText.text = projects
  }
  fun fillRepositories(repositories: String) {
    repositoriesText.text = repositories
  }

  fun fillUserRepositories(userRepositories: String) {
    userRepositoriesText.text = userRepositories
  }

  fun fillProjectsUsers(projectsUsers: String) {
    projectsUsersText.text = projectsUsers
  }
  fun fillRepositoryProjects(repositoryProjects: String) {
    repositoryProjectsText.text = repositoryProjects
  }
}
