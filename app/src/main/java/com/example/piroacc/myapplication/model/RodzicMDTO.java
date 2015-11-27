package com.example.piroacc.myapplication.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RodzicMDTO implements java.io.Serializable {

    private Integer rodzicId;
    private String dataUtworzenia;
    private String imie;
    private String haslo;
    private boolean status;
    private String email;
    private String numerTelefonu;

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getImie() {

        return imie;
    }

    public void setRodzicId(Integer rodzicId) {
        this.rodzicId = rodzicId;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public Integer getRodzicId() {

        return rodzicId;
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

    public String getEmail() {
        return email;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public RodzicMDTO() {

    }

    public RodzicMDTO(String dataUtworzenia , String imie, String haslo, boolean status, String email, String numerTelefonu) {
        this.dataUtworzenia = dataUtworzenia;
        this.imie = imie;
        this.haslo = haslo;
        this.status = status;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

}
