package com.example.ad42_nguyenhoang_day07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.ad42_nguyenhoang_day07.Event.ETrade;
import com.example.ad42_nguyenhoang_day07.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            getFragment(MainFragment.newInstance());
        }
    }

    private void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(Contact contact) {

        String phone = contact.getPhone();

        FragmentDetail fragmentDetail = new FragmentDetail();
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        fragmentDetail.setArguments(bundle);
        getFragment(fragmentDetail);



    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent1(ETrade eTrade){

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NameAdd",eTrade.getName());
        bundle.putString("PhoneAdd",eTrade.getSdt());
        mainFragment.setArguments(bundle);
        getFragment(mainFragment);
    }

}