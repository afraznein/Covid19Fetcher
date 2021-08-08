package com.app.covidfetcher.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.app.covidfetcher.service.EmailService;
import com.app.covidfetcher.service.WebScraper;

public class Covid19Handler implements RequestHandler<ScheduledEvent, String> {

    @Override
    public String handleRequest(ScheduledEvent input, Context context) {
        //Get Covid Table Data
        String message = WebScraper.tableScrapper();
        //Email result
        EmailService.sendCovid19Email(message);
        return null;
    }
}
