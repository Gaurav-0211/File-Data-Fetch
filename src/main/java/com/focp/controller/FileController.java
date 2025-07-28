package com.focp.controller;

import com.focp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    public FileService fileService;


    private String path = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
        }else {
            String fileName = this.fileService.uploadFile(path, file);
            return ResponseEntity.ok("File uploaded Successfully" + fileName);
        }
    }
}
