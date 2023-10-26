package com.stripe.sample.Controllers;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.sample.Models.PaymentRequest;
import com.stripe.sample.Services.POSTService;
import com.stripe.sample.Services.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {
    @Autowired
    WebPageController webPageController;

    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody Map<String, Object> requestBody){
            PaymentRequestService paymentRequestService = new PaymentRequestService();
            PaymentRequest paymentRequest =
                    paymentRequestService
                            .returnPaymentRequestFromRequestBody(requestBody);

            POSTService postService = new POSTService();
            return postService.postIntent(paymentRequest);
    }

    @PostMapping("/webhook")
    public String webhookEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, "whsec_2ed7f5c58a91f634c4773d70fec9c5c7abb723b60c35485e30ce139871dca754"
            );
        } catch (SignatureVerificationException e) {
            // Invalid signature
            System.out.println("⚠️  Webhook error while validating signature.");
            return "Error";
        }

        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;

        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            System.out.println(" HELLO HELLO this is an error in dataObjectDeserializer if-else");
            return "Error";
        }
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.amount_capturable_updated": {
                System.out.println("message " + " payment_intent.amount_capturable_updated");
                // Then define and call a function to handle the event payment_intent.amount_capturable_updated
                break;
            }
            case "payment_intent.canceled": {
                System.out.println("message " + "payment_intent.canceled");
                // Then define and call a function to handle the event payment_intent.canceled
                break;
            }
            case "payment_intent.created": {
                System.out.println("message " + "payment_intent.created");
                // Then define and call a function to handle the event payment_intent.created
                break;
            }
            case "payment_intent.partially_funded": {
                System.out.println("message " + "payment_intent.partially_funded");
                // Then define and call a function to handle the event payment_intent.partially_funded
                break;
            }
            case "payment_intent.payment_failed": {
                System.out.println("message " + "payment_intent.payment_failed");
                // Then define and call a function to handle the event payment_intent.payment_failed
                break;
            }
            case "payment_intent.processing": {
                System.out.println("message " + "payment_intent.processing");
                // Then define and call a function to handle the event payment_intent.processing
                break;
            }
            case "payment_intent.requires_action": {
                System.out.println("message " + "payment_intent.requires_action");
                // Then define and call a function to handle the event payment_intent.requires_action
                break;
            }
            case "payment_intent.succeeded": {
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                System.out.println("\nPAYMENTINTENT amount: " + paymentIntent.getAmount() +
                        "\nwith id: " + paymentIntent.getId() + "\nsucceeded");
                System.out.println("META DATA!!!!!!!!!!!\n\n" +
                        paymentIntent.getMetadata().toString());

             /*   JSONObject jsonObj = new JSONObject();

                //save to db -> use a method toJsonString() and send to Order API
                //to db saving ENDPOINT
                saveToDB(paymentIntent.getId(), paymentIntent.getAmount(),
                        paymentIntent.getMetadata().get("provider_id"),
                        paymentIntent.getMetadata().get("order_id"),
                        paymentIntent.getCreated());

                jsonObj.put("id", paymentIntent.getId().toString());
                jsonObj.put("amount", paymentIntent.getAmount().toString());
                JSONObject metaJsonObj = new JSONObject();
                System.out.println("jsonObj: " + jsonObject);
                System.out.println("\nmetaJsonObj: " + metaJsonObj);
                metaJsonObj.put("provider_id", paymentIntent.getMetadata().get("provider_id"));
                metaJsonObj.put("order_id", paymentIntent.getMetadata().get("order_id"));
                jsonObj.put("metadata", metaJsonObj);

                String jsonString = jsonObj.toString();
                System.out.println(jsonString);*/
           /*     System.out.println(paymentIntent.getId().toString() +
                        paymentIntent.getAmount().toString()
                        + paymentIntent.getMetadata().toString());*/
               /* JsonObject jsonObject = JsonParser.parseString(paymentIntent.getId().toString() +
                        paymentIntent.getAmount().toString()
                        + paymentIntent.getMetadata().toString()).getAsJsonObject();
                System.out.println("jsonObject id:" + jsonObject.get("id"));*/
                //TODO hur kommunicerar vi vidare med Stripe
                // att vi vill spara ned metadata etc från paymentIntent?
                break;
            }
            case "charge.succeeded": {
                // Then define and call a function to handle the event payment_intent.succeeded
                break;
            }
            // Add cases for other event types as needed
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
        return webPageController.getWebhookPage();
    }

    //todo actual save
    private void saveToDB(String id, Long amount, String provider_id, String order_id, Long created) {
        System.out.println("simulation save to db:\nid " + id + " amount" + amount +
                " provider_id " + provider_id + " order_id " + order_id + " created " + created);
        //also the date, is 'created' enough?
    }
}
