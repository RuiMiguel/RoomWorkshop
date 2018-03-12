package com.gigigo.ruialonso.roompersistance.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity()
public class User {
  @PrimaryKey public int id;

  public String name;

  public String email;
}
