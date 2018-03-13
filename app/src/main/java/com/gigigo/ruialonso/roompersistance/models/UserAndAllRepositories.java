package com.gigigo.ruialonso.roompersistance.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import com.gigigo.ruialonso.roompersistance.db.entities.RepositoryEntity;
import com.gigigo.ruialonso.roompersistance.db.entities.UserEntity;
import java.util.List;

/**
 * Created by rui.alonso on 13/03/2018.
 */

public class UserAndAllRepositories {
  @Embedded public UserEntity user;

  @Relation(parentColumn = "id", entityColumn = "user_id") public List<RepositoryEntity>
      repositories;
}
