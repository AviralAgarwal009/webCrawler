package com.webCrawler.searchEngine.DAO;

public interface NotCrawledDAO {

	public String deleteURL();

	public void add(String a);
	public boolean check(String url);
	public String stopSites();
}
