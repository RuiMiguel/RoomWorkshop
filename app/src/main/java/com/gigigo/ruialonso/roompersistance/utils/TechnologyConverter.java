package com.gigigo.ruialonso.roompersistance.utils;

import android.arch.persistence.room.TypeConverter;
import com.gigigo.ruialonso.roompersistance.db.Technology;

public class TechnologyConverter {
  @TypeConverter public static Technology toTechnology(String name) {
    return Technology.valueOf(name);
  }

  @TypeConverter public static String toName(Technology technology) {
    return technology.toString();
  }
}
