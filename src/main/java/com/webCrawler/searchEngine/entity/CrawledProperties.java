package com.webCrawler.searchEngine.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "crawled_prop")
public class CrawledProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prop_id")
	private int id;

	@Column(name = "words")
	private String words;

	@Column(name = "score")
	private int score;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "url_ref")
	private MainTable mainTable;

	public CrawledProperties() {

	}

	public CrawledProperties(String words, int score) {
		this.words = words;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public MainTable getMainTable() {
		return mainTable;
	}

	public void setMainTable(MainTable mainTable) {
		this.mainTable = mainTable;
	}

	@Override
	public String toString() {
		return "CrawledProperties [id=" + id + ", words=" + words + ", score=" + score + "]";
	}

}