package com.personalprojects.pocconsultacep.repository;

import com.personalprojects.pocconsultacep.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}