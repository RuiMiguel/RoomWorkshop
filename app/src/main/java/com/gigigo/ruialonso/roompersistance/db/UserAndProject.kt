package com.gigigo.ruialonso.roompersistance.db

import android.arch.persistence.room.Embedded
import com.gigigo.ruialonso.roompersistance.db.entities.Project
import com.gigigo.ruialonso.roompersistance.db.entities.User

class UserAndProject {
  @Embedded(prefix = "user_")
  var user: User = User()

  @Embedded(prefix = "project_")
  var project: Project = Project()
}
