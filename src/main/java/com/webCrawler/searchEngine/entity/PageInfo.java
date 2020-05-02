package com.webCrawler.searchEngine.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "page_data")
public class PageInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_id")
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "heading")
	private String title;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "url")
	private MainTable mainTable;

	public PageInfo() {

	}

	public PageInfo(String description, String title) {
		this.description = description;
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MainTable getMainTable() {
		return mainTable;
	}

	public void setMainTable(MainTable mainTable) {
		this.mainTable = mainTable;
	}

	@Override
	public String toString() {
		return "PageInfo [ description=" + description + ", title=" + title + ", mainTable=" + mainTable + "]";
	}

}
