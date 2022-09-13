package com.jonfriend.playdatenow_v03b.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn; // JRF manually adding
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="playdate")
public class PlaydateMdl {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	// begin: entity-specific table fields
	@NotBlank(message="Playdate Status required.")
	private String eventStatus; 
	
	private String eventName;
	
	@NotBlank(message="Location Name required.")
	private String locationName;
	
	@NotBlank(message="Location Address required.")
	private String locationAddy;
	
//	@Transient
	@NotNull(message="Date is required.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date eventDate;

	@NotBlank(message="Start Time required.")
	private String startTimeTxt; 
	
	private String eventDescription;

	@NotNull (message = "Max Kids Count must be 1 or greater.")
    @Min(value =1, message="Max Kids Count must be 1 or greater.")
	private Integer maxCountKids; 

//	@NotNull (message = "Max Adults Count must be 1 or greater.")
//    @Min(value =1, message="Max Adults Count must be 1 or greater.")
	// Aug deployment note: presently not incorporating this element, for biz reasons. 
	private Integer maxCountAdults; 
	
    // end: entity-specific table fields
    
    // start: code for joins
    
    // join user table
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="createdby_id")
	private UserMdl userMdl;  
	
    // join rsvp table
    @OneToMany(mappedBy="playdateMdl", fetch = FetchType.LAZY)
    private List<RsvpMdl> rsvpList; 
    

	// join stateterritory table
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="stateterritory_id")
	private StateterritoryMdl stateterritoryMdl;
	
    // instantiate the model: 
    public PlaydateMdl() {}
    
    // add methods to populate maintain createdAt/UpdatedAt
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    // begin: getters and setters

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

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationAddy() {
		return locationAddy;
	}

	public void setLocationAddy(String locationAddy) {
		this.locationAddy = locationAddy;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getStartTimeTxt() {
		return startTimeTxt;
	}

	public void setStartTimeTxt(String startTimeTxt) {
		this.startTimeTxt = startTimeTxt;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Integer getMaxCountKids() {
		return maxCountKids;
	}

	public void setMaxCountKids(Integer maxCountKids) {
		this.maxCountKids = maxCountKids;
	}

	public Integer getMaxCountAdults() {
		return maxCountAdults;
	}

	public void setMaxCountAdults(Integer maxCountAdults) {
		this.maxCountAdults = maxCountAdults;
	}

	public UserMdl getUserMdl() {
		return userMdl;
	}

	public void setUserMdl(UserMdl userMdl) {
		this.userMdl = userMdl;
	}

	public List<RsvpMdl> getRsvpList() {
		return rsvpList;
	}

	public void setRsvpList(List<RsvpMdl> rsvpList) {
		this.rsvpList = rsvpList;
	}

	public StateterritoryMdl getStateterritoryMdl() {
		return stateterritoryMdl;
	}

	public void setStateterritoryMdl(StateterritoryMdl stateterritoryMdl) {
		this.stateterritoryMdl = stateterritoryMdl;
	}

    // end: getters and setters
    
// end mdl
}
