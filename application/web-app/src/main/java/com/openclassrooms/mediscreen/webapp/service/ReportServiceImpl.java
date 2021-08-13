package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ReportServiceImpl implements ReportService {

    /**
     * @see Logger
     */
    //private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);

    /**
     * @see WebClient
     */
    //private final WebClient webClientReport;

    /**
     * Public constructor.
   //  * @param webClientReport1 to call report microservice
     */
    public ReportServiceImpl(/*@Qualifier("getWebClientReport") final WebClient webClientReport1*/) {
        //webClientReport = webClientReport1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDiabetesAssessmentByPatient(Patient patient) {
        return "risk";
    }
}
