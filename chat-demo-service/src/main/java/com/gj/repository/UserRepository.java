package com.gj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gj.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

}
