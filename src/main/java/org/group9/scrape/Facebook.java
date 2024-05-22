package org.group9.scrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Facebook {

    public static void main(String[] args) {
        String url = "https://www.facebook.com/profile.php?id=100088682591863";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements postLinks = doc.select("a[href^='/posts/']");

            for (Element link : postLinks) {
                String postLink = link.absUrl("href");
                System.out.println(postLink);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

