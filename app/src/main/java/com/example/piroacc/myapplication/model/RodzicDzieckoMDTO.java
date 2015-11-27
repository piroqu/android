package com.example.piroacc.myapplication.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class RodzicDzieckoMDTO implements Serializable {

    private Integer dzieckoId;
    private Integer rodzicId;



    public RodzicDzieckoMDTO(Integer dzieckoId, Integer rodzicId) {
        this.dzieckoId = dzieckoId;
        this.rodzicId = rodzicId;
    }

    public RodzicDzieckoMDTO() {
    }

    public Integer getDzieckoId() {
        return dzieckoId;
    }

    public void setDzieckoId(Integer dzieckoId) {
        this.dzieckoId = dzieckoId;
    }

    public Integer getRodzicId() {
        return rodzicId;
    }

    public void setRodzicId(Integer rodzicId) {
        this.rodzicId = rodzicId;
    }
}
