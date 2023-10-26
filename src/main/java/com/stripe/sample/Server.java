package com.stripe.sample;
/*
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.port;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.sample.Models.PaymentRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
@SpringBootApplication
public class Server {
  private static Gson gson = new Gson();

  static class CreatePaymentResponse1 {
    private String clientSecret;

    public CreatePaymentResponse1(String clientSecret) {
      this.clientSecret = clientSecret;
    }
  }
  static class CreatePaymentItem {
    @SerializedName("id")
    String id;

    public String getId() {
      return id;
    }
  }

  static class CreatePayment {
    @SerializedName("items")
    CreatePaymentItem[] items;

    public CreatePaymentItem[] getItems() {
      return items;
    }
  }


  static int calculateOrderAmount(Object[] items) {
    int finalAmount = 0;
    for (Object item: items) {
      //finalAmount+=item.getAmount();
    }
    // Replace this constant with a calculation of the order's amount
    // Calculate the order total on the server to prevent
    // people from directly manipulating the amount on the client
    return 1400;
  }

  public static void main(String[] args) {
    SpringApplication.run(Server.class, args);
   /* port(4242);
    staticFiles.externalLocation(Paths.get("public").toAbsolutePath().toString());

    // This is your test secret API key.
    Stripe.apiKey = "sk_test_51O4f1QCTYTgJy1iwwrbAL6pCRpD0mU5R9ss6nkg3szjKEVMhDIvXmFh0PRr8VgfIGoVy66Jkyd343ubhi8HprhIp00tNjmuRRY";

    post("/create-payment-intent1", (request, response) -> {
      response.type("application/json");
      CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);

      PaymentIntentCreateParams params =
              PaymentIntentCreateParams.builder()
                      .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
                      .setCurrency("sek")
                      // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
                      .setAutomaticPaymentMethods(
                              PaymentIntentCreateParams.AutomaticPaymentMethods
                                      .builder()
                                      .setEnabled(true)
                                      .build()
                      )
                      .build();

      // Create a PaymentIntent with the order amount and currency
      PaymentIntent paymentIntent = PaymentIntent.create(params);

      CreatePaymentResponse1 paymentResponse = new CreatePaymentResponse1(paymentIntent.getClientSecret());
      return gson.toJson(paymentResponse);
    });
  }
}*/