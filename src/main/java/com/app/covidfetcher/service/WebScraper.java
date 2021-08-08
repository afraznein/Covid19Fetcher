package com.app.covidfetcher.service;

import com.app.covidfetcher.constants.LambdaAWSConstants;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.IOException;

public class WebScraper {

    public static String tableScrapper() {
        StringBuilder message = new StringBuilder();
        try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            HtmlPage page = webClient.getPage(LambdaAWSConstants.COVID_19_TABLE_URL);
            //String stringHtmlPage = page.asXml();
            //HtmlTable table = page.getElementByName(LambdaAWSConstants.COVID_19_TABLE_CLASS);
            final HtmlTable table = page.getFirstByXPath("//table[@class ='"+LambdaAWSConstants.COVID_19_TABLE_CLASS +"']");
            for (final HtmlTableRow row : table.getRows()) {
                for (final HtmlTableCell cell : row.getCells()) {
                    message.append(cell.asNormalizedText());
                }
                message.append("\n");
            }
            return message.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return message.toString();
        }
    }
}
