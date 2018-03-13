package com.gigigo.ruialonso.roompersistance.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "User")
class UserEntity {
  @PrimaryKey
  var id: Int = 0

  var name: String? = null

  var email: String? = null
}
