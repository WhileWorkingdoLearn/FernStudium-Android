package com.example.praxisphase_tageins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.praxisphase_tageins.viewLayer.CounterViewModel;
import com.example.praxisphase_tageins.viewLayer.Counter_Fragment;
import com.example.praxisphase_tageins.viewLayer.ServiceViewModel;

public class MainActivity extends AppCompatActivity {
    private ServiceViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, Counter_Fragment.class,null)
                .commit();
        vm = new ViewModelProvider(this).get(ServiceViewModel.class);
        vm.initService(getApplicationContext());
        vm.logCount("Hello World");
    }

    @Override
    protected void onPause() {
        super.onPause();
        vm.stopCounterService(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.counter_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String title = menu.getItem(0).getTitle().toString().toLowerCase();
        if(title.equals("add counter 2")){
            menu.getItem(0).setTitle("remove counter 2");
        } else {
            menu.getItem(0).setTitle("add counter 2");
        }

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_counter_add:
                if(item.getTitle().toString().toLowerCase().equals("add counter 2")) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout2, Counter_Fragment.class, null)
                            .commit();
                } else {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout2);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(fragment)
                            .commit();
                }
                return true;
            default:
                return false;
        }
    }


}