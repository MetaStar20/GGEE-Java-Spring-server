package com.wissensalt.rnd.springbootimagegallery.controller;

import com.wissensalt.rnd.springbootimagegallery.dao.IGgeeDAO;
import com.wissensalt.rnd.springbootimagegallery.dao.IWfmDAO;
import com.wissensalt.rnd.springbootimagegallery.data.Ggee;
import com.wissensalt.rnd.springbootimagegallery.data.Wfm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
@Controller
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private IGgeeDAO ggeeDAO;

    @Autowired
    private IWfmDAO wfmDAO;


    @Value("${upload.dir}")
    private String uploadDir;

    @GetMapping("/upload")
    public String display(Model p_Model) {
        return "upload";
    }

    @Transactional
    @PostMapping("/process-upload")
    public String processUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(path, bytes);

            Ggee ggee = new Ggee();
            ggee.setImg_path("/images/"+file.getOriginalFilename());
            ggee.setCreated_time(HelperUtils.getCurrentTimeUsingDate());
            ggee.setId_request(0000);
            ggee.setId_method(1000);
            ggeeDAO.save(ggee);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "An Error Occured " + e.toString() + "'");
            LOGGER.error("Error occured {}", e.toString());
        }
        return "redirect:upload";
    }
}
