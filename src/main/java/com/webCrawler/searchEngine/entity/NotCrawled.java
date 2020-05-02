package com.webCrawler.searchEngine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "left_crawled")
public class NotCrawled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crawled_id")
	private int id;

	@Column(name = "url")
	private String url;

	public NotCrawled() {
	}

	public NotCrawled(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "NotCrawled [id=" + id + ", url=" + url + "]";
	}

}
