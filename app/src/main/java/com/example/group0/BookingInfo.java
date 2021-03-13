package com.example.group0;

public class BookingInfo {

    String clothe, size, item, fullname, phoneNo, address, date, total;

    public BookingInfo (){

    }

    public BookingInfo(String clothe, String size, String item, String fullname, String phoneNo, String address, String date) {
        this.clothe = clothe;
        this.size = size;
        this.item = item;
        this.fullname = fullname;
        this.phoneNo = phoneNo;
        this.address = address;
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getClothe() {
        return clothe;
    }

    public void setClothe(String clothe) {
        this.clothe = clothe;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getphoneNo() {
        return phoneNo;
    }

    public void setphoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
