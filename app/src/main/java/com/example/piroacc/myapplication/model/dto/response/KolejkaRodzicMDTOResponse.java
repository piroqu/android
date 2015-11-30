package com.example.piroacc.myapplication.model.dto.response;


import java.io.Serializable;

/**
 * Created by PiroACC on 2015-11-30.
 */
public class KolejkaRodzicMDTOResponse implements Serializable{

    private String typZadanie;
    private Integer dzieckodzieckoId;
    private String imieDziecka;

    @Override
    public String toString() {
        return "KolejkaRodzicMDTOResponse{" +
                "typZadanie='" + typZadanie + '\'' +
                ", dzieckodzieckoId=" + dzieckodzieckoId +
                ", imieDziecka='" + imieDziecka + '\'' +
                '}';
    }

    public String getTypZadanie() {
        return typZadanie;
    }

    public void setTypZadanie(String typZadanie) {
        this.typZadanie = typZadanie;
    }

    public Integer getDzieckodzieckoId() {
        return dzieckodzieckoId;
    }

    public void setDzieckodzieckoId(Integer dzieckodzieckoId) {
        this.dzieckodzieckoId = dzieckodzieckoId;
    }

    public String getImieDziecka() {
        return imieDziecka;
    }

    public void setImieDziecka(String imieDziecka) {
        this.imieDziecka = imieDziecka;
    }
}
