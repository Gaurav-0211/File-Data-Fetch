package com.focp.controller;

import com.focp.dto.FileDto;
import com.focp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    public FileService fileService;

    @Value("${project.file}")
    private String path;

    @PostMapping
    public ResponseEntity<FileDto> uploadFile(@RequestParam("file")MultipartFile file, @PathVariable Integer fileId) throws IOException {
        FileDto fileDto = this.fileService.getFileById(fileId);

        String fileName = this.fileService.uploadFile(path, file);
        fileDto.setFileName(fileName);
    }
}
