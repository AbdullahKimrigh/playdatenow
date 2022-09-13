package com.jonfriend.playdatenow_v03b.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.playdatenow_v03b.models.PlaydateMdl;

@Repository
public interface PlaydateRpo extends CrudRepository<PlaydateMdl, Long> {
	
	List<PlaydateMdl> findAll();
	
	PlaydateMdl findByIdIs(Long id);
	
	
}
