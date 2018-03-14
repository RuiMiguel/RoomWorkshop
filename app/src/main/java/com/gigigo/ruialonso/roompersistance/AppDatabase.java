package com.gigigo.ruialonso.roompersistance;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import com.gigigo.ruialonso.roompersistance.db.dao.ProjectDao;
import com.gigigo.ruialonso.roompersistance.db.dao.ProjectsUsersJoinDao;
import com.gigigo.ruialonso.roompersistance.db.dao.RepositoryDao;
import com.gigigo.ruialonso.roompersistance.db.dao.RepositoryProjectJoinDao;
import com.gigigo.ruialonso.roompersistance.db.dao.UserDao;
import com.gigigo.ruialonso.roompersistance.db.entities.Project;
import com.gigigo.ruialonso.roompersistance.db.entities.ProjectsUsersJoin;
import com.gigigo.ruialonso.roompersistance.db.entities.Repository;
import com.gigigo.ruialonso.roompersistance.db.entities.RepositoryProjectJoin;
import com.gigigo.ruialonso.roompersistance.db.entities.User;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Database(entities = { User.class, Project.class, Repository.class, RepositoryProjectJoin.class, ProjectsUsersJoin.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

  public static final String DATABASE_NAME = "basic-sample-db";

  private static AppDatabase INSTANCE;

  private static AppExecutors executors = new AppExecutors();

  public abstract UserDao userDao();

  public abstract ProjectDao projectDao();

  public abstract RepositoryDao repositoryDao();

  public abstract RepositoryProjectJoinDao repositoryProjectJoinDao();

  public abstract ProjectsUsersJoinDao projectsUsersJoinDao();

  private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

  public static AppDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = buildDatabase(context.getApplicationContext());
      INSTANCE.updateDatabaseCreated(context.getApplicationContext());
    }
    return INSTANCE;
  }

  private static AppDatabase buildDatabase(final Context appContext) {
    return Room
        //.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
        .databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
        //.addMigrations(MIGRATION_1_2)
        //.fallbackToDestructiveMigration()
        .addCallback(new Callback() {
          @Override public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            executors.execute(AppExecutors.DISK, new Function0<Unit>() {
              @Override public Unit invoke() {
                // Generate the data for pre-population
                AppDatabase database = AppDatabase.getInstance(appContext);

                DatabaseInitializer.populateData(database);

                // notify that the database was created and it's ready to be used
                database.setDatabaseCreated();

                return null;
              }
            });
          }
        }).build();
  }

  static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override public void migrate(SupportSQLiteDatabase database) {
      /*
      // SQLite supports a limited operations for ALTER.
      // Changing the type of a column is not directly supported, so this is what we need
      // to do:

      // Create the new table
      database.execSQL(
          "CREATE TABLE Repository_new (id INTEGER NOT NULL,"
              + "name TEXT,"
              + "url TEXT,"
              + "user_id INTEGER,"
              + "creationDate INTEGER,"
              + "PRIMARY KEY(id),"
              + "FOREIGN KEY(user_id) REFERENCES User(id) ON DELETE CASCADE)");

      // Copy the data
      database.execSQL("INSERT INTO Repository_new (id, name, url, user_id, creationDate) "
          + "SELECT id, name, url, user_id, creationDate "
          + "FROM Repository");

      // Remove the old table
      database.execSQL("DROP TABLE Repository");

      // Change the table name to the correct one
      database.execSQL("ALTER TABLE Repository_new RENAME TO Repository");
      */
    }
  };

  private void updateDatabaseCreated(final Context context) {
    if (context.getDatabasePath(DATABASE_NAME).exists()) {
      setDatabaseCreated();
    }
  }

  private void setDatabaseCreated() {
    isDatabaseCreated.postValue(true);
  }

  public LiveData<Boolean> getDatabaseCreated() {
    return isDatabaseCreated;
  }

  public void fillData() {
    executors.execute(AppExecutors.DISK, new Function0<Unit>() {
      @Override public Unit invoke() {
        DatabaseInitializer.clearData(INSTANCE);
        DatabaseInitializer.populateData(INSTANCE);
        return null;
      }
    });
  }

  public void clearData() {
    executors.execute(AppExecutors.DISK, new Function0<Unit>() {
      @Override public Unit invoke() {
        DatabaseInitializer.clearData(INSTANCE);
        return null;
      }
    });
  }
}
