package com.jonfriend.playdatenow_v03b.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.playdatenow_v03b.models.UserMdl;

@Repository
public interface UserRpo extends CrudRepository<UserMdl, Long> {
    
    Optional<UserMdl> findByEmail(String email);
   
    Optional<UserMdl> findByUserName(String userName);     // adding to enforce username, too
    
    List<UserMdl> findAll();
    
    UserMdl findByIdIs(Long id);
    
    
    // end of rpo   
}