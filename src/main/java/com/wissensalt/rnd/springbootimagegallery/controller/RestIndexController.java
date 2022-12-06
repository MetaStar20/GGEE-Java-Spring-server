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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wissensalt.rnd.springbootimagegallery.utils.HelperUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 5/17/21.
 *
 * Pritesh
 */
@RestController
@RequestMapping("/api")
public class RestIndexController {

    @Autowired
    private IGgeeDAO ggeeDAO;

    @Autowired
    private IWfmDAO wfmDAO;

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping("/getstatus")
    public Wfm getstatus() {
         return wfmDAO.findTopByOrderByIdDesc();
    }

    @PostMapping("/upload")
    public String processUpload(@RequestParam("file")MultipartFile file, @RequestParam("id_request") final String id_request) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(path, bytes);

            Ggee ggee = new Ggee();
            ggee.setImg_path("/images/"+file.getOriginalFilename());
            ggee.setCreated_time(HelperUtils.getCurrentTimeUsingDate());
            ggee.setId_request(Integer.valueOf(id_request));
            ggee.setId_method(1000);
            ggeeDAO.save(ggee);

        }catch (Exception e) {
            return "fail";
        }
        return "success";
    }


    @PostMapping("/stop")
    public String stop(@RequestParam("stopped") final String stopped, @RequestParam("id_request") final String id_request) {
        try {
            Ggee ggee = new Ggee();
            ggee.setCreated_time(HelperUtils.getCurrentTimeUsingDate());
            ggee.setStopped(Integer.valueOf(stopped));
            ggee.setId_request(Integer.valueOf(id_request));
            ggee.setId_method(1200);
            ggeeDAO.save(ggee);

        }catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
