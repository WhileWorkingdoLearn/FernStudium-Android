package com.example.praxisphase_tagdrei;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class WebView_Fragment extends Fragment {
    private WebView webView;
    private View root;
    private ViewModelWebView vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_webview,container,false);
        webView = root.findViewById(R.id.webView);
        vm = new ViewModelProvider(requireActivity()).get(ViewModelWebView.class);
        vm.getData().observe(getViewLifecycleOwner(), s -> {
            webView.loadData(s,"text/html; charset=UTF-8", null);
        });
        return root;
    }
}
