package com.example.med_id.med_id.Repository;

import com.example.med_id.med_id.models.DoctorTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorTreatmentRepo extends JpaRepository<DoctorTreatment, Long> {
    @Query("FROM DoctorTreatment WHERE isDelete = FALSE ORDER By modifiedOn DESC")
    List<DoctorTreatment> GetAllDoctorTreatmentList();

}
