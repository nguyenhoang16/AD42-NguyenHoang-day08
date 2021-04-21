package com.example.ad42_nguyenhoang_day07;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad42_nguyenhoang_day07.Event.Bus;
import com.example.ad42_nguyenhoang_day07.Event.ETrade;
import com.example.ad42_nguyenhoang_day07.databinding.HomeFragmentBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment{
    HomeFragmentBinding binding;
    List<Contact> contactList;
    AdapterContact adapterContact;
    View btnAdd;

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false);

        btnAdd = binding.btnAdd;
//        Contact contact = new Contact(getArguments().getString("NameAdd"),getArguments().getString("PhoneAdd"));
//        contactList.add(contact);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(BtnAddFragment.newInstance());
            }

            private void getFragment(BtnAddFragment newInstance) {
                getFragmentManager().beginTransaction().replace(R.id.container,newInstance).commit();
            }
        });


        getContactList();
        adapterContact =new AdapterContact(contactList);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.rcContact.setAdapter(adapterContact);
        binding.rcContact.setLayoutManager(layoutManager);
        adapterContact.setIonClickName(new IonClickName() {
            @Override
            public void onClickItem(View view, int position) {
                Contact c = contactList.get(position);
                Bus.getInstance().post(c);
            }
        });
        return binding.getRoot();
    }

    private List<Contact> getContactList(){
        contactList = new ArrayList<>();
        // image
//        Uri photoUri =ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI,
//                Long.parseLong(String.valueOf(getId())));;
        ContentResolver cr = getContext().getContentResolver();
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String name = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String avatar = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                Contact c = new Contact(name,phone);
                // lấy image contact
                if (avatar != null){
                    c.setImgURI(avatar);
                    c.setIsImage(true);
                } else
                    c.setIsImage(false);
                contactList.add(c);
            }
        }
        return contactList;
    }
    @Override
    public void onStart() {
        super.onStart();
        Bus.getInstance().register(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        Bus.getInstance().unRegister(getContext());
    }
}
