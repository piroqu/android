package com.example.piroacc.myapplication.model.dto.request;

import com.example.piroacc.myapplication.model.Uzytkownik;

import java.util.Date;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DzieckoMDTORequest implements java.io.Serializable {

    private Integer dzieckoId;
    private String dataUtworzenia;
    private String haslo;
    private boolean status;
    private String imie;

    public DzieckoMDTORequest() {
    }

    public DzieckoMDTORequest(Uzytkownik user) {
        this.dzieckoId = user.getId();
        this.dataUtworzenia = user.getDataUtworzenia();
        this.haslo = user.getHaslo();
        this.status = true;
        this.imie = user.getImie();
    }

    public DzieckoMDTORequest(Integer dzieckoId, String dataUtworzenia, String haslo, boolean status, String imie) {
        this.dzieckoId = dzieckoId;
        this.dataUtworzenia = dataUtworzenia;
        this.haslo = haslo;
        this.status = status;
        this.imie = imie;
    }

    public void setDzieckoId(Integer dzieckoId) {
        this.dzieckoId = dzieckoId;
    }

    public void setDataUtworzenia(String dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Integer getDzieckoId() {
        return dzieckoId;
    }

    public String getDataUtworzenia() {
        return dataUtworzenia;
    }

    public String getHaslo() {
        return haslo;
    }

    public boolean isStatus() {
        return status;
    }

    public String getImie() {
        return imie;
    }
}
