package com.focp.controller;

import com.focp.Repo.FileRepo;
import com.focp.config.ResourceNotFoundException;
import com.focp.entity.FileEntity;
import com.focp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    public FileService fileService;

    @Autowired
    private FileRepo fileRepo;


    private String path = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
        }
        if(!file.getContentType().equals("pdf/xlsx")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only PDF and Excel file type allowed");
        }
        else if (file.getSize() < 40 * 1024) {
            return ResponseEntity.badRequest().body("File is too small. Minimum size is 40KB");
        }
        else {
            String fileName = this.fileService.uploadFile(path, file);
            return ResponseEntity.ok("File uploaded Successfully" + fileName);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        try {
            FileEntity fileEntity = fileRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("File", "File Id",0));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename(fileEntity.getFileName())
                    .build());

            return new ResponseEntity<>(fileEntity.getData(), headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

}
