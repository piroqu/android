package com.example.piroacc.myapplication.model;

import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;

import java.util.Date;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class Uzytkownik  {

    private Integer id;
    private String dataUtworzenia;
    private String haslo;
    private String imie;
    private String email;
    private String numerTelefonu;
    private boolean isParent;

    public Uzytkownik() {
    }

    public Uzytkownik(RodzicMDTORequest parentsData) {
        id = parentsData.getRodzicId();
        dataUtworzenia = parentsData.getDataUtworzenia();
        haslo = parentsData.getHaslo();
        imie = parentsData.getImie();
        email = parentsData.getEmail();
        numerTelefonu = parentsData.getNumerTelefonu();
        isParent = true;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", dataUtworzenia=" + dataUtworzenia +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", email='" + email + '\'' +
                ", numerTelefonu='" + numerTelefonu + '\'' +
                '}';
    }

    public boolean isParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(String dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
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
