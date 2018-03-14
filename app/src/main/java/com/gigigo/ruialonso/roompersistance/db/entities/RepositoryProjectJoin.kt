package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

@Entity(
    tableName = "repo_project_join",
    foreignKeys = arrayOf(
        ForeignKey(entity = Repository::class, parentColumns = arrayOf("id"),
            childColumns = arrayOf("repo_id")),
        ForeignKey(entity = Project::class, parentColumns = arrayOf("id"),
            childColumns = arrayOf("project_id"))),
    primaryKeys = arrayOf("repo_id", "project_id"))

class RepositoryProjectJoin(
    @ColumnInfo(name = "repo_id")
    var repoId: Int,

    @ColumnInfo(name = "project_id")
    var projectId: Int
) {
  constructor() : this(0, 0)
}