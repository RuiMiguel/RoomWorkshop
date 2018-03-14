package com.gigigo.ruialonso.roompersistance.db;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import com.gigigo.ruialonso.roompersistance.db.entities.Repository;
import com.gigigo.ruialonso.roompersistance.db.entities.User;
import java.util.List;

public class UserAndRepositories {
  @Embedded public User user;

  @Relation(parentColumn = "id", entityColumn = "user_id", entity = Repository.class) public List<Repository>
      repositories;
}
