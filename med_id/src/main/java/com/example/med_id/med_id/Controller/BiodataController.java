package com.example.med_id.med_id.Controller;

import com.example.med_id.med_id.Repository.BiodataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/biodata")
public class BiodataController {


    @Autowired
    private BiodataRepo biodataRepo;
    @GetMapping(value = "index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("biodata/index");
        return view;
    }
}
