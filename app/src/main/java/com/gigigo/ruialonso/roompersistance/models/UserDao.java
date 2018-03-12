package com.gigigo.ruialonso.roompersistance.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao public interface UserDao {
  @Query("SELECT * FROM User") LiveData<List<User>> findAllUsers();

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertUser(User user);

  @Query("DELETE FROM User") void deleteAll();
}
