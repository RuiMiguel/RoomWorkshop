package com.gigigo.ruialonso.roompersistance.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "User")
public class UserEntity {
  @PrimaryKey public int id;

  public String name;

  public String email;
}
