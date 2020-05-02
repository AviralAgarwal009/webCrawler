package com.webCrawler.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.webCrawler.searchEngine.DAO.NotCrawledDAO;

@Service
public class NotCrawledServiceImpl implements NotCrawledService{
	

	@Autowired
	NotCrawledDAO notCrawledDAO;
	
	@Autowired
	MainTableService mainTableService;
	
	@Override
	@Transactional
	public String deleteURL() {
		String url="";
		try {
			
			url=notCrawledDAO.deleteURL();
		}catch(Exception e) {
			
		}
		return url;
	}
	
	@Override
	@Transactional
	public boolean check(String url) {
		
		return notCrawledDAO.check(url);
	}

	@Override
	@Transactional
	public void save(String a) {
		
		//check whether this url exists in MainTable
		//return true if the url has to be added
		//we also have to check whether this table exists in NotCrawled to handle the duplicate exception
		boolean check1=mainTableService.check(a);
		
		boolean check2=check(a);

		if(check1 && check2) {
			notCrawledDAO.add(a);
		}
	}
		

	/*@Override
	@Transactional
	public String[] stopSites() {
		String[] arr = null;
		return arr;
	}*/

}
