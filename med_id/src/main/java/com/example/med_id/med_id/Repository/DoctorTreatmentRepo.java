package com.example.med_id.Med_Id.repository;

import com.example.med_id.Med_Id.models.DoctorTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorTreatmentRepo extends JpaRepository<DoctorTreatment, Long> {
}
