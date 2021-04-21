package com.example.ad42_nguyenhoang_day07;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.ad42_nguyenhoang_day07.databinding.DetailFragmentBinding;

public class FragmentDetail extends Fragment {
    DetailFragmentBinding binding;
    Button btnBack;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.detail_fragment,container,false);
        binding.tvgetPhone.setText("Số điện thoại: "+getArguments().getString("phone"));
        btnBack = binding.btnQuayLai;
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment(MainFragment.newInstance());
            }
        });
        return binding.getRoot();
    }
    private void closefragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}
