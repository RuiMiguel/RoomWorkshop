package com.gigigo.ruialonso.roompersistance.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import com.gigigo.ruialonso.roompersistance.utils.DateConverter;
import java.util.List;

@Dao @TypeConverters(DateConverter.class) public interface ProjectDao {

  @Query("SELECT * FROM Project") LiveData<List<Project>> findAllProjects();

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertProject(Project project);

  @Query("DELETE FROM Project") void deleteAll();
}
