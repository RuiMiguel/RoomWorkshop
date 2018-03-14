package com.gigigo.ruialonso.roompersistance.db

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.gigigo.ruialonso.roompersistance.db.entities.Project
import com.gigigo.ruialonso.roompersistance.db.entities.ProjectsUsersJoin
import com.gigigo.ruialonso.roompersistance.db.entities.User

class UserAndProjects {
  @Embedded(prefix = "user_")
  var user: User = User()

  @Relation(parentColumn = "user_id", entityColumn = "user_id")
  var projects: List<ProjectsUsersJoin> = emptyList()
}
