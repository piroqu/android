package com.example.piroacc.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class Pozycja implements Parcelable {

    private Integer id;
    private double dlugoscGeograficzna;
    private double szerokoscGeograficzna;
    private String data;
    private boolean czyZsynchronizowano;
    private Integer fkDzieckoId;

    public Pozycja() {
    }

    @Override
    public String toString() {
        return "Pozycja{" +
                "id=" + id +
                ", dlugoscGeograficzna=" + dlugoscGeograficzna +
                ", szerokoscGeograficzna=" + szerokoscGeograficzna +
                ", data=" + data +
                ", czyZsynchronizowano=" + czyZsynchronizowano +
                ", fkDzieckoId=" + fkDzieckoId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    public void setDlugoscGeograficzna(double dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    public double getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    public void setSzerokoscGeograficzna(double szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isCzyZsynchronizowano() {
        return czyZsynchronizowano;
    }

    public void setCzyZsynchronizowano(boolean czyZsynchronizowano) {
        this.czyZsynchronizowano = czyZsynchronizowano;
    }

    public Integer getFkDzieckoId() {
        return fkDzieckoId;
    }

    public void setFkDzieckoId(Integer fkDzieckoId) {
        this.fkDzieckoId = fkDzieckoId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private Pozycja(Parcel in) {
        id = in.readInt();
        dlugoscGeograficzna = in.readDouble();
        szerokoscGeograficzna = in.readDouble();
        data = in.readString();
//        czyZsynchronizowan
        fkDzieckoId = in.readInt();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(dlugoscGeograficzna);
        dest.writeDouble(szerokoscGeograficzna);
        dest.writeString(data);
//        dest.writeBooleanArray(czyZsynchronizowano);
        dest.writeInt(fkDzieckoId);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Pozycja> CREATOR = new Parcelable.Creator<Pozycja>() {
        public Pozycja createFromParcel(Parcel in) {
            return new Pozycja(in);
        }

        public Pozycja[] newArray(int size) {
            return new Pozycja[size];
        }
    };
}
