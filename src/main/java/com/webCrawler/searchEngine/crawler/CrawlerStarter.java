package com.webCrawler.searchEngine.crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.webCrawler.searchEngine.Lemmatizer.Lemmatizer;
import com.webCrawler.searchEngine.entity.CrawledProperties;
import com.webCrawler.searchEngine.entity.MainTable;
import com.webCrawler.searchEngine.entity.PageInfo;
import com.webCrawler.searchEngine.service.CrawledPropertiesService;
import com.webCrawler.searchEngine.service.DocumentFrequencyService;
import com.webCrawler.searchEngine.service.NotCrawledService;
import com.webCrawler.searchEngine.service.PageInfoService;

@Component
public class CrawlerStarter {

	Map<String,Integer> hm;

	private NotCrawledService notCrawledService;

	public CrawlerStarter() {

	}

	@Autowired
	public CrawlerStarter(NotCrawledService ncs) {

		notCrawledService = ncs;
	}

	@Autowired
	private CrawledPropertiesService crawledPropertiesService;
	
	
	@Autowired
	private PageInfoService pageServiceInfo;
	
	@Autowired
	private DocumentFrequencyService documentFrequencyService;
	
	
	public void SaveInfromation(String url,String pageInfo,String title,int wordCount) {
		
		
		System.out.println(url);
		
		if(pageInfo.length()>10000)
		{
			pageInfo=pageInfo.substring(0, 9998);
		}
		
		if(title.length()>500) {
			title=title.substring(0, 498);
		}
		
		MainTable mainTable=new MainTable(url, wordCount);
		PageInfo p=new PageInfo(pageInfo, title);
		
		p.setMainTable(mainTable);
		
		int id=pageServiceInfo.save(p);

		CrawledProperties cw;
		
		 for (Map.Entry<String,Integer> entry : hm.entrySet()){
			 
			 if(entry.getKey().length()<30)
			 {
				 
				 cw=new CrawledProperties(entry.getKey(), entry.getValue());
				 crawledPropertiesService.save(cw,id);
				 documentFrequencyService.add(entry.getKey());
				 
			 }
	    } 
		 
		 //inverse document frequency is yet to be updated;
		System.out.println("All data is stored");
		

	}
	
	public void start() {
		while(true) {   //condition will be added later after making signin page for the admin
			
			startCrawling();
		}
	}
	
	public boolean technicalChooser() {
		
		BufferedReader br=null;
		boolean result=false;
		try {
			
			br=new BufferedReader(new FileReader("stopWords.txt"));//path of text file of words
			StringBuilder sb=new StringBuilder();
			String t;
			while((t=br.readLine())!=null) {
				
				sb.append(t+"\n");
			}
			String[] words=sb.toString().split("\n");
			int wordCount=0;
			for(int i=0;i<words.length;i++) {
				if(hm.containsKey(words[i])) {
					wordCount++;
				}
				
			}
			
			if(wordCount>=3) {
				result=true;
			}
			
			
		}catch(Exception e) {
			System.out.println("Exception inside technicalChooser method");
		}
		return result;
	}
	
