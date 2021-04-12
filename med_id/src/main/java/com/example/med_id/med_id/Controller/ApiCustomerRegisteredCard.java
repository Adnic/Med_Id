package com.example.med_id.Med_Id.controller;

import com.example.med_id.Med_Id.models.CustomerRegisteredCard;
import com.example.med_id.Med_Id.repository.CustomerRegisteredCardRepo;
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
public class ApiCustomerRegisteredCard {

    @Autowired
    private CustomerRegisteredCardRepo customerRegisteredCardRepo;

    @GetMapping("/customerregistered")
    public ResponseEntity<List<CustomerRegisteredCard>> GetAllCustomerRegisteredCard()
    {
        try
        {
            List<CustomerRegisteredCard> customerRegisteredCard = this.customerRegisteredCardRepo.findAll();
            return new ResponseEntity<>(customerRegisteredCard, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("customerregistered")
    public ResponseEntity<Object> SaveCustomerRegisteredCard(@RequestBody CustomerRegisteredCard customerRegisteredCard)
    {
//        CustomerRegisteredCard customerRegisteredCardData = this.customerRegisteredCardRepo.save(customerRegisteredCard);
        try
        {
            customerRegisteredCard.setCreatedOn(new Date());
            customerRegisteredCard.setDelete(false);
            this.customerRegisteredCardRepo.save(customerRegisteredCard);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("customerregistered/{id}")
    public ResponseEntity<CustomerRegisteredCard> GetCustomerRegisteredCard(@PathVariable("id") Long id)
    {
        try
        {
            Optional<CustomerRegisteredCard> customerRegisteredCard = this.customerRegisteredCardRepo.findById(id);
            if (customerRegisteredCard.isPresent())
            {
                ResponseEntity rest = new ResponseEntity<>(customerRegisteredCard, HttpStatus.OK);
                return rest;
            }
            else
            {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
@PutMapping("/customerregistered{id}")
    public ResponseEntity<Object> UpdateCustomerRegisteredCard (@RequestBody CustomerRegisteredCard customerRegisteredCard,
                                                                @PathVariable("id") Long id)
    {
        Optional<CustomerRegisteredCard> customerRegisteredCardData = this.customerRegisteredCardRepo.findById(id);
        if (customerRegisteredCardData.isPresent())
        {
            customerRegisteredCard.setId(id);
            customerRegisteredCard.setModifiedOn(new Date());
            this.customerRegisteredCardRepo.save(customerRegisteredCard);
            ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
            return rest;
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("deletecustomerregistered/{id}")
    public ResponseEntity<Object> DeleteCardRegisteredCard(@RequestBody CustomerRegisteredCard customerRegisteredCard,
                                                           @PathVariable("id") Long id)
        {
            Optional<CustomerRegisteredCard> customerRegisteredCardData = this.customerRegisteredCardRepo.findById(id);
            if (customerRegisteredCardData.isPresent())
            {
                customerRegisteredCard.setId(id);
                customerRegisteredCard.setModifiedOn(new Date());
                customerRegisteredCard.setDelete(true);
                this.customerRegisteredCardRepo.save(customerRegisteredCard);
                ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
                return rest;
            } else
            {
                return ResponseEntity.notFound().build();
            }
        }
}
