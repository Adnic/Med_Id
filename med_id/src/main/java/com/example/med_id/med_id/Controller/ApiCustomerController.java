package com.example.med_id.Med_Id.controller;



import com.example.med_id.Med_Id.Repository.CustomerRepo;
import com.example.med_id.Med_Id.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping ("/api")
public class ApiCustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> GetAllCustomer(){
        try
        {
            List<Customer> customer = this.customerRepo.findAll();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Customer>> GetCustomerById(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = this.customerRepo.findById(id);
            if (customer.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(customer, HttpStatus.OK);
                return rest;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<Object> SaveCustomer(@RequestBody Customer customer) {
        Customer customerData = this.customerRepo.save(customer);
        try {
            customer.setCreatedOn(new Date());
            customer.setCreatedBy(1L);
            customer.setDelete(false);
            this.customerRepo.save(customer);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Object> UpdateCustomer(@RequestBody Customer customer,
                                                 @PathVariable("id") Long id){
        Optional<Customer> customerData = this.customerRepo.findById(id);
        if(customerData.isPresent()){
            customer.setId(id);
            customer.setModifiedBy(1L);
            customer.setModifiedOn(new Date());
            this.customerRepo.save(customer);

            ResponseEntity responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
            return responseEntity;
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/deletecustomer/{id}")
    public ResponseEntity<Object> DeleteCustomer( @RequestBody Customer customer,
                                                  @PathVariable("id") Long id){
        Optional<Customer> customerData = this.customerRepo.findById(id);
        if (customerData.isPresent()){
            customer.setId(id);
            customer.setDeletedBy(1L);
            customer.setDeletedOn(new Date());

            this.customerRepo.save(customer);

            ResponseEntity responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
            return responseEntity;
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/deletecustomer/{id}")
    public ResponseEntity<List<Customer>> GetDeleteCustomerById(@PathVariable ("id") Long id){
        try{
            Optional<Customer> customer = this.customerRepo.findById(id);
            if(customer.isPresent()){
                ResponseEntity responseEntity = new ResponseEntity<>( customer, HttpStatus.OK);
                return responseEntity;
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

//    //Search
//    @GetMapping("searchcustomer/{name}")
//    public ResponseEntity<List<Customer>> SearchCustomerByName(@PathVariable("name") String name){
//        if (name != null){
//            List<Customer> customers = this.customerRepo.SearchCustomer(name);
//            return new ResponseEntity<>(customers, HttpStatus.OK);
//        } else {
//            List<Customer> customers = this.customerRepo.findAll();
//            return new ResponseEntity<>(customers, HttpStatus.OK);
//        }
//    }
}
