import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.app.covidfetcher.handler.Covid19Handler;
import com.app.covidfetcher.service.WebScraper;

public class Main {
    public static void main(String[] args){
        String s = WebScraper.tableScrapper();
    }
}
