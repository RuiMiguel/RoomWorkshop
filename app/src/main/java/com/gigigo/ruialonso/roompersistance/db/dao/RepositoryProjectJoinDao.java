package com.gigigo.ruialonso.roompersistance.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import com.gigigo.ruialonso.roompersistance.db.RepositoryAndProject;
import com.gigigo.ruialonso.roompersistance.db.entities.RepositoryProjectJoin;
import com.gigigo.ruialonso.roompersistance.utils.DateConverter;
import java.util.List;

@Dao @TypeConverters(DateConverter.class) public interface RepositoryProjectJoinDao {
  @Query("SELECT repo_id, project_id FROM repo_project_join INNER JOIN Repository ON repo_id = Repository.id WHERE Repository.name LIKE :repositoryName")
  LiveData<List<RepositoryProjectJoin>> findAllProjectsRepositoriesByRepositoryName(
      String repositoryName);

  @Query("SELECT Repository.id, Repository.name, Repository.url, Repository.user_id, Repository.creationDate, Project.id AS project_id, Project.name AS project_name, Project.creationDate AS project_creationDate, Project.technology AS project_technology"
      + " FROM repo_project_join INNER JOIN Repository ON repo_id = Repository.id INNER JOIN Project ON project_id = Project.id WHERE Repository.name LIKE :repositoryName")
  LiveData<List<RepositoryAndProject>> findAllProjectAndRepositoryByRepositoryName(
      String repositoryName);

  @Insert(onConflict = OnConflictStrategy.IGNORE) void insertRepositoryProject(
      RepositoryProjectJoin repositoryProjectJoin);

  @Query("DELETE FROM repo_project_join") void deleteAll();
}
