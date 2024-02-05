package com.example.praxisphase_tagdrei;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class Selection_Fragment extends Fragment {
    private View root;
    private Button buttonWeb1, buttonWeb2, buttonWeb3;
    private ViewModelWebView viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_selection,container,false);
        buttonWeb1 = root.findViewById(R.id.buttonWeb1);
        buttonWeb2 = root.findViewById(R.id.buttonWeb2);
        buttonWeb3 = root.findViewById(R.id.buttonWeb3);
        viewModel = new ViewModelProvider(requireActivity()).get(ViewModelWebView.class);
        buttonWeb1.setOnClickListener(View->{
                    viewModel.loadData("hmtl1","webView1");
        });
        buttonWeb2.setOnClickListener(View->{
           viewModel.loadData("hmtl2","webView2");
        });
        buttonWeb3.setOnClickListener(View->{
           viewModel.loadData("hmtl3","webView2");
        });
        return root;
    }
}
