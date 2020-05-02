package com.webCrawler.searchEngine.DAO;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCrawler.searchEngine.entity.CrawledProperties;
import com.webCrawler.searchEngine.entity.MainTable;


@Repository
public class CrawledPropertiesDAOImpl implements CrawledPropertiesDAO{

	@Autowired
	EntityManager entityManager;
	@Override
	public void save(CrawledProperties cw,int id) {
		
		System.out.println("inside crawled properties dao to save crawled properties associted with main table");
		Session session=entityManager.unwrap(Session.class);
		
		System.out.println(cw.getWords());
		MainTable mainTable=session.get(MainTable.class, id);
		mainTable.add(cw);
		session.save(cw);
		
		System.out.println("Saved");
		
	}

}
