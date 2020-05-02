package com.webCrawler.searchEngine.DAO;

import com.webCrawler.searchEngine.entity.MainTable;

public interface MainTableDAO {
	
	public boolean check(String s);

	public void save(MainTable mainTable);


}
