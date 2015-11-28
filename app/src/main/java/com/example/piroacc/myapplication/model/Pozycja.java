package com.example.piroacc.myapplication.model;

import java.util.Date;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class Pozycja {

    private Integer id;
    private double dlugoscGeograficzna;
    private double szerokoscGeograficzna;
    private String data;
    private boolean czyZsynchronizowano;
    private Integer fkDzieckoId;

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
}
