package com.example.daniil.SpringBootTestApp.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    private String id;
    private String numCode, charCode, nominal, name, value;

    public Currency() {
    }

    public Currency(String id, String numCode, String charCode, String nominal, String name, String value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getNominal() {
        return nominal;
    }
}