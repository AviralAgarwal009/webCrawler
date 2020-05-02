package com.webCrawler.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webCrawler.searchEngine.DAO.MainTableDAO;
import com.webCrawler.searchEngine.entity.MainTable;

@Service
public class MainTableServiceImpl implements MainTableService {

	@Autowired
	private MainTableDAO mainTableDAO;
	
	@Override
	@Transactional
	public boolean check(String s) {
		
		boolean check=mainTableDAO.check(s);
		return check;
	}

	@Override
	@Transactional
	public void save(MainTable mainTable) {
		
		mainTableDAO.save(mainTable);
		
	}


}
