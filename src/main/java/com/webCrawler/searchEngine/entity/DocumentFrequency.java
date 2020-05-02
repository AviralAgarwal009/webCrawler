package com.webCrawler.searchEngine.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inverse_doc_fre")
public class DocumentFrequency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_id")
	private int id;
	
	@Column(name = "words")
	private String words;

	@Column(name = "occurrence")
	private int occurrence;

	public DocumentFrequency() {

	}

	public DocumentFrequency(String words, int occurrence) {
		this.words = words;
		this.occurrence = occurrence;
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

	public int getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(int occurrence) {
		this.occurrence = occurrence;
	}

	@Override
	public String toString() {
		return "DocumentFrequency [id=" + id + ", words=" + words + ", occurrence=" + occurrence + "]";
	}

	 

}
