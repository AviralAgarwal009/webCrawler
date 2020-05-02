package com.webCrawler.searchEngine.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCrawler.searchEngine.entity.DocumentFrequency;
import com.webCrawler.searchEngine.entity.NotCrawled;

@Repository
public class DocumentFrequencyDAOImpl implements DocumentFrequencyDAO{

	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public void add(String word) {
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<DocumentFrequency> q=session.createQuery("from DocumentFrequency where words=:word");
		q.setParameter("word", word);
		List<DocumentFrequency> l=q.getResultList();
		if(l.isEmpty()) {
			
			DocumentFrequency d=new DocumentFrequency(word, 1);
			session.save(d);
		}
		else {
			
			int number=l.get(0).getOccurrence();
			DocumentFrequency d=l.get(0);
			d.setOccurrence(number+1);
			session.saveOrUpdate(d);
		}
		
		
		
	}
	
	

}
