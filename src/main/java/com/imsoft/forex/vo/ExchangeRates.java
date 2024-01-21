package com.imsoft.forex.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRates {
    
    @JsonProperty(value = "Date")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date date;
    
    @JsonProperty(value = "USD/NTD")
    private String usdToNtd;
    
    @JsonProperty(value = "RMB/NTD")
    private String rmbToNtd;
    
    @JsonProperty(value = "USD/JPY")
    private String usdToJpy;

    @JsonProperty(value = "GBP/USD")
    private String gbpToUsd;
    
    @JsonProperty(value = "AUD/USD")
    private String audToUsd;
    
    @JsonProperty(value = "USD/HKD")
    private String usdToHkd;
    
    @JsonProperty(value = "USD/ZAR")
    private String usdToZar;
    
    @JsonProperty(value = "NZD/USD")
    private String nzdToUsd;
    
    @JsonProperty(value = "USD/RMB")
    private String usdToRmb;
    
    @JsonProperty(value = "EUR/USD")
    private String eurToUsd;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsdToNtd() {
        return usdToNtd;
    }

    public void setUsdToNtd(String usdToNtd) {
        this.usdToNtd = usdToNtd;
    }

    public String getRmbToNtd() {
        return rmbToNtd;
    }

    public void setRmbToNtd(String rmbToNtd) {
        this.rmbToNtd = rmbToNtd;
    }

    public String getUsdToJpy() {
        return usdToJpy;
    }

    public void setUsdToJpy(String usdToJpy) {
        this.usdToJpy = usdToJpy;
    }

    public String getGbpToUsd() {
        return gbpToUsd;
    }

    public void setGbpToUsd(String gbpToUsd) {
        this.gbpToUsd = gbpToUsd;
    }

    public String getAudToUsd() {
        return audToUsd;
    }

    public void setAudToUsd(String audToUsd) {
        this.audToUsd = audToUsd;
    }

    public String getUsdToHkd() {
        return usdToHkd;
    }

    public void setUsdToHkd(String usdToHkd) {
        this.usdToHkd = usdToHkd;
    }

    public String getUsdToZar() {
        return usdToZar;
    }

    public void setUsdToZar(String usdToZar) {
        this.usdToZar = usdToZar;
    }

    public String getNzdToUsd() {
        return nzdToUsd;
    }

    public void setNzdToUsd(String nzdToUsd) {
        this.nzdToUsd = nzdToUsd;
    }

    public String getUsdToRmb() {
        return usdToRmb;
    }

    public void setUsdToRmb(String usdToRmb) {
        this.usdToRmb = usdToRmb;
    }

    public String getEurToUsd() {
        return eurToUsd;
    }

    public void setEurToUsd(String eurToUsd) {
        this.eurToUsd = eurToUsd;
    }

    @Override
    public String toString() {
        return "ExchangeRates [date=" + date + ", usdToNtd=" + usdToNtd + ", rmbToNtd=" + rmbToNtd + ", usdToJpy="
                + usdToJpy + ", gbpToUsd=" + gbpToUsd + ", audToUsd=" + audToUsd + ", usdToHkd=" + usdToHkd
                + ", usdToZar=" + usdToZar + ", nzdToUsd=" + nzdToUsd + ", usdToRmb=" + usdToRmb + ", eurToUsd="
                + eurToUsd + "]";
    }

}
