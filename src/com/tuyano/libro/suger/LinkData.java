package com.tuyano.libro.suger;

import java.net.URL;
import java.util.Date;
 
import javax.jdo.annotations.*;
 
@PersistenceCapable(identityType = IdentityType.APPLICATION)


public class LinkData {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
     
    @Persistent
    private String title;
     
    @Persistent
    private String url;
     
    @Persistent
    private String comment;
     
    @Persistent
    private Date datetime;
 
    public LinkData(String title, String url, String comment, Date datetime) {
        super();
        this.title = title;
        this.url = url;
        this.comment = comment;
        this.datetime = datetime;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getUrl() {
        return url;
    }
 
    public void setUrl(String url) {
        this.url = url;
    }
 
    public String getComment() {
        return comment;
    }
 
    public void setComment(String comment) {
        this.comment = comment;
    }
 
    public Date getDatetime() {
        return datetime;
    }
 
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
