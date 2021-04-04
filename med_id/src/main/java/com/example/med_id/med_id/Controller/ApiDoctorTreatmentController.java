package com.example.med_id.med_id.Controller;

import com.example.med_id.med_id.Repository.DoctorTreatmentRepo;
import com.example.med_id.med_id.models.DoctorTreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiDoctorTreatmentController {


    @Autowired
    private DoctorTreatmentRepo doctorTreatmentRepo;


    @GetMapping("/doctortreatment")
    public ResponseEntity<List<DoctorTreatment>> GetAllDoctorTreatmentList() {
        try {
            List<DoctorTreatment> doctorTreatment = this.doctorTreatmentRepo.findAll();
            return new ResponseEntity<>(doctorTreatment, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/doctortreatment/{id}")
    public ResponseEntity<List<DoctorTreatment>> GetDoctorTreatmentById(@PathVariable("id") Long id) {
        try {
            Optional<DoctorTreatment> doctorTreatment = this.doctorTreatmentRepo.findById(id);
            if (doctorTreatment.isPresent()) {
                ResponseEntity responseEntity = new ResponseEntity<>(doctorTreatment, HttpStatus.OK);
                return responseEntity;
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/doctortreatment")
    public ResponseEntity<Object> SaveDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment) {
//       DoctorTreatment doctorTreatmentData = this.doctorTreatmentRepo.save(doctorTreatment);

        try {
            doctorTreatment.setCreatedBy(1);
            doctorTreatment.setCreatedOn(new Date());
            this.doctorTreatmentRepo.save(doctorTreatment);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/doctortreatment/{id}")
    public ResponseEntity<Object> UpdateDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment,
                                                       @PathVariable("id") Long id) {
        Optional<DoctorTreatment> doctorTreatmentData = this.doctorTreatmentRepo.findById(id);
        if (doctorTreatmentData.isPresent()) {
            doctorTreatment.setId(id);
            doctorTreatment.setModifiedBy(1);
            doctorTreatment.setModifiedOn(new Date());
            this.doctorTreatmentRepo.save(doctorTreatment);

            ResponseEntity responseEntity = new ResponseEntity<>("Succes", HttpStatus.OK);
            return responseEntity;
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/deletedoctortreatment/{id}")
    public ResponseEntity<Object> DeleteDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment,
                                                       @PathVariable("id") Long id) {
        Optional<DoctorTreatment> doctorTreatmentData = this.doctorTreatmentRepo.findById(id);
        if (doctorTreatmentData.isPresent()) {
            doctorTreatment.setId(id);
            doctorTreatment.setDeletedBy(1);
            doctorTreatment.setDeletedOn(new Date());
            doctorTreatment.setDelete(true);

            this.doctorTreatmentRepo.save(doctorTreatment);

            ResponseEntity responseEntity = new ResponseEntity<>("Succes", HttpStatus.OK);
            return responseEntity;
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/deletedoctortreatment/{id}")
    public ResponseEntity<List<DoctorTreatment>> GetDeleteDoctorTreatmentById(@PathVariable("id") Long id) {
        try {
            Optional<DoctorTreatment> educationLevel = this.doctorTreatmentRepo.findById(id);
            if (educationLevel.isPresent()) {
                ResponseEntity responseEntity = new ResponseEntity<>(educationLevel, HttpStatus.OK);
                return responseEntity;
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

//    //search
//    @GetMapping("searchdoctortreatment/{name}")
//    public ResponseEntity<List<DoctorTreatment>> SearchDoctorTreatmentByName(@PathVariable("name") String name) {
//        if (name != null) {
//            List<DoctorTreatment> doctorTreatments = this.doctorTreatmentRepo.SearchDoctorTreatment(name);
//            return new ResponseEntity<>(educationLevels, HttpStatus.OK);
//        } else {
//            List<EducationLevel> educationLevels = this.educationLevelRepo.findAll();
//            return new ResponseEntity<>(educationLevels, HttpStatus.OK);
//        }
//    }
}
