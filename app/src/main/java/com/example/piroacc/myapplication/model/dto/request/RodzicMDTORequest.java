package com.example.piroacc.myapplication.model.dto.request;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RodzicMDTORequest implements java.io.Serializable {

    private Integer rodzicId;
    private String dataUtworzenia;
    private String imie;
    private String haslo;
    private boolean status;
    private String email;
    private String numerTelefonu;

    @Override
    public String toString() {
        return "RodzicMDTORequest{" +
                "rodzicId=" + rodzicId +
                ", dataUtworzenia=" + dataUtworzenia +
                ", imie='" + imie + '\'' +
                ", haslo='" + haslo + '\'' +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", numerTelefonu='" + numerTelefonu + '\'' +
                '}';
    }

    public Integer getRodzicId() {
        return rodzicId;
    }

    public void setRodzicId(Integer rodzicId) {
        this.rodzicId = rodzicId;
    }

    public String getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(String dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
