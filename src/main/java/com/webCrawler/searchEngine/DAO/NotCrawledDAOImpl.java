package com.webCrawler.searchEngine.DAO;
import java.util.List; 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCrawler.searchEngine.entity.MainTable;
import com.webCrawler.searchEngine.entity.NotCrawled;

@Repository
public class NotCrawledDAOImpl implements NotCrawledDAO{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public String deleteURL() {

		Session session = entityManager.unwrap(Session.class);
		String url;

		String deleteQuery=stopSites();
		Query q3 = session.createQuery(deleteQuery);
		q3.executeUpdate();//to delete all pages that are not required
		
		
		String dataq = "from NotCrawled where id = (select min(id) from NotCrawled)";
		Query<NotCrawled> q = session.createQuery(dataq, NotCrawled.class);

		q.setMaxResults(1);
		List<NotCrawled> l = q.getResultList();

		url = l.get(0).getUrl();
		int uid = l.get(0).getId();// get the id of the first record

		Query q2 = session.createQuery("delete from NotCrawled where id =:uId");
		q2.setParameter("uId", uid);
		
		q2.executeUpdate();// delete the first record
		
		return url;

	}

	@Override
	public void add(String a) {
		Session session=entityManager.unwrap(Session.class);
		
		NotCrawled n=new NotCrawled(a);
		session.saveOrUpdate(n);
		
	}

	@Override
	public boolean check(String url) {
		// TODO Auto-generated method stub
		
		Session session=entityManager.unwrap(Session.class);
		Query<NotCrawled> q=session.createQuery("from NotCrawled where url =:s",NotCrawled.class);
		q.setParameter("s", url);
		
		List<NotCrawled> l=q.getResultList();
		if(l.isEmpty()) {
			return true;//if does not contain then return true so that link can be added
		}
	
		return false;
	}

	@Override
	public String stopSites() {

		String query="";
		try
		{
			BufferedReader br=new BufferedReader(new FileReader("blockedSites.txt"));
			StringBuilder sb=new StringBuilder();
			int count=0;
			String t;
			while((t=br.readLine())!=null){
			    count++;
			    sb.append(t+"\n");            
			}
			br.close();
			String[] sites=sb.toString().split("\n");
			query="delete from NotCrawled where url";
			int i=0;
			for( i=0;i<count-1;i++){
			    
			    query=query+" like '%"+sites[i]+"%' or url";
			}
			query=query+" like '%"+sites[i]+"%'";
			System.out.println(query);
		}catch(Exception e)
		{
			System.out.println("Exception inside not crawled stop sites");
		}
		
		return query;
	}

}
