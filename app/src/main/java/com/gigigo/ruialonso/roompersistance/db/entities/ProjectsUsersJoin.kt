package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(tableName = "project_user_join",
    indices = [(Index("user_id")), (Index("project_id"))],
    primaryKeys = ["user_id", "project_id"])
class ProjectsUsersJoin(
    @ColumnInfo(name = "user_id")
    var userId: Int,

    @ColumnInfo(name = "project_id")
    var projectId: Int
) {
  constructor() : this(0, 0)
}