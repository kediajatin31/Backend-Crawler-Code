package com.example.webCrawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crawler {
	
	static void crawl(int level, String url, String parentTitle, TreeMap<String, List<String>> treeMap, ArrayList<String> visitedUrls) {
	   
		if (level <= 5) {
	        Document doc = request(url, visitedUrls);

	        if (doc != null) {
	            String title = doc.title();
	            treeMap.putIfAbsent(title, new ArrayList<>());

	            if (parentTitle != null && !parentTitle.isEmpty()) {
	                treeMap.get(parentTitle).add("Link: " + url);
	            }

	            for (Element link : doc.select("a[href]")) {
	                String nextUrl = link.absUrl("href").trim();
	                if (!visitedUrls.contains(nextUrl) && !nextUrl.isEmpty()) {
	                    crawl(++level, nextUrl, title, treeMap, visitedUrls);
	                }
	            }
	        }
	    }
	}


	private static Document request(String url, ArrayList<String> visitedUrls) {
	    try {
	        Connection.Response response = Jsoup.connect(url)
	                .ignoreContentType(true)  // Ignore unsupported MIME types
	                .execute();

	        if (response.statusCode() == 200) {
	            visitedUrls.add(url);
	            return response.parse();
	        } else {
	            System.out.println("Error fetching URL: " + url);
	        }
	    } catch (IOException e) {
	        System.out.println("Error fetching URL: " + url);
	    }

	    return null;
	}


    static void printTreemap(TreeMap<String, List<String>> treeMap, String prefix) {
        for (Map.Entry<String, List<String>> entry : treeMap.entrySet()) {
            System.out.println(prefix + "Title: " + entry.getKey());

            // Print links
            for (String link : entry.getValue()) {
                System.out.println(prefix + "  " + link);
            }

            // Recursively print children
            TreeMap<String, List<String>> childMap = new TreeMap<>();
            for (String link : entry.getValue()) {
                String childLink = link.replace("Link: ", "");
                childMap.put(childLink, new ArrayList<>());
                printTreemap(childMap, prefix + "    ");
            }
        }
    }

    
}
