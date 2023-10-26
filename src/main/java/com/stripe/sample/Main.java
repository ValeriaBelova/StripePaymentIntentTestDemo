package com.stripe.sample;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.port;
import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        //todo remove after testing / break out
      /*  Stripe.apiKey = "sk_test_51O4f1QCTYTgJy1iwwrbAL6pCRpD0mU5R9ss6nkg3szjKEVMhDIvXmFh0PRr8VgfIGoVy66Jkyd343ubhi8HprhIp00tNjmuRRY";
        // Replace this endpoint secret with your endpoint's unique secret
        // If you are testing with the CLI, find the secret by running 'stripe listen'
        // If you are using an endpoint defined with the API or dashboard, look in your webhook settings
        // at https://dashboard.stripe.com/webhooks
        String endpointSecret = "whsec_2ed7f5c58a91f634c4773d70fec9c5c7abb723b60c35485e30ce139871dca754";

        post("/webhook", (request, response) -> {
            String payload = request.body();
            Event event = null;

            try {
                event = ApiResource.GSON.fromJson(payload, Event.class);
            } catch (JsonSyntaxException e) {
                // Invalid payload
                System.out.println("⚠️  Webhook error while parsing basic request.");
                response.status(400);
                return "";
            }
            String sigHeader = request.headers("Stripe-Signature");
            if(endpointSecret != null && sigHeader != null) {
                // Only verify the event if you have an endpoint secret defined.
                // Otherwise use the basic event deserialized with GSON.
                try {
                    event = Webhook.constructEvent(
                            payload, sigHeader, endpointSecret
                    );
                } catch (SignatureVerificationException e) {
                    // Invalid signature
                    System.out.println("⚠️  Webhook error while validating signature.");
                    response.status(400);
                    return "";
                }
            }
            // Deserialize the nested object inside the event
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
            } else {
                // Deserialization failed, probably due to an API version mismatch.
                // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
                // instructions on how to handle this case, or return an error here.
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
                    System.out.println("message " + "payment_intent.succeeded");
                    response.status(200);
                    // Then define and call a function to handle the event payment_intent.succeeded
                    break;
                }
                // ... handle other event types
                default:
                    System.out.println("Unhandled event type: " + event.getType());
            }
            response.status(200);
            return "";
        });*/
    }
}
