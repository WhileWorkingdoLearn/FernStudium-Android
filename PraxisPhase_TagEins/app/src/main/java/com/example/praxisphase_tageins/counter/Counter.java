package com.example.praxisphase_tageins.counter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Counter {

    private int value;
    private MutableLiveData<String> liveDataValue;

    public Counter(){
        value = 0;
        liveDataValue = new MutableLiveData<>();
    }

    public void setValue(int value) {
        this.value = value;
        liveDataValue.postValue(""+ value);
    }

    public int getValue() {
        return value;
    }
    public LiveData<String> getLiveDataValue(){
        return liveDataValue;
    };
    public void countUp(){
        value++;
        liveDataValue.postValue(""+ value);
    }
    public void countDown(){
        if(value < 1){
            return;
        }
        value--;
        liveDataValue.postValue("" + value);
    }
}
