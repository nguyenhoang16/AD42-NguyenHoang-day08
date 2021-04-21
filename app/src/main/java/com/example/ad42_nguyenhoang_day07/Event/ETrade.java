package com.example.ad42_nguyenhoang_day07.Event;

public class ETrade {
String name,sdt;

    public ETrade() {
    }

    public ETrade(String name, String sdt) {
        this.name = name;
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
