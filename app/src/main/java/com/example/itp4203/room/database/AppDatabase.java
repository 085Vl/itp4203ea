package com.example.itp4203.room.database;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.itp4203.room.dao.CourseDao;
import com.example.itp4203.room.dao.UserDao;
import com.example.itp4203.room.model.Course;
import com.example.itp4203.room.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Course.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UserDao userDao();
    public abstract CourseDao courseDao();

    private static AppDatabase instance;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);
    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    ).build();
                }
            }
        }
        return instance;
    }
}
