package com.gigigo.ruialonso.roompersistance.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import com.gigigo.ruialonso.roompersistance.utils.DateConverter;
import java.util.List;

@Dao @TypeConverters(DateConverter.class) public interface RepositoryDao {
  @Query("SELECT * FROM Repository")
  LiveData<List<Repository>> findAllRepositories();

  @Query("SELECT Repository.* FROM Repository INNER JOIN User ON Repository.user_id = User.id WHERE User.name = :username")
  LiveData<List<Repository>> findAllRepositoriesByUsername(String username);

  @Query("SELECT Repository.* FROM Repository INNER JOIN User ON Repository.user_id = User.id WHERE User.name IN (:usernames)")
  LiveData<List<Repository>> findAllRepositoriesByUsernamesCollection(List<String> usernames);

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertRepository(Repository repository);

  @Query("DELETE FROM Repository") void deleteAll();
}
