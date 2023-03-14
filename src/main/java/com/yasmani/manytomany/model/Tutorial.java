package com.yasmani.manytomany.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tutorials")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="published")
	private boolean published;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade= {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name="tutorial_tags",
	joinColumns= {@JoinColumn(name="tutorial_id")},
	inverseJoinColumns= {@JoinColumn(name="tag_id")})
	
	private Set<Tag> tags = new HashSet<>();
	
	public Tutorial() {
		
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isPublished() {
		return published;
	}


	public void setPublished(boolean published) {
		this.published = published;
	}


	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	public Tutorial( String title, String description, boolean published) {
		super();
		this.title = title;
		this.description = description;
		this.published = published;
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.getTutorials().add(this);
	}
	
	public void removeTag(long tagId) {
		Tag tag = this.tags.stream().filter(t-> t.getId()==tagId).findFirst().orElse(null);
		if(tag!=null) {
			this.tags.remove(tag);
			tag.getTutorials().remove(this);
		}
	}
	
}
