package com.example.praxisphase_tagdrei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_selection, Selection_Fragment.class, null)
                .commit();

        if(findViewById(R.id.layout_horizontal) != null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_selection, Selection_Fragment.class, null)
                    .replace(R.id.fragment_webview1, WebView_Fragment.class,null,"Webview1")
                    .replace(R.id.fragment_webview2, WebView_Fragment.class, null,"Webview2")
                    .replace(R.id.fragment_webview3, WebView_Fragment.class, null,"Webview3")
                    .commit();
        }
        new ViewModelProvider(this).get(ViewModelWebView.class);
    }
}