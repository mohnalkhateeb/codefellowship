package com.lab16.codefellowship.models;

import javax.persistence.*;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  
  @Column(columnDefinition = "text")
  private String body;
  
  private String createdAt;
  
  @ManyToOne
  private ApplicationUser applicationUser;
  
  public Post() {}
  
  public Post(String body, String createdAt, ApplicationUser applicationUser) {
    this.body = body;
    this.createdAt = createdAt;
    this.applicationUser = applicationUser;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public String getBody () {
    return body;
  }
  
  public String getCreatedAt () {
    return createdAt;
  }
  
  public ApplicationUser getApplicationUser () {
    return applicationUser;
  }
}
