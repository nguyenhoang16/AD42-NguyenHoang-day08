package com.example.ad42_nguyenhoang_day07;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.ad42_nguyenhoang_day07.Event.Bus;
import com.example.ad42_nguyenhoang_day07.Event.ETrade;
import com.example.ad42_nguyenhoang_day07.databinding.BtnAddFragmentBinding;

public class BtnAddFragment extends Fragment {
    BtnAddFragmentBinding binding;
    Button btnAdd;
    EditText edName,edPhone;

    public static BtnAddFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BtnAddFragment fragment = new BtnAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.btn_add_fragment,container,false);
        btnAdd = binding.btnAddPhoneBook;
        edName = binding.addName;
        edPhone = binding.addPhoneNumber;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edName.getText().toString().equals("") || edPhone.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Chưa nhập thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    Bus.getInstance().post(new ETrade(edName.toString(),edPhone.toString()));
                    closefragment(MainFragment.newInstance());
                }
            }

        });


        return binding.getRoot();
    }
    private void closefragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
}
