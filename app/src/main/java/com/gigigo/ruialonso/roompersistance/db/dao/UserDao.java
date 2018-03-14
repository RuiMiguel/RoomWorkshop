package com.gigigo.ruialonso.roompersistance.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.gigigo.ruialonso.roompersistance.db.UserAndRepositories;
import com.gigigo.ruialonso.roompersistance.db.entities.User;
import java.util.List;

@Dao public interface UserDao {

  @Transaction @Query("SELECT * FROM User") LiveData<List<User>> findAllUsers();

  @Transaction
  @Query("SELECT * FROM User WHERE name = :username")
  LiveData<List<UserAndRepositories>> findAllRepositoriesByUsername(String username);

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertUser(User user);

  @Query("DELETE FROM User") void deleteAll();
}
