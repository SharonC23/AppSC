package com.miApp.AppS.repository;

import com.miApp.AppS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserRepository findByEmail(String email);
    UserRepository findById(long id);

}

