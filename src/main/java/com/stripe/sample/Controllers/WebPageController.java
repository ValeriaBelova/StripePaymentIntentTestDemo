package com.stripe.sample.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    @GetMapping("/webhookPage")
    public String getWebhookPage(){
        return "webhookpage.html";
    }
}
