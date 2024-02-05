package com.example.praxisphase_tagvier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.praxisphase_tagvier.datalayer.Search_Fragment;

public class MainActivity extends AppCompatActivity {
    private TodoAppViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //vm = new ViewModelProvider(this).get(TodoAppViewModel.class);
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, Search_Fragment.class,null,"searchFragment")
                .commit();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //vm.closeDatabase();
    }
}