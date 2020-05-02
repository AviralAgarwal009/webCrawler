package com.webCrawler.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webCrawler.searchEngine.DAO.DocumentFrequencyDAO;

@Service
public class DocumentFrequencyServiceImpl implements DocumentFrequencyService{

	@Autowired
	DocumentFrequencyDAO documentFrequencyDAO;
	@Override
	@Transactional
	public void add(String word) {
		
		documentFrequencyDAO.add(word);
		
	}

}
