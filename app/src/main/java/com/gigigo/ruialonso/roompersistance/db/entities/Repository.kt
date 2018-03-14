package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.gigigo.ruialonso.roompersistance.utils.DateConverter
import java.util.Date

@Entity(tableName = "Repository")
@TypeConverters(DateConverter::class)
class Repository(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var name: String?,

    var url: String?,

    @ColumnInfo(name = "user_id")
    var userId: Int,

    var creationDate: Date?
) {
  constructor() : this(0, null, null, 0, null)
}