package com.jonfriend.playdatenow_v03b.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
 

@Entity
@Table(name="rsvp")
public class RsvpMdl {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    // begin: entity-specific table fields
    
//    @NotNull (message = "null thang: RSVP status is required.")
    @NotBlank(message="RSVP status is required.")
    private String rsvpStatus;
    
    @NotNull (message = "Minimum one child on the RSVP")
    @Min(value =1, message="Minimum one child on the RSVP.")
    private Integer kidCount;
    
    @NotNull (message = "Minimum one adult on the RSVP")
    @Min(value =1, message="Minimum one adult on the RSVP.")
    private Integer adultCount;
    
    private String comment;
	    
    // end: entity-specific table fields
    
    // start: code for joins
    
    // join user table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="createdby_id")
    private UserMdl userMdl;  
    
    // join playdate table 
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="playdate_id")
	private PlaydateMdl playdateMdl;  

    // end: code for joins
    
    // instantiate the model: 
    public RsvpMdl() {}
    
    // add methods to populate maintain createdAt/UpdatedAt
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    // getters and setters - start

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRsvpStatus() {
		return rsvpStatus;
	}

	public void setRsvpStatus(String rsvpStatus) {
		this.rsvpStatus = rsvpStatus;
	}

	public Integer getKidCount() {
		return kidCount;
	}

	public void setKidCount(Integer kidCount) {
		this.kidCount = kidCount;
	}

	public Integer getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserMdl getUserMdl() {
		return userMdl;
	}

	public void setUserMdl(UserMdl userMdl) {
		this.userMdl = userMdl;
	}

	public PlaydateMdl getPlaydateMdl() {
		return playdateMdl;
	}

	public void setPlaydateMdl(PlaydateMdl playdateMdl) {
		this.playdateMdl = playdateMdl;
	}

    // getters and setters - end
    
// end mdl
}
