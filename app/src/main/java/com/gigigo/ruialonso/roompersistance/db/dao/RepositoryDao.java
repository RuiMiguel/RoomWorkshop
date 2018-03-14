package com.gigigo.ruialonso.roompersistance.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.TypeConverters;
import com.gigigo.ruialonso.roompersistance.db.entities.Repository;
import com.gigigo.ruialonso.roompersistance.utils.DateConverter;
import java.util.List;

@Dao @TypeConverters(DateConverter.class) public interface RepositoryDao {
  @Transaction @Query("SELECT * FROM Repository") LiveData<List<Repository>> findAllRepositories();

  @Query("SELECT Repository.* FROM Repository INNER JOIN User ON Repository.user_id = User.id WHERE User.name IN (:usernames)")
  LiveData<List<Repository>> findAllRepositoriesByUsernamesCollection(List<String> usernames);

  @Query("SELECT Repository.id, Repository.name, Repository.url, Repository.user_id, Repository.creationDate FROM Repository INNER JOIN repo_project_join ON Repository.id = repo_id WHERE project_id = :projectId")
  LiveData<List<Repository>> findRepositoriesForProjectId(int projectId);

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertRepository(Repository repository);

  @Query("DELETE FROM Repository") void deleteAll();
}
