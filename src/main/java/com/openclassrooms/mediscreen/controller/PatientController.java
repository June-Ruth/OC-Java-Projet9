package com.openclassrooms.mediscreen.controller;

import com.openclassrooms.mediscreen.model.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PatientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private PatientService patientService;

    public PatientController(final PatientService patientService1) {
        patientService = patientService1;
    }

    @RequestMapping("/patients/list")
    public String getAllPatients(final Model model) {
        LOGGER.info("Getting all patients.");
        List<Patient> patientList = patientService.findAllPatients();
        model.addAttribute("patientList", patientList);
        return "patients/list";
    }


    /*
    @RequestMapping("/bidList/list")
    public String home(final Model model) {
        List<BidList> result = bidListService.findAllBidList();
        model.addAttribute("bidListList", result);
        LOGGER.info("Find all bid list, size = " + result.size());
        return "bidList/list";
    }
     */

}
