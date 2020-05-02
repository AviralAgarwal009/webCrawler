package com.webCrawler.searchEngine.service;

import com.webCrawler.searchEngine.entity.MainTable;

public interface MainTableService {

	public boolean check(String s);

	public void save(MainTable mainTable);
	
}
