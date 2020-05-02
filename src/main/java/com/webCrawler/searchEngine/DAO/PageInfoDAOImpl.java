package com.webCrawler.searchEngine.DAO;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCrawler.searchEngine.entity.MainTable;
import com.webCrawler.searchEngine.entity.PageInfo;


@Repository
public class PageInfoDAOImpl implements PageInfoDAO{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public int save(PageInfo pageInfo) {
		
		Session session=entityManager.unwrap(Session.class);
		session.save(pageInfo);

		return pageInfo.getMainTable().getUrlId();
	}

}
