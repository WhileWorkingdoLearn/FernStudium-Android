package com.example.praxisphase_tagvier;

import android.app.Application;
import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.praxisphase_tagvier.datalayer.CustomDataBase;
import com.example.praxisphase_tagvier.datalayer.ToDoAppDBHelper;

public class TodoAppViewModel extends AndroidViewModel {

    private CustomDataBase repository;

    private LiveData<String> searchResult;

    public TodoAppViewModel(@NonNull Application application) {
        super(application);
        this.repository = new CustomDataBase(application.getApplicationContext());
    }
    public void addEntry(String date,String place,String title, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ToDoAppDBHelper.TodoContract.COLLUMN_1,date);
        contentValues.put(ToDoAppDBHelper.TodoContract.COLLUMN_2,place);
        contentValues.put(ToDoAppDBHelper.TodoContract.COLLUMN_3,title);
        contentValues.put(ToDoAppDBHelper.TodoContract.COLLUMN_4,desc);
        repository.insert(contentValues);
    }
    public void search(String value){
        repository.find(value);
    }
    public void closeDatabase(){
        repository.closeDB();
    }

}
