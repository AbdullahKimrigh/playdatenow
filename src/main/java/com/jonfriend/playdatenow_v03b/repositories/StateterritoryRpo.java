package com.jonfriend.playdatenow_v03b.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.playdatenow_v03b.models.StateterritoryMdl;


@Repository
public interface StateterritoryRpo extends CrudRepository<StateterritoryMdl, Long> {
	
	List<StateterritoryMdl> findAll();
	
}
