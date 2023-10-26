package com.stripe.sample.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequestMeta {
    private int provider_id;
    private int order_id;
}
