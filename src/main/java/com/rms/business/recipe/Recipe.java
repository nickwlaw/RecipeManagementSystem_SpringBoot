package com.rms.business.recipe;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.rms.business.user.User;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User user;
	private String description;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
	@Column(name = "isActive")
	private boolean active;

	public Recipe(int id, String name, User user, String description, LocalDateTime dateCreated,
			LocalDateTime dateUpdated, boolean active) {
		this.id = id;
		this.name = name;
		this.user = user;
		this.description = description;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", user=" + user + ", description=" + description
				+ ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", active=" + active + "]";
	}
	
}
