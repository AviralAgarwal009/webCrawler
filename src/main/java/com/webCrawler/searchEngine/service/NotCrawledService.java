package com.webCrawler.searchEngine.service;

public interface NotCrawledService {

	public String deleteURL();

	public void save(String a);

	public boolean check(String url);


}
