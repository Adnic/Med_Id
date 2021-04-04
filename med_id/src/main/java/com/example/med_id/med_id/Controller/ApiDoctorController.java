package com.example.med_id.med_id.Controller;

import com.example.med_id.med_id.Repository.DoctorRepo;
import com.example.med_id.med_id.models.Doctor;
import jdk.jfr.events.ExceptionThrownEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiDoctorController {

    @Autowired
    private DoctorRepo doctorRepo;

    @GetMapping("/doctor")
    public ResponseEntity<List<Doctor>> GetAllDoctor(){
        try {
            List<Doctor> doctor  = this.doctorRepo.findAll();
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/doctor")
    public ResponseEntity<Object> SaveDoctor(@RequestBody Doctor doctor) {
        Doctor doctorData = this.doctorRepo.save(doctor);
        try {
            doctor.setCreatedOn(new Date());
            doctor.setCreatedBy(1);
            this.doctorRepo.save(doctor);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<Doctor>> GetDoctorById(@PathVariable("id")Long id) {
        try {
            Optional<Doctor> doctor = this.doctorRepo.findById(id);
            if (doctor.isPresent()) {
                ResponseEntity rest = new ResponseEntity(doctor, HttpStatus.OK);
                return rest;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("doctormapped")
    public ResponseEntity<Map<String, Object>> GetAllpage(@RequestParam(defaultValue = "0")int page,
                                                          @RequestParam(defaultValue = "5")int size)
    {
        try {
            List<Doctor> doctor = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);
            Page<Doctor> pageTuts;
            pageTuts = doctorRepo.findAll(pagingSort);
            doctor = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("doctor", doctor);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/doctor/{id}")
    public ResponseEntity<Object> UpdateDoctor(@RequestBody Doctor doctor, @PathVariable("id") Long id) {
        Optional<Doctor> doctorData = this.doctorRepo.findById(id);
        if (doctorData.isPresent()) {
            doctor.setId(id);
            doctor.setModifiedOn(new Date());
            doctor.setModifiedBy(1);
            this.doctorRepo.save(doctor);
            ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
            return rest;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deletedoctor/{id}")
    public ResponseEntity<Object> DeleteDoctor(@RequestBody Doctor doctor, @PathVariable("id") Long id) {
        Optional<Doctor> doctorData = this.doctorRepo.findById(id);
        if (doctorData.isPresent()) {
            doctor.setId(id);
            doctor.setModifiedOn(new Date());
            doctor.setModifiedBy(1);
            doctor.setDelete(true);

            this.doctorRepo.save(doctor);

            ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
            return rest;
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
