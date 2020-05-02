package com.webCrawler.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webCrawler.searchEngine.DAO.CrawledPropertiesDAO;
import com.webCrawler.searchEngine.entity.CrawledProperties;
import com.webCrawler.searchEngine.entity.MainTable;

@Service
public class CrawledPropertiesServiceImpl implements CrawledPropertiesService {

	@Autowired
	private CrawledPropertiesDAO crawledPropertiesDAO;
	
	@Override
	@Transactional
	public void save(CrawledProperties cw,int id) {
		
		crawledPropertiesDAO.save(cw,id);
		
	}

}
