package com.example.med_id.Med_Id.repository;

import com.example.med_id.Med_Id.models.CustomerRegisteredCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRegisteredCardRepo extends JpaRepository<CustomerRegisteredCard, Long> {
}
