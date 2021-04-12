package com.example.med_id.Med_Id.controller;
import com.example.med_id.Med_Id.models.DoctorTreatment;
import com.example.med_id.Med_Id.repository.DoctorTreatmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiDoctorTreatmentController {
@Autowired
private DoctorTreatmentRepo doctorTreatmentRepo;

@GetMapping("/doctortreatment")
    public ResponseEntity<Object> GetAllDoctorTreatment()
    {
        try
        {
            List<DoctorTreatment> doctorTreatment = this.doctorTreatmentRepo.findAll();
            return new ResponseEntity<>(doctorTreatment, HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/doctortreatment")
    public ResponseEntity<Object> SaveDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment)
        {
//            DoctorTreatment doctorTreatmentData = this.doctorTreatmentRepo.save(doctorTreatment);
            try
            {
                 doctorTreatment.setCreatedOn(new Date());
                doctorTreatment.setCreatedBy(1L);
                doctorTreatment.setDelete(false);
                 this.doctorTreatmentRepo.save(doctorTreatment);
                 return new ResponseEntity<>("Success", HttpStatus.OK);
            } catch (Exception e)
            {
                return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
            }
        }


    @GetMapping("doctortreatment/{id}")
    public ResponseEntity<List<DoctorTreatment>> GetDoctorTreatmentById(@PathVariable("id") Long id)
        {
            try
            {
                Optional<DoctorTreatment> doctorTreatment = this.doctorTreatmentRepo.findById(id);
                if (doctorTreatment.isPresent())
                {
                    ResponseEntity rest = new ResponseEntity<>(doctorTreatment, HttpStatus.OK);
                    return rest;
                } else
                    {
                        return ResponseEntity.notFound().build();
                    }
            } catch (Exception e)
                {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
        }

    @PutMapping("/doctortreatment{id}")
    public ResponseEntity<Object> UpdateDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment,
                                                        @PathVariable("id") Long id)
    {
        Optional<DoctorTreatment> doctorTreatmentData = this.doctorTreatmentRepo.findById(id);
        if (doctorTreatmentData.isPresent())
            {
                doctorTreatment.setId(id);
                doctorTreatment.setModifiedOn(new Date());
                doctorTreatment.setModifiedBy(1L);
                this.doctorTreatmentRepo.save(doctorTreatment);
                ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
                return rest;
            } else
                {
                    return ResponseEntity.notFound().build();
                }
    }

    @PutMapping("/deletedoctortreatment/{id}")
    public ResponseEntity<Object> DeleteDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment,
                                                        @PathVariable("id") Long id)
    {
        Optional<DoctorTreatment> doctorTreatmentData = this.doctorTreatmentRepo.findById(id);
        if (doctorTreatmentData.isPresent())
        {
            doctorTreatment.setId(id);
            doctorTreatment.setModifiedOn(new Date());
            doctorTreatment.setModifiedBy(1L);
            doctorTreatment.setDelete(true);
//            doctorTreatment.setDeletedBy(1);
            this.doctorTreatmentRepo.save(doctorTreatment);
            ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
            return rest;
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/deletedoctortreatment/{id}")
    public ResponseEntity<List<DoctorTreatment>> GetDeleteDoctorTreatmentById(@PathVariable ("id") Long id){
        try{
            Optional<DoctorTreatment> doctorTreatment = this.doctorTreatmentRepo.findById(id);
            if(doctorTreatment.isPresent()){
                ResponseEntity responseEntity = new ResponseEntity<>( doctorTreatment, HttpStatus.OK);
                return responseEntity;
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
