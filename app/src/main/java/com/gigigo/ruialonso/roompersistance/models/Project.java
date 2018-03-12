package com.gigigo.ruialonso.roompersistance.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import com.gigigo.ruialonso.roompersistance.utils.DateConverter;
import com.gigigo.ruialonso.roompersistance.utils.TechnologyConverter;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@Entity(
    foreignKeys = {
      @ForeignKey(entity = Repository.class, parentColumns = "id", childColumns = "repo_id")
    },
    indices = { @Index(value = "repo_id") })
@TypeConverters({ DateConverter.class, TechnologyConverter.class })
public class Project {
  @PrimaryKey(autoGenerate = true) @NonNull public int id;

  public String name;

  @ColumnInfo(name = "repo_id") @NotNull public int repoId;

  public Date creationDate;

  public Technology technology;
}
