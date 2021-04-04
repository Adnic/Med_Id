package com.example.med_id.med_id.Repository;
import com.example.med_id.med_id.models.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiodataRepo extends JpaRepository<Biodata, Long> {

}
