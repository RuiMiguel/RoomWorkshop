package com.gigigo.ruialonso.roompersistance.db

import android.arch.persistence.room.Embedded
import com.gigigo.ruialonso.roompersistance.db.entities.Project
import com.gigigo.ruialonso.roompersistance.db.entities.Repository

class RepositoryAndProject {
  @Embedded
  var repository: Repository = Repository()

  @Embedded(prefix = "project_")
  var project: Project = Project()
}
