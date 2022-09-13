package com.jonfriend.playdatenow_v03b.models;




import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="stateterritory")
public class StateterritoryMdl {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	// begin entity-specific fields
	
	private String fullName;
    
	private String abbreviation; 
	
	// end entity-specific fields
	
    // begin joins
    
//	join playdate
    @OneToMany(mappedBy="stateterritoryMdl", fetch = FetchType.LAZY)
    private List<PlaydateMdl> playdateList; 

    // end joins
    
    // instantiate the model: 
    public StateterritoryMdl() {}
    // begin: getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public List<PlaydateMdl> getPlaydateList() {
		return playdateList;
	}

	public void setPlaydateList(List<PlaydateMdl> playdateList) {
		this.playdateList = playdateList;
	}

    // end: getters and setters
    
// end mdl
}
