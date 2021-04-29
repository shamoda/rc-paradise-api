package com.api.rc_paradise_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rc_paradise_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}