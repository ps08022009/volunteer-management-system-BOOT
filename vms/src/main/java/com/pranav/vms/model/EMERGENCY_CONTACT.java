package com.pranav.vms.model;

public class EMERGENCY_CONTACT {

    private String name;   
    private String phone;  

    public EMERGENCY_CONTACT() {}

    public EMERGENCY_CONTACT(String name, String phone) {
        this.name = name;
        this.phone = phone;
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
}
