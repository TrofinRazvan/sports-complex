package com.springboot.sportscomplex.repositories;

import com.springboot.sportscomplex.models.entities.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SmsEntity, Long> {

}
