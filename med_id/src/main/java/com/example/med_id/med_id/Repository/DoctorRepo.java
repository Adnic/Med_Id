package com.example.med_id.Med_Id.repository;

import com.example.med_id.Med_Id.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
