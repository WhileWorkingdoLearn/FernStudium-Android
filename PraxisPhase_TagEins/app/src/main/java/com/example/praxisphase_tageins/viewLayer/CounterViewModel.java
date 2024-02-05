package com.example.praxisphase_tageins.viewLayer;


import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.praxisphase_tageins.counter.Counter;

public class CounterViewModel extends ViewModel {

    private static final String NAME = "CounterValues";
    private Counter counter;
    private LiveData<String> cntValue;
    private SharedPreferences sharedPref;

    public CounterViewModel(){
        counter = new Counter();
        cntValue = counter.getLiveDataValue();
    }
    public void init(Context context,String id){
        sharedPref = context.getApplicationContext().getSharedPreferences(NAME,context.MODE_PRIVATE);
        counter.setValue(getSavedData(id));
    }


    public void countUp(){
        counter.countUp();
    }
    public void reset(){
        counter.setValue(0);
    }
    public LiveData<String> getCounterData(){
        return cntValue;
    }
    public boolean saveCounterData(String id){
        if(this.sharedPref != null){
            sharedPref.edit().putInt(id,counter.getValue()).commit();
            return true;
        }
        return false;
    }

    private int getSavedData(String id){
        if(this.sharedPref != null) {
            int cnt = sharedPref.getInt(id, 0);
            return cnt;
        }
        return 0;
    }


}
