package com.jonfriend.playdatenow_v03b.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.jonfriend.playdatenow_v03b.models.StateterritoryMdl;
import com.jonfriend.playdatenow_v03b.repositories.StateterritoryRpo;

@Service
public class StateterritorySrv {
	
	// adding the playdate repository as a dependency
	private final StateterritoryRpo stateterritoryRpo;
	
	public StateterritorySrv(StateterritoryRpo stateterritoryRpo) {this.stateterritoryRpo = stateterritoryRpo;}

	
	// returns all stateterritory 
	public List<StateterritoryMdl> returnAll(){
		return stateterritoryRpo.findAll();
	}
	
}