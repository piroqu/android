package com.example.piroacc.myapplication.model.dto.response.parent;


public class ParentChildMDTOResponse implements java.io.Serializable {

    private Integer childId;
    private String name;
    private String email;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Name : " + name + ", email : " + email;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
