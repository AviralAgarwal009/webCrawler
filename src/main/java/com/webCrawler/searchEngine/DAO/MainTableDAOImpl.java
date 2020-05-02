package com.webCrawler.searchEngine.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCrawler.searchEngine.entity.MainTable;

@Repository
public class MainTableDAOImpl implements MainTableDAO{

	
	@Autowired
	EntityManager entityManager;
	@Override
	public boolean check(String s) {
		
		
		
		Session session=entityManager.unwrap(Session.class);
		Query<MainTable> q=session.createQuery("from MainTable where url =:s",MainTable.class);
		q.setParameter("s", s);
		
		List<MainTable> l=q.getResultList();
		if(l.isEmpty()) {
			return true;//if does not contain then return true so that link can be added
		}
	
		return false;
	}
	@Override
	public void save(MainTable mainTable) {
		
		System.out.println("Inside Main Table Dao save methoda called to save page info portion");
		Session session=entityManager.unwrap(Session.class);
		session.save(mainTable);
		System.out.println(mainTable.getUrlId());
		
	}

}
