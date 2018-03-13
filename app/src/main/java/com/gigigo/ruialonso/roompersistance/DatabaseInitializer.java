package com.gigigo.ruialonso.roompersistance;

import android.util.Log;
import com.gigigo.ruialonso.roompersistance.db.entities.ProjectEntity;
import com.gigigo.ruialonso.roompersistance.db.entities.RepositoryEntity;
import com.gigigo.ruialonso.roompersistance.models.Technology;
import com.gigigo.ruialonso.roompersistance.db.entities.UserEntity;
import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

  private static UserEntity addUser(final AppDatabase db, int id, String name, String email) {
    UserEntity user = new UserEntity();
    user.id = id;
    user.name = name;
    user.email = email;
    db.userDao().insertUser(user);
    Log.d("DATABASE", "inserted user " + name);
    return user;
  }

  private static ProjectEntity addProject(final AppDatabase db, String name, int repoId, Date creationDate,
      Technology technology) {
    ProjectEntity project = new ProjectEntity();
    project.name = name;
    project.repoId = repoId;
    project.creationDate = creationDate;
    project.technology = technology;

    db.projectDao().insertProject(project);
    Log.d("DATABASE", "inserted project " + name);
    return project;
  }

  private static RepositoryEntity addRepository(final AppDatabase db, String name, String url, int userId,
      Date creationDate) {
    RepositoryEntity repository = new RepositoryEntity();
    repository.name = name;
    repository.url = url;
    repository.userId = userId;
    repository.creationDate = creationDate;

    db.repositoryDao().insertRepository(repository);
    Log.d("DATABASE", "inserted repo " + name);
    return repository;
  }

  public static void clearData(final AppDatabase db) {
    db.userDao().deleteAll();
    db.projectDao().deleteAll();
    db.repositoryDao().deleteAll();
  }

  public static void populateData(AppDatabase db) {
    UserEntity rui = addUser(db, 1, "Rui", "rui@gigigo.com");
    UserEntity manu = addUser(db, 2, "Manu", "manu@gigigo.com");
    UserEntity beni = addUser(db, 3, "Beni", "beni@gigigo.com");
    UserEntity alberto = addUser(db, 4, "Alberto", "alberto@gigigo.com");
    UserEntity santi = addUser(db, 5, "Santi", "santi@gigigo.com");
    UserEntity alex = addUser(db, 6, "Alex", "alex@gigigo.com");

    Date date = getTodayPlusDays(0);

    RepositoryEntity ruiGithub = addRepository(db, "Rui Github", "github.com", rui.id, date);
    RepositoryEntity ruiBitbucket = addRepository(db, "Rui Bitbucket", "bitbucket.org", rui.id, date);
    RepositoryEntity manuGithub = addRepository(db, "Manu Github", "github.com", manu.id, date);
    RepositoryEntity manuBitbucket = addRepository(db, "Manu Bitbucket", "bitbucket.org", manu.id, date);
    RepositoryEntity beniGithub = addRepository(db, "Beni Github", "github.com", beni.id, date);
    RepositoryEntity beniBitbucket = addRepository(db, "Beni Bitbucket", "bitbucket.org", beni.id, date);
    RepositoryEntity albertoGithub = addRepository(db, "Alberto Github", "github.com", alberto.id, date);
    RepositoryEntity santiBitbucket = addRepository(db, "Santi Bitbucket", "bitbucket.org", santi.id, date);
    RepositoryEntity alexBitbucket = addRepository(db, "Alex Bitbucket", "bitbucket.org", alex.id, date);

    ProjectEntity woah = addProject(db, "WOAH", 1, date, Technology.Android);
    ProjectEntity vips = addProject(db, "Vips", 3, date, Technology.iOS);
    ProjectEntity destapp = addProject(db, "Destapp", 2, date, Technology.WindowsPhone);
    ProjectEntity mcdonald = addProject(db, "McDonalds", 2, date, Technology.Android);
    ProjectEntity orchextra = addProject(db, "Orchextra", 1, date, Technology.iOS);
    ProjectEntity nubico = addProject(db, "Nubico", 4, date, Technology.Blackberry10);
    ProjectEntity ferring = addProject(db, "Ferring", 5, date, Technology.Android);
    ProjectEntity forme = addProject(db, "Forme", 6, date, Technology.Android);
  }

  private static Date getTodayPlusDays(int daysAgo) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, daysAgo);
    return calendar.getTime();
  }
}
