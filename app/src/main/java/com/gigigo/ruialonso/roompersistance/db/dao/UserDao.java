package com.gigigo.ruialonso.roompersistance.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.gigigo.ruialonso.roompersistance.db.entities.UserEntity;
import com.gigigo.ruialonso.roompersistance.models.UserAndAllRepositories;
import java.util.List;

@Dao public interface UserDao {

  @Transaction @Query("SELECT * FROM User") LiveData<List<UserEntity>> findAllUsers();

  @Query("SELECT * FROM User WHERE name = :username")
  LiveData<List<UserAndAllRepositories>> findAllRepositoriesByUsername(String username);

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertUser(UserEntity user);

  @Query("DELETE FROM User") void deleteAll();
}
