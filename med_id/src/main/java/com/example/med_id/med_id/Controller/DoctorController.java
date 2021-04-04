package com.example.med_id.med_id.Controller;

import com.example.med_id.med_id.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepo doctorRepo;

    @GetMapping(value = "index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("doctor/index");
        return view;
    }
}
