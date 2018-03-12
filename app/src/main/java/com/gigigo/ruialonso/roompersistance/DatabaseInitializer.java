package com.gigigo.ruialonso.roompersistance;

import android.util.Log;
import com.gigigo.ruialonso.roompersistance.models.Project;
import com.gigigo.ruialonso.roompersistance.models.Repository;
import com.gigigo.ruialonso.roompersistance.models.Technology;
import com.gigigo.ruialonso.roompersistance.models.User;
import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

  private static User addUser(final AppDatabase db, int id, String name, String email) {
    User user = new User();
    user.id = id;
    user.name = name;
    user.email = email;
    db.userDao().insertUser(user);
    Log.d("DATABASE", "inserted user " + name);
    return user;
  }

  private static Project addProject(final AppDatabase db, String name, int repoId, Date creationDate,
      Technology technology) {
    Project project = new Project();
    project.name = name;
    project.repoId = repoId;
    project.creationDate = creationDate;
    project.technology = technology;

    db.projectDao().insertProject(project);
    Log.d("DATABASE", "inserted project " + name);
    return project;
  }

  private static Repository addRepository(final AppDatabase db, String name, String url, int userId,
      Date creationDate) {
    Repository repository = new Repository();
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
    User rui = addUser(db, 1, "Rui", "rui@gigigo.com");
    User manu = addUser(db, 2, "Manu", "manu@gigigo.com");
    User beni = addUser(db, 3, "Beni", "beni@gigigo.com");
    User alberto = addUser(db, 4, "Alberto", "alberto@gigigo.com");
    User santi = addUser(db, 5, "Santi", "santi@gigigo.com");
    User alex = addUser(db, 6, "Alex", "alex@gigigo.com");

    Date date = getTodayPlusDays(0);

    Repository ruiGithub = addRepository(db, "Rui Github", "github.com", rui.id, date);
    Repository ruiBitbucket = addRepository(db, "Rui Bitbucket", "bitbucket.org", rui.id, date);

    Repository manuGithub = addRepository(db, "Manu Github", "github.com", manu.id, date);
    Repository manuBitbucket = addRepository(db, "Manu Bitbucket", "bitbucket.org", manu.id, date);

    Repository beniGithub = addRepository(db, "Beni Github", "github.com", beni.id, date);
    Repository beniBitbucket = addRepository(db, "Beni Bitbucket", "bitbucket.org", beni.id, date);

    Repository albertoGithub = addRepository(db, "Alberto Github", "github.com", alberto.id, date);

    Repository santiBitbucket =
        addRepository(db, "Santi Bitbucket", "bitbucket.org", santi.id, date);

    Repository alexBitbucket = addRepository(db, "Alex Bitbucket", "bitbucket.org", alex.id, date);


    Project woah = addProject(db, "WOAH", 1, date, Technology.Android);
    Project vips = addProject(db, "Vips", 3, date, Technology.iOS);
    Project destapp = addProject(db, "Destapp", 2, date, Technology.WindowsPhone);
    Project mcdonald = addProject(db, "McDonalds", 2, date, Technology.Android);
    Project orchextra = addProject(db, "Orchextra", 1, date, Technology.iOS);
    Project nubico = addProject(db, "Nubico", 4, date, Technology.Blackberry10);
    Project ferring = addProject(db, "Ferring", 5, date, Technology.Android);
    Project forme = addProject(db, "Forme", 6, date, Technology.Android);
  }

  private static Date getTodayPlusDays(int daysAgo) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, daysAgo);
    return calendar.getTime();
  }
}
