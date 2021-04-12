package com.example.med_id.Med_Id.repository;

import com.example.med_id.Med_Id.models.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiodataRepo extends JpaRepository<Biodata, Long> {

}
