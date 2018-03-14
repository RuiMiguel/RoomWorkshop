package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "User")
class User(
    @PrimaryKey
    var id: Int,

    var name: String?,

    var email: String?
) {
  constructor() : this(0, null, null)
}
