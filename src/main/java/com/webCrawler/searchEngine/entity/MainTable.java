package com.webCrawler.searchEngine.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "crawled_pages")
public class MainTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "url_id")
	private int urlId;

	@Column(name = "url")
	private String url;

	@Column(name = "word_count")
	private int wordCount;

	@OneToMany(mappedBy = "mainTable", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<CrawledProperties> crawledProperties;

	@OneToOne(mappedBy = "mainTable", cascade = { CascadeType.ALL })
	private PageInfo pageInfo;

	public MainTable() {

	}

	public MainTable(String url, int wordCount) {
		this.url = url;
		this.wordCount = wordCount;
	}

	public int getUrlId() {
		return urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	@Override
	public String toString() {
		return "MainTable [urlId=" + urlId + ", url=" + url + ", wordCount=" + wordCount + "]";
	}

	public List<CrawledProperties> getCrawledProperties() {
		return crawledProperties;
	}

	public void setCrawledProperties(List<CrawledProperties> crawledProperties) {
		this.crawledProperties = crawledProperties;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public void add(CrawledProperties crawledprop) {
		if (crawledProperties == null) {
			crawledProperties = new ArrayList<>();
		}

		crawledProperties.add(crawledprop);

		crawledprop.setMainTable(this);
	}

}
