package com.example.wsrfood.activities.launchScreen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wsrfood.api.ApiInterface;
import com.example.wsrfood.api.RetrofitInstance;
import com.example.wsrfood.api.dish.DishResponse;
import com.example.wsrfood.api.version.VersionResponse;
import com.example.wsrfood.database.DishDao;
import com.example.wsrfood.database.DishDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LaunchViewModel extends AndroidViewModel {
    private static final String TAG = "LaunchViewModel";
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final DishDao dao;
    private final ApiInterface api;

    public LaunchViewModel(@NonNull Application application) {
        super(application);
        dao = DishDatabase.getInstance(application).dishDao();
        api = RetrofitInstance.apiService;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void refreshDishes() {
        Disposable disposable = dao.removeAllDishes().andThen(api.getDishes()).map(DishResponse::getDishes).flatMapCompletable(dishes -> dao.insertDishes(dishes).subscribeOn(Schedulers.io())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            isLoading.setValue(false);
        }, throwable -> error.setValue(throwable.getMessage()));
        compositeDisposable.add(disposable);
    }

    public void compareLocalVersionsToRemote() {
        io.reactivex.rxjava3.functions.BiFunction<VersionResponse, List<String>, Boolean> zipper = (versionResponse, localVersions) -> {
            List<String> versions = versionResponse.getVersions();
            return versions.equals(localVersions);
        };
        Disposable disposable = Single.zip(RetrofitInstance.apiService.loadVersions(), dao.getVersions(), zipper).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((isEqual, err) -> {
            if (err != null) {
                error.setValue(err.getMessage());
            } else {
                if (isEqual) {
                    isLoading.setValue(false);
                } else {
                    isLoading.setValue(true);
                }
            }
        });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}

