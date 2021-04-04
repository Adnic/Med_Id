package com.example.med_id.med_id.Controller;
import com.example.med_id.med_id.Repository.BiodataRepo;
import com.example.med_id.med_id.models.Biodata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;
@RestController
@Controller ("*")
@RequestMapping ("/api")
public class ApiBiodataController {

    @Autowired
    private BiodataRepo biodataRepo;

    @GetMapping(value = "index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("biodata/index");
        return view;
    }

    @GetMapping("/biodata")
    public ResponseEntity<List<Biodata>> GetAllBiodata() {
        try {
            List<Biodata> biodata = this.biodataRepo.findAll();
            return new ResponseEntity<>(biodata, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/biodata/{id}")
    public ResponseEntity<List<Biodata>> GetBiodataById(@PathVariable("id") Long id) {
        try {
            Optional<Biodata> biodata = this.biodataRepo.findById(id);
            if (biodata.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(biodata, HttpStatus.OK);
                return rest;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/biodata")
    public ResponseEntity<Object> SaveBiodata(@RequestBody Biodata biodata) {
        Biodata biodataData = this.biodataRepo.save(biodata);
        try {
            biodata.setCreatedOn(new Date());
            biodata.setCreatedBy(1);
            this.biodataRepo.save(biodata);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/biodata/{id}")
    public ResponseEntity<Object> UpdateBiodata(@RequestBody Biodata biodata,
                                                @PathVariable("id") Long id) {
       Optional <Biodata> biodataData = this.biodataRepo.findById(id);

        try {
            biodata.setCreatedBy(1);
            biodata.setCreatedOn(new Date());
            this.biodataRepo.save(biodata);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}

