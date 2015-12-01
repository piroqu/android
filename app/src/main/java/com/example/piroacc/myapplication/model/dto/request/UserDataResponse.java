package com.example.piroacc.myapplication.model.dto.request;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by PiroACC on 2015-11-30.
 */
public class UserDataResponse implements Parcelable{

    private Integer id;
    private String name;
    private String creation_date;
    private String email;
    private String role;

    public UserDataResponse() {

    }


    @Override
    public String toString() {
        return "UserDataResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creation_date=" + creation_date +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private UserDataResponse(Parcel in){
        id = in.readInt();
        name = in.readString();
        creation_date = in.readString();
        email=in.readString();
        role=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(creation_date);
        dest.writeString(email);
        dest.writeString(role);
    }
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<UserDataResponse> CREATOR = new Parcelable.Creator<UserDataResponse>() {
        public UserDataResponse createFromParcel(Parcel in) {
            return new UserDataResponse(in);
        }

        public UserDataResponse[] newArray(int size) {
            return new UserDataResponse[size];
        }
    };
}
