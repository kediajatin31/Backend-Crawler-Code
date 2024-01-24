package com.example.webCrawler;

import org.apache.tomcat.util.http.parser.MediaType;
//import org.example.Crawler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


@RestController
public class CrawlerController {

//	@RequestMapping("/crawl")
    @ResponseBody
    @RequestMapping(value = "/crawl", produces = "text/plain")
    public String crawlAPI() {
        String url = "https://wikipedia.org/";
        TreeMap<String, List<String>> treeMap = new TreeMap<>();
        ArrayList<String> visitedUrls = new ArrayList<>();
        Crawler.crawl(1, url, "", treeMap, visitedUrls);  // Use the class name to call the static method
        // Use Gson library for pretty printing JSON
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(treeMap);

        // Print the formatted JSON to the console (optional)
//        System.out.println(jsonOutput);
        
        return jsonOutput;
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

