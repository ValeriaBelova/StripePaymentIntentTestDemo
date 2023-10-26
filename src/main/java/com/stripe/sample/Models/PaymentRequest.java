package com.stripe.sample.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

@Data
public class PaymentRequest {
    private Integer amount;
    private String currency;
    private PaymentRequestMeta paymentRequestMeta;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .create();
        return gson.toJson(this);
    }
}
