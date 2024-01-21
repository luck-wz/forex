package com.imsoft.forex.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "COLLECTION")
public class Collection {

    @Id
    @Column(name = "COLLECTION_DATE")
    @JsonFormat(pattern = "yyyyMMdd")
    @JsonProperty(value = "date")
    private Date date;
    
    @Column(name = "USD_TO_NTD")
    @JsonProperty(value = "usd")
    private String usdToNtd;

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

    @Override
    public String toString() {
        return "Collection [date=" + date + ", usdToNtd=" + usdToNtd + "]";
    }

}
