package com.webCrawler.searchEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.webCrawler.searchEngine.crawler.CrawlerStarter;

@SpringBootApplication(scanBasePackages = { "com.webCrawler.searchEngine" })
public class SearchEngineApplication implements CommandLineRunner{

	@Autowired
	public CrawlerStarter crawlerStarter;
	
	public static void main(String[] args) {
		
		SpringApplication.run(SearchEngineApplication.class, args);
		
		System.out.println("Hello Worlds : Springboot Application(Cralwer) has started");
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		crawlerStarter.start();
		
	}

}
