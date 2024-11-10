package com.pranav.vms.repository;

import com.pranav.vms.model.User;  
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface user_repo extends MongoRepository<User, String> {

}
