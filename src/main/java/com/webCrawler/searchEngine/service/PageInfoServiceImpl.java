package com.webCrawler.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webCrawler.searchEngine.DAO.PageInfoDAO;
import com.webCrawler.searchEngine.entity.MainTable;
import com.webCrawler.searchEngine.entity.PageInfo;


@Service
public class PageInfoServiceImpl implements PageInfoService{

	@Autowired
	PageInfoDAO pageInfoDAO;
	
	@Override
	@Transactional
	public int save(PageInfo pageInfo) {
		
		return pageInfoDAO.save(pageInfo);
	}

}
