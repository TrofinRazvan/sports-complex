package com.springboot.sportscomplex.repositories;

import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @Query("SELECT u FROM UserEntity u WHERE u.phoneNumber = ?1")
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}
