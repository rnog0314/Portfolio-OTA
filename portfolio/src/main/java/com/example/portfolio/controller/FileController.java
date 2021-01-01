package com.example.portfolio.controller;

import java.util.Base64;

import com.example.portfolio.model.entity.FileDB;

// import java.util.List;
// import java.util.stream.Collectors;

// import com.example.portfolio.model.entity.FileDB;
import com.example.portfolio.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/portfolio/file")
public class FileController {

  @Autowired
  private FileStorageService storageService;

  @GetMapping(value="/index")
  public String index(Model m) {
    String path = "";
    m.addAttribute("path", path);
    return "file_upload";
  }


  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file, Model m) {
    String message = "";
    try {
      FileDB savedFile = storageService.store(file);
      byte[] bytes = savedFile.getData();
      String image =  Base64.getEncoder().encodeToString(bytes);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      m.addAttribute("message", message);
      m.addAttribute("image", image);
      return "file_upload";
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      m.addAttribute("message", message);
      return "file_upload";
    }
  }



}
