package com.example.piroacc.myapplication.model.dto.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DzieckoMDTOResponse implements java.io.Serializable {

//    @SerializedName("id")
    private Integer dzieckoId;

    public Integer getDzieckoId() {
        return dzieckoId;
    }

    public void setDzieckoId(Integer dzieckoId) {
        this.dzieckoId = dzieckoId;
    }

    @Override
    public String toString() {
        return "DzieckoMDTOResponse{" +
                "dzieckoId=" + dzieckoId +
                '}';
    }
}
