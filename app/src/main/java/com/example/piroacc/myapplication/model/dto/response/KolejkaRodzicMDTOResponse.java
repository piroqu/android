package com.example.piroacc.myapplication.model.dto.response;


import java.io.Serializable;

/**
 * Created by PiroACC on 2015-11-30.
 */
public class KolejkaRodzicMDTOResponse implements Serializable{

    private String typZadanie;
    private Integer dzieckodzieckoId;

    @Override
    public String toString() {
        return "KolejkaRodzicMDTOResponse{" +
                "typZadanie='" + typZadanie + '\'' +
                ", dzieckodzieckoId=" + dzieckodzieckoId +
                '}';
    }

    public String getTypZadanie() {
        return typZadanie;
    }

    public void setTypZadanie(String typZadanie) {
        this.typZadanie = typZadanie;
    }

    public int getDzieckodzieckoId() {
        return dzieckodzieckoId;
    }

    public void setDzieckodzieckoId(int dzieckodzieckoId) {
        this.dzieckodzieckoId = dzieckodzieckoId;
    }
}
