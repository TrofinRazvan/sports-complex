package com.springboot.sportscomplex.repositories;

import com.springboot.sportscomplex.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = "SELECT u.first_name, u.last_name FROM users u JOIN users_subscriptions us ON u.id = us.users_id JOIN subscriptions s ON us.subscriptions_id = s.id WHERE u.phone_number = :phoneNumber", nativeQuery = true)
//    @Query(value = "SELECT u.first_name, u.last_name FROM users u WHERE u.phone_number = :phoneNumber", nativeQuery = true)
    Optional<User> findUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    List<User> findUserByFirstNameAndLastNameAndAddress(String firstName, String lastName, String Address);
    User findById(String id);
    long count();
}