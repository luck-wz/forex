package com.imsoft.forex.controller.response;

import java.util.List;

public class ForexResponse {
    
    private ForexStatus error;
    
    private List<?> currency;

    public ForexStatus getError() {
        return error;
    }

    public void setError(ForexStatus error) {
        this.error = error;
    }

    public List<?> getCurrency() {
        return currency;
    }

    public void setCurrency(List<?> currency) {
        this.currency = currency;
    }
    
}
