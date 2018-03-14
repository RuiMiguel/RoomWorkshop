package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.gigigo.ruialonso.roompersistance.db.Technology
import com.gigigo.ruialonso.roompersistance.utils.DateConverter
import com.gigigo.ruialonso.roompersistance.utils.TechnologyConverter
import java.util.Date

@Entity(tableName = "Project")
@TypeConverters(DateConverter::class, TechnologyConverter::class)
class Project(
    @PrimaryKey
    var id: Int,

    var name: String?,

    var creationDate: Date?,

    var technology: Technology?
) {
  constructor() : this(0, null, null, null)
}