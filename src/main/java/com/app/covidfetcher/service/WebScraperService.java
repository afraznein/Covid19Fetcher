package com.app.covidfetcher.service;

import com.app.covidfetcher.constants.LambdaAWSConstants;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.joda.time.LocalDate;

import java.io.IOException;

public class WebScraperService {

    public static String parseTable() {
        StringBuilder message = new StringBuilder();
        LocalDate today = LocalDate.now();
        message.append("South Carolina Covid 19 Data for " + today + "\n");
        try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            HtmlPage page = webClient.getPage(LambdaAWSConstants.COVID_19_TABLE_URL);
            final HtmlTable table = page.getFirstByXPath("//table[@class ='" + LambdaAWSConstants.COVID_19_TABLE_CLASS + "']");
            for (final HtmlTableRow row : table.getRows()) {
                for (final HtmlTableCell cell : row.getCells()) {
                    message.append(cell.asNormalizedText());
                    message.append("\t");
                }
                message.append("\n");
            }
            return message.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return message.toString();
        }
    }
}
