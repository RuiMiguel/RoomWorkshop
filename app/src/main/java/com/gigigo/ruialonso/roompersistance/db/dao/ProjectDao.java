package com.gigigo.ruialonso.roompersistance.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.TypeConverters;
import com.gigigo.ruialonso.roompersistance.db.UserAndProjects;
import com.gigigo.ruialonso.roompersistance.db.entities.Project;
import com.gigigo.ruialonso.roompersistance.utils.DateConverter;
import java.util.List;

@Dao @TypeConverters(DateConverter.class) public interface ProjectDao {

  @Transaction
  @Query("SELECT * FROM Project") LiveData<List<Project>> findAllProjects();

  @Query("SELECT Project.id, Project.name, Project.creationDate, Project.technology FROM Project INNER JOIN repo_project_join ON Project.id = project_id WHERE repo_id = :repositoryId")
  LiveData<List<Project>> findProjectsForRepositoryId(int repositoryId);

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertProject(Project project);

  @Query("DELETE FROM Project") void deleteAll();
}
