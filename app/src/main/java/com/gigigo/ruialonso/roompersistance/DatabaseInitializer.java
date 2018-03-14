package com.gigigo.ruialonso.roompersistance;

import android.util.Log;
import com.gigigo.ruialonso.roompersistance.db.entities.Project;
import com.gigigo.ruialonso.roompersistance.db.entities.ProjectsUsersJoin;
import com.gigigo.ruialonso.roompersistance.db.entities.Repository;
import com.gigigo.ruialonso.roompersistance.db.Technology;
import com.gigigo.ruialonso.roompersistance.db.entities.RepositoryProjectJoin;
import com.gigigo.ruialonso.roompersistance.db.entities.User;
import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

  private static User addUser(final AppDatabase db, int id, String name, String email) {
    User user = new User();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    db.userDao().insertUser(user);
    Log.d("DATABASE", "inserted user " + name);
    return user;
  }

  private static Project addProject(final AppDatabase db, int id, String name, Date creationDate, Technology technology) {
    Project project = new Project();
    project.setId(id);
    project.setName(name);
    project.setCreationDate(creationDate);
    project.setTechnology(technology);

    db.projectDao().insertProject(project);
    Log.d("DATABASE", "inserted project " + name);
    return project;
  }

  private static Repository addRepository(final AppDatabase db, int id, String name, String url, int userId,
      Date creationDate) {
    Repository repository = new Repository();
    repository.setId(id);
    repository.setName(name);
    repository.setUrl(url);
    repository.setUserId(userId);
    repository.setCreationDate(creationDate);

    db.repositoryDao().insertRepository(repository);
    Log.d("DATABASE", "inserted repo " + name);
    return repository;
  }

  private static RepositoryProjectJoin addRepoProject(final AppDatabase db, int repoId, int projectId) {
    RepositoryProjectJoin repositoryProjectJoin = new RepositoryProjectJoin();
    repositoryProjectJoin.setRepoId(repoId);
    repositoryProjectJoin.setProjectId(projectId);

    db.repositoryProjectJoinDao().insertRepositoryProject(repositoryProjectJoin);
    Log.d("DATABASE", "inserted repoProject " + repoId + "-"+ projectId);
    return repositoryProjectJoin;
  }

  private static ProjectsUsersJoin addUserProject(final AppDatabase db, int userId, int projectId) {
    ProjectsUsersJoin projectsUsersJoin = new ProjectsUsersJoin();
    projectsUsersJoin.setUserId(userId);
    projectsUsersJoin.setProjectId(projectId);

    db.projectsUsersJoinDao().insertProjectsUsersJoin(projectsUsersJoin);
    Log.d("DATABASE", "inserted userProject " + userId + "-"+ projectId);
    return projectsUsersJoin;
  }

  public static void clearData(final AppDatabase db) {
    db.userDao().deleteAll();
    db.repositoryProjectJoinDao().deleteAll();
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
    User claudia = addUser(db, 7, "Claudia", "claudia@gigigo.com");

    Date date = getTodayPlusDays(0);

    Repository ruiGithub = addRepository(db, 1, "Rui Github", "github.com", rui.getId(), date);
    Repository ruiBitbucket = addRepository(db, 2, "Rui Bitbucket", "bitbucket.org", rui.getId(), date);
    Repository manuGithub = addRepository(db, 3, "Manu Github", "github.com", manu.getId(), date);
    Repository manuBitbucket = addRepository(db, 4, "Manu Bitbucket", "bitbucket.org", manu.getId(), date);
    Repository beniGithub = addRepository(db, 5, "Beni Github", "github.com", beni.getId(), date);
    Repository beniBitbucket = addRepository(db, 6, "Beni Bitbucket", "bitbucket.org", beni.getId(), date);
    Repository albertoGithub = addRepository(db, 7, "Alberto Github", "github.com", alberto.getId(), date);
    Repository santiBitbucket = addRepository(db, 8, "Santi Bitbucket", "bitbucket.org", santi.getId(), date);
    Repository alexBitbucket = addRepository(db, 9, "Alex Bitbucket", "bitbucket.org", alex.getId(), date);

    Project woah = addProject(db, 1, "WOAH", date, Technology.Android);
    Project vips = addProject(db, 2, "Vips", date, Technology.iOS);
    Project destapp = addProject(db, 3, "Destapp", date, Technology.WindowsPhone);
    Project mcdonald = addProject(db, 4, "McDonalds", date, Technology.Android);
    Project orchextra = addProject(db, 5, "Orchextra", date, Technology.iOS);
    Project nubico = addProject(db, 6, "Nubico", date, Technology.Blackberry10);
    Project ferring = addProject(db, 7, "Ferring", date, Technology.Android);
    Project forme = addProject(db, 8, "Forme", date, Technology.Android);
    Project smileworld = addProject(db, 9, "Smileworld", date, Technology.Android);

    addRepoProject(db, ruiGithub.getId(), woah.getId());
    addRepoProject(db, santiBitbucket.getId(), vips.getId());
    addRepoProject(db, albertoGithub.getId(), destapp.getId());
    addRepoProject(db, beniBitbucket.getId(), mcdonald.getId());
    addRepoProject(db, beniGithub.getId(), ferring.getId());
    addRepoProject(db, alexBitbucket.getId(), forme.getId());
    addRepoProject(db, manuGithub.getId(), orchextra.getId());
    addRepoProject(db, ruiGithub.getId(), orchextra.getId());
    addRepoProject(db, ruiGithub.getId(), nubico.getId());

    addUserProject(db, rui.getId(), woah.getId());
    addUserProject(db, rui.getId(), nubico.getId());
    addUserProject(db, rui.getId(), orchextra.getId());
    addUserProject(db, manu.getId(), orchextra.getId());
    addUserProject(db, beni.getId(), mcdonald.getId());
    addUserProject(db, beni.getId(), ferring.getId());
    addUserProject(db, alberto.getId(), destapp.getId());
    addUserProject(db, santi.getId(), vips.getId());
    addUserProject(db, alex.getId(), forme.getId());
  }

  private static Date getTodayPlusDays (int daysAgo) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, daysAgo);
    return calendar.getTime();
  }
}