	public void startCrawling() {
		
		//iteration left
		hm=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		System.out.println("Hello inside crawler starter");
		String url;// it will take a url from not crawled entity
		url = notCrawledService.deleteURL();// gets the url to crawl and delete that url from the database

		System.out.println(url);
		// now crawl that url
		URLConnection u = null;
		try {

			u = new URL(url).openConnection();
			u.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			u.setReadTimeout(10000);
			u.connect();

			BufferedReader br = new BufferedReader(new InputStreamReader(u.getInputStream(), Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String t;
			while ((t = br.readLine()) != null) {

				sb.append(t);

			}
			// parse the html code
			Document doc = Jsoup.parse(sb.toString());
			
			
			


			// get all the descriptions (meta:keywords , meta:description , headings ,
			// paragraph , title , div ,
			String description = "";
			try
			{
				
		
				Elements elements = doc.select("meta[name=description]");
				for (Element e : elements) {
	
					if (!(e.attr("content").equals("")) && !(e.attr("content").equals("\n"))
							&& !(e.attr("content").equals(" "))) {
	
						description = description + e.attr("content") + "\n";
					}
	
				}
			}catch(Exception e) {
				System.out.println("Inside description Jsoup");
			}

			String keywords = "";
			try
			{
				
				Elements elements = doc.select("meta[name=keywords]");
				for (Element e : elements) {
	
					if (!(e.attr("content").equals("")) && !(e.attr("content").equals("\n"))
							&& !(e.attr("content").equals(" "))) {
	
						keywords = keywords + e.attr("content") + "\n";
					}
	
				}
			}catch(Exception e) {
				System.out.println("Inside keywords Jsoup");
			}
			

			String heading = "";
			try
			{
				
			
				Elements elements = doc.select("h1");
				for (Element e : elements) {
	
					heading = heading + e.ownText() + " ";
				}
	
				elements = doc.select("h2");
				for (Element e : elements) {
	
					heading = heading + e.ownText() + " ";
				}
	
				elements = doc.select("h3");
				for (Element e : elements) {
	
					heading = heading + e.ownText() + " ";
				}
	
				elements = doc.select("h4");
				for (Element e : elements) {
	
					heading = heading + e.ownText() + " ";
				}
	
				elements = doc.select("h5");
				for (Element e : elements) {
	
					heading = heading + e.ownText() + " ";
				}
	
				elements = doc.select("h6");
				for (Element e : elements) {
	
					heading = heading + e.ownText() + " ";
				}
			}catch(Exception e) {
				System.out.println("Inside Heading Jsoup");
			}
			
			
			String title="";
			try {
				
			Elements elements = doc.select("title");
			title = elements.get(0).ownText();
			}catch(Exception e)
			{
				System.out.println("Title Exception");
			}

			String div = "";
			
			try
			{
				Elements elements = doc.select("div");
			
			
				for (Element e : elements) {
					div = div + e.ownText() + "  \n";//"changeParagraphForSearchEngine ";// to identify the change in paragraph to
																				// show the description of the link
	
				}
			}catch(Exception e) {
				System.out.println("Inside div Jsoup");
			}

			String p = "";
			try
			{
				Elements elements = doc.select("p");
			
			
			for (Element e : elements) {

				p = p + e.ownText() +"  \n";//+ "changeParagraphForSearchEngine ";// to identify the change in paragraph to show
																		// description of the link
			}
			}catch(Exception e) {
				System.out.println("Inside p Jsoup");
			}


			
			
			//convert each word using lemmatization
			//convert each technical word using lemmatizer as well
			//check using allWords whether a website is technical or not ---if not technical return 
			//functionality is yet to be added
			
			System.out.println(div+"\n"+p+"\n"+title+"\n"+heading);
			
			
			System.out.println("Using lemmatizer");
			Lemmatizer lemma=new Lemmatizer();
			
			String titleWords[]=title.split("\\W+");
			String titleLemma[]=lemma.wordLem(titleWords);
			
			String headingWords[]=heading.split("\\W+");
			String headingLemma[]=lemma.wordLem(headingWords);
			
			String descriptionWords[]=description.split("\\W+");
			String descriptionLemma[]=lemma.wordLem(descriptionWords);
			
			String keywordsWords[]=keywords.split("\\W+");
			String keywordsLemma[]=lemma.wordLem(keywordsWords);
			
			String pWords[]=p.split("\\W+");
			String pLemma[]=lemma.wordLem(pWords);
			
			String divWords[]=div.split("\\W+");
			String divLemma[]=lemma.wordLem(divWords);
			
			int length=titleWords.length+headingWords.length+descriptionWords.length+keywordsWords.length+pWords.length+divWords.length;
			for(String w:titleLemma) {
				if(hm.containsKey(w)) {
					int v=hm.get(w);
					v=v+80;
					hm.put(w, v);
				}
				else {
					hm.put(w, 80);
				}
			}
			
			for(String w:headingLemma) {
				if(hm.containsKey(w)) {
					int v=hm.get(w);
					v=v+40;
					hm.put(w, v);
				}
				else {
					hm.put(w, 40);
				}
			}
			
			for(String w:descriptionLemma) {
				if(hm.containsKey(w)) {
					int v=hm.get(w);
					v=v+30;
					hm.put(w, v);
				}
				else {
					hm.put(w, 30);
				}
			}
			
			for(String w:keywordsLemma) {
				if(hm.containsKey(w)) {
					int v=hm.get(w);
					v=v+40;
					hm.put(w, v);
				}
				else {
					hm.put(w, 40);
				}
			}
			
			for(String w:divLemma) {
				if(hm.containsKey(w)) {
					int v=hm.get(w);
					v=v+2;
					hm.put(w, v);
				}
				else {
					hm.put(w, 2);
				}
			}
			
			for(String w:pLemma) {
				if(hm.containsKey(w)) {
					int v=hm.get(w);
					v=v+2;
					hm.put(w, v);
				}
				else {
					hm.put(w, 2);
				}
			}
			
			//now check hm against stored database of technical words to decide whether current webiste is technical or not 
			boolean check=technicalChooser();
			if(!check)
			{
				System.out.println("Not saved because not technical website");
				return;//if false that means current website is not technical
			}
			
			//all the words of the current url is stored in the hm hashmap now to save them in the database call saveInfromation
			String pageInfo=p+div;
			
			
			
			
			
			//save the data to the database
			SaveInfromation(url,pageInfo,heading,length);
			
			
			
			//save all the anchors in the NotCrawled (check MainTable to check if the link already exist)
			Elements elements=doc.select("a");
			String a="";
			for(Element e:elements) {
				a="";
				String l=e.attr("href");
				if(l.length()>2) {
					
					if(l.charAt(0)=='/'&&l.charAt(1)!='/') {
						a="https:/" + u.getURL().getHost()+l;
					}
					else if(l.charAt(0)=='h') {
						a=l;

					}

				}
				if (!a.equals("")) {

					if(a.charAt(7)!='/')
				       {
				           
				           a=a.substring(0,7)+"/"+a.substring(7);
				       }
					notCrawledService.save(a);
				}

			}

		} catch (Exception e) {

			System.out.println("Exception inside CrawlerStarter:  " + e.getMessage());
			e.printStackTrace();

		}

	}

}
