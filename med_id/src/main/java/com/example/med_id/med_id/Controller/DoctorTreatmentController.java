package com.example.med_id.med_id.Controller;

import com.example.med_id.med_id.Repository.DoctorTreatmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/doctortreatment/")
public class DoctorTreatmentController {

    @Autowired
    private DoctorTreatmentRepo doctorTreatmentRepo;

    @GetMapping(value = "index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("doctortreatment/index");
        return view;
    }
}
