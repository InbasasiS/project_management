package com.assignments.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignments.projectmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
