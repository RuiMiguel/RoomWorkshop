package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.gigigo.ruialonso.roompersistance.utils.DateConverter
import java.util.Date

@Entity(tableName = "Repository")
@TypeConverters(DateConverter::class)
class RepositoryEntity {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0

  var name: String? = null

  var url: String? = null

  @ColumnInfo(name = "user_id")
  var userId: Int = 0

  var creationDate: Date? = null
}