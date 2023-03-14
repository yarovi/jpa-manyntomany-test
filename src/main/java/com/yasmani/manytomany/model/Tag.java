package com.yasmani.manytomany.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tags")
public class Tag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch =FetchType.LAZY,
			cascade= {
			CascadeType.PERSIST,
			CascadeType.MERGE
	},
			mappedBy="tags")

	@JsonIgnore
	private Set<Tutorial> tutorials = new HashSet<>();
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Tutorial> getTutorials() {
		return tutorials;
	}
	public void setTutorials(Set<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}
	
	
	

}
