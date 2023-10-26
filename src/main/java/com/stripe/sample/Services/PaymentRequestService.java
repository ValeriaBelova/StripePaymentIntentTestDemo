package com.stripe.sample.Services;

import com.stripe.sample.Models.PaymentRequest;
import com.stripe.sample.Models.PaymentRequestMeta;

import java.util.Map;

public class PaymentRequestService {

    public PaymentRequest returnPaymentRequestFromRequestBody(Map<String, Object> requestBody) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount((Integer) requestBody.get("amount"));
        paymentRequest.setCurrency((String) requestBody.get("currency"));
        paymentRequest.setPaymentRequestMeta(
                new PaymentRequestMeta(
                        (Integer) requestBody.get("provider_id"),
                        (Integer) requestBody.get("order_id")));
        return paymentRequest;
    }
}
