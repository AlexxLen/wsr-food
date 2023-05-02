package com.example.wsrfood.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wsrfood.models.Dish;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DishDao {

    @Insert
    Completable insertDishes(List<Dish> dishes);

    @Query("SELECT * FROM dishes")
    LiveData<List<Dish>> getAllDishes();

    @Query("SELECT * FROM dishes WHERE id = :dishId")
    LiveData<Dish> getDishById(int dishId);

    @Query ("SELECT DISTINCT version FROM dishes ORDER BY version ASC")
    Single<List<String>> getVersions();

    @Query("DELETE FROM dishes WHERE 1 = 1")
    Completable removeAllDishes();

}
