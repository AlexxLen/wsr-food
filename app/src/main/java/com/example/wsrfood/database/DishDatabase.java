package com.example.wsrfood.database;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wsrfood.models.Dish;

@Database(entities = {Dish.class}, version = 1, exportSchema = false)
public abstract class DishDatabase extends RoomDatabase {
    private static final String DB_NAME = "dish.db";

    private static DishDatabase instance = null;

    public static DishDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    DishDatabase.class,
                    DB_NAME
            ).build();
        }
        return instance;
    }

    public abstract DishDao dishDao();
}
