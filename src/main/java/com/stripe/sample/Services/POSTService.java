package com.stripe.sample.Services;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.sample.Models.CreatePaymentResponse;
import com.stripe.sample.Models.PaymentRequest;
//import com.stripe.sample.Server;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

import static spark.Spark.staticFiles;

@Service
public class POSTService {
    private static Gson gson = new Gson();

    public String postIntent(PaymentRequest paymentRequest){
        //  port(4343);
        staticFiles.externalLocation(Paths.get("public").toAbsolutePath().toString());

    // This is your test secret API key.
    Stripe.apiKey = "sk_test_51O4f1QCTYTgJy1iwwrbAL6pCRpD0mU5R9ss6nkg3szjKEVMhDIvXmFh0PRr8VgfIGoVy66Jkyd343ubhi8HprhIp00tNjmuRRY";

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) paymentRequest.getAmount())
                        .setCurrency(paymentRequest.getCurrency())
                        .putMetadata("provider_id", "" + paymentRequest.getPaymentRequestMeta().getProvider_id() + "")
                        .putMetadata("order_id", "" + paymentRequest.getPaymentRequestMeta().getOrder_id() + "")
                        // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = null;
        try {
            paymentIntent = PaymentIntent.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
        return gson.toJson(paymentResponse);
}
}
