package com.example.daniil.SpringBootTestApp.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String charCode1, charCode2, oldSum;
    private Double newSum;

    public History() {
    }

    public History(String charCode1, String charCode2, String oldSum, Double newSum) {
        this.charCode1 = charCode1;
        this.charCode2 = charCode2;
        this.oldSum = oldSum;
        this.newSum = newSum;
    }

    public String getCharCode1() {
        return charCode1;
    }

    public void setCharCode1(String charCode1) {
        this.charCode1 = charCode1;
    }

    public String getCharCode2() {
        return charCode2;
    }

    public void setCharCode2(String charCode2) {
        this.charCode2 = charCode2;
    }

    public String getOldSum() {
        return oldSum;
    }

    public void setOldSum(String oldSum) {
        this.oldSum = oldSum;
    }

    public Double getNewSum() {
        return newSum;
    }

    public void setNewSum(Double newSum) {
        this.newSum = newSum;
    }
}