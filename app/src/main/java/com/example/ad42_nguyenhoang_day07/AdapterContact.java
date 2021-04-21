package com.example.ad42_nguyenhoang_day07;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad42_nguyenhoang_day07.databinding.ItemContactBinding;

import java.util.List;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.Viewhoder> {
    List<Contact> contactList;
    IonClickName ionClickName;

    public AdapterContact(List<Contact> contactList, IonClickName ionClickName) {
        this.contactList = contactList;
        this.ionClickName = ionClickName;
    }

    public AdapterContact(List<Contact> contactList) {this.contactList = contactList;
    }

    public void setIonClickName(IonClickName ionClickName) {
        this.ionClickName = ionClickName;
    }

    @NonNull
    @Override
    public AdapterContact.Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        ItemContactBinding binding;
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_contact,parent,false );

        Viewhoder viewhoder = new Viewhoder(binding);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Contact contact = contactList.get(position);
        holder.binding.tvName.setText(contact.getName());

        if(contact.isImage())
            holder.binding.imgAvatar.setImageURI(Uri.parse(contact.getImgURI()));
        else
            holder.binding.imgAvatar.setImageResource(R.drawable.ic_baseline_account_box_24);
        holder.binding.item.setOnClickListener(v -> ionClickName.onClickItem(holder.binding.item,position));

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ItemContactBinding binding;
        public Viewhoder(@NonNull ItemContactBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

        }
    }
}
