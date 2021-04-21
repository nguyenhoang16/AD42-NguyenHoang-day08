package com.example.ad42_nguyenhoang_day07;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String id, name, phone;
    private int imageAvatar;
    private boolean isImage;
    private Uri avatar;
    private String imgURI;

    public Contact() {
    }

    public Contact(String name, String phone, boolean isImage) {
        this.name = name;
        this.phone = phone;
        this.isImage = isImage;
    }

    public Contact(String name, String phone, String imgURI) {
        this.name = name;
        this.phone = phone;
        this.imgURI = imgURI;
    }

    public Contact(String name, String phone, Uri avatar, boolean isImage) {
        this.name = name;
        this.phone = phone;
        this.avatar = avatar;
        this.isImage = isImage;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Contact(String name) {
        this.name = name;
    }

    protected Contact(Parcel in) {
        id = in.readString();
        name = in.readString();
        phone = in.readString();
        imageAvatar = in.readInt();
        isImage = in.readByte() != 0;
        avatar = in.readParcelable(Uri.class.getClassLoader());
        imgURI = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeInt(imageAvatar);
        dest.writeByte((byte) (isImage ? 1 : 0));
        dest.writeParcelable(avatar, flags);
        dest.writeString(imgURI);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(int imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public Uri getAvatar() {
        return avatar;
    }

    public void setAvatar(Uri avatar) {
        this.avatar = avatar;
    }

    public String getImgURI() {
        return imgURI;
    }

    public void setImgURI(String imgURI) {
        this.imgURI = imgURI;
    }

    public boolean isImage() {
        return isImage;
    }
    public void setIsImage(boolean isImage){
        this.isImage = isImage;
    }
}
