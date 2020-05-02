package com.webCrawler.searchEngine.DAO;

import com.webCrawler.searchEngine.entity.CrawledProperties;
import com.webCrawler.searchEngine.entity.MainTable;

public interface CrawledPropertiesDAO {

	public void save(CrawledProperties cw,int id);
}
