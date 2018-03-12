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

  private val clearButton: Button by lazy { findViewById<Button>(R.id.clear_button) }
  private val fillButton: Button by lazy { findViewById<Button>(R.id.fill_button) }

  private val fetchUsersButton: Button by lazy { findViewById<Button>(R.id.fetch_users_button) }
  private val stopFetchUsersButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_users_button) }
  private val fetchProjectsButton: Button by lazy { findViewById<Button>(R.id.fetch_projects_button) }
  private val stopFetchProjectsButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_projects_button) }
  private val fetchRepositoriesButton: Button by lazy { findViewById<Button>(R.id.fetch_repositories_button) }
  private val stopFetchRepositoriesButton: Button by lazy { findViewById<Button>(R.id.stop_fetch_repositories_button) }

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
}
