package com.gigigo.ruialonso.roompersistance.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.gigigo.ruialonso.roompersistance.db.ProjectAndUsers;
import com.gigigo.ruialonso.roompersistance.db.UserAndProject;
import com.gigigo.ruialonso.roompersistance.db.UserAndProjects;
import com.gigigo.ruialonso.roompersistance.db.entities.ProjectsUsersJoin;
import java.util.List;

@Dao public interface ProjectsUsersJoinDao {
  @Query("SELECT user_id, project_id FROM project_user_join INNER JOIN Project ON Project.id = project_user_join.project_id WHERE project_user_join.project_id = :projectId")
  LiveData<List<ProjectsUsersJoin>> findUsersForProjectId(int projectId);

  @Transaction
  @Query("SELECT User.id AS user_id, User.name AS user_name, User.email AS user_email, Project.id AS project_id, Project.name AS project_name, Project.creationDate AS project_creationDate, Project.technology AS project_technology "
      + "FROM project_user_join INNER JOIN User ON User.id = project_user_join.user_id INNER JOIN Project ON Project.id = project_user_join.project_id WHERE project_user_join.user_id = :userId")
  LiveData<List<UserAndProject>> findProjectsForUserId(int userId);

  @Transaction
  @Query("SELECT DISTINCT User.id AS user_id, User.name AS user_name, User.email AS user_email, Project.id AS project_id, Project.name AS project_name, Project.creationDate AS project_creationDate, Project.technology AS project_technology "
      + "FROM project_user_join INNER JOIN User ON User.id = project_user_join.user_id INNER JOIN Project ON Project.id = project_user_join.project_id")
  LiveData<List<UserAndProjects>> findProjectsForUsers();


  @Transaction
  @Query("SELECT DISTINCT User.id AS user_id, User.name AS user_name, User.email AS user_email, Project.id AS project_id, Project.name AS project_name, Project.creationDate AS project_creationDate, Project.technology AS project_technology "
      + "FROM project_user_join INNER JOIN User ON User.id = project_user_join.user_id INNER JOIN Project ON Project.id = project_user_join.project_id")
  LiveData<List<ProjectAndUsers>> findUsersForProjects();

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertProjectsUsersJoin(
      ProjectsUsersJoin projectsUsersJoin);

  @Query("DELETE FROM project_user_join") void deleteAll();
}
