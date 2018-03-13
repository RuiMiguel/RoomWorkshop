package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.gigigo.ruialonso.roompersistance.models.Technology
import com.gigigo.ruialonso.roompersistance.utils.DateConverter
import com.gigigo.ruialonso.roompersistance.utils.TechnologyConverter
import java.util.Date

@Entity(
    tableName = "Project",
    foreignKeys = arrayOf(ForeignKey(
        entity = RepositoryEntity::class, parentColumns = arrayOf("id"),
        childColumns = arrayOf("repo_id"))),
    indices = arrayOf(Index(value = "repo_id")))
@TypeConverters(DateConverter::class, TechnologyConverter::class)
class ProjectEntity {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0

  var name: String? = null

  @ColumnInfo(name = "repo_id")
  var repoId: Int = 0

  var creationDate: Date? = null

  var technology: Technology? = null
}