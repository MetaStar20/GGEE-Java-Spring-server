package com.wissensalt.rnd.springbootimagegallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.wissensalt.rnd.springbootimagegallery.dao.IGgeeDAO;
import com.wissensalt.rnd.springbootimagegallery.dao.IWfmDAO;
import com.wissensalt.rnd.springbootimagegallery.data.Ggee;
import com.wissensalt.rnd.springbootimagegallery.data.Wfm;
import org.springframework.transaction.annotation.Transactional;
import com.wissensalt.rnd.springbootimagegallery.utils.HelperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created on 5/17/21.
 *
 * Pritesh
 */
@Controller
public class IndexController {

   private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IGgeeDAO ggeeDAO;

    @Autowired
    private IWfmDAO wfmDAO;    

    @GetMapping("/")
    public String display(Model p_Model) {
        p_Model.addAttribute("imageData", ggeeDAO.findByIdMethod(1000));
        return "index";
    }

    @Transactional
    @GetMapping("/start")
    public String start() {
            // init parameter
            int id_request = 0;
            int id_method  = 1000;
            int resolution = 0;
            int intervals  = 1000;
            int extent     = 60;

            // save WFM request into the server database.
            Wfm newAction = new Wfm();
            newAction.setId_method(id_method);
            newAction.setResolution(resolution);
            newAction.setIntervals(intervals);
            newAction.setExtent(extent);
            newAction.setCreated_time(HelperUtils.getCurrentTimeUsingDate());
            wfmDAO.save(newAction);

            // set parameter.
            id_request =  newAction.getId();
            // send the request to the google glass.
            HelperUtils.sendStartPostRequest(id_request,id_method,resolution,intervals,extent);
        return "redirect:";
    }

    @Transactional
    @GetMapping("/stop")
    public String stop() {
        // set parameter
        int id_request = 0;
        int id_method  = 1200;

        // save WFM request into the server database.   
        Wfm newAction = new Wfm();
        newAction.setId_method(id_method);
        newAction.setCreated_time(HelperUtils.getCurrentTimeUsingDate());
        wfmDAO.save(newAction);

        // set parameter.
        id_request =  newAction.getId();

        // send the request to the google glass.
        HelperUtils.sendStopPostRequest(id_request,id_method);

        return "redirect:";
    }

}
