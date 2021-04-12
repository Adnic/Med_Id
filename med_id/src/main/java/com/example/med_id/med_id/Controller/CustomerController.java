package com.example.med_id.Med_Id.controller;


import com.example.med_id.Med_Id.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/customer/")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping(value = "index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("customer/index");
        return view;
    }
}
