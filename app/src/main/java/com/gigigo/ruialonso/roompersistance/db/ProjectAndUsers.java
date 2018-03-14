package com.gigigo.ruialonso.roompersistance.db;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import com.gigigo.ruialonso.roompersistance.db.entities.Project;
import com.gigigo.ruialonso.roompersistance.db.entities.ProjectsUsersJoin;
import java.util.ArrayList;
import java.util.List;

public class ProjectAndUsers {
  @Embedded(prefix = "project_") public Project project;

  @Relation(parentColumn = "project_id", entityColumn = "project_id")
  public List<ProjectsUsersJoin> users;
}