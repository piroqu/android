package com.example.piroacc.myapplication.model;

import com.example.piroacc.myapplication.model.dto.response.KolejkaRodzicMDTOResponse;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class Dziecko {

    private Integer id;
    private String imie;

    public Dziecko() {
    }

    public Dziecko (KolejkaRodzicMDTOResponse kolejkaRodzicMDTOResponse){
        id = kolejkaRodzicMDTOResponse.getDzieckodzieckoId();
        imie = "TESTOWE";
    }

    public Dziecko(Integer id, String imie) {
        this.id = id;
        this.imie = imie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
}
