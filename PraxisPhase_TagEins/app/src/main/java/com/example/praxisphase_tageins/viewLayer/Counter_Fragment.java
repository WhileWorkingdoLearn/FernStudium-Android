package com.example.praxisphase_tageins.viewLayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.praxisphase_tageins.R;

public class Counter_Fragment extends Fragment {
    private View root;
    private CounterViewModel vm;
    private TextView txt;
    private Button buttonUp, buttonRest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_counter,container,false);
        vm = new ViewModelProvider(this).get(CounterViewModel.class);
        vm.init(requireContext(),"" + this.getId());
        buttonUp = root.findViewById(R.id.buttonAdd);
        buttonUp.setOnClickListener(view -> {
                vm.countUp();
        });
        buttonRest = root.findViewById(R.id.buttonReset);
        buttonRest.setOnClickListener(View->{
            vm.reset();
        });

        txt = root.findViewById(R.id.text_counter);
        vm.getCounterData().observe(requireActivity(), s -> {
            txt.setText(s);
        });

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        vm.saveCounterData("" + this.getId());
    }
}
