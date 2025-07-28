package com.focp.service;
import com.focp.Repo.FileRepo;
import com.focp.config.ResourceNotFoundException;
import com.focp.dto.FileDto;
import com.focp.entity.FileEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileRepo fileRepo;
    
    @Autowired
    private ModelMapper mapper;

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();

        String newFileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf(".")));

        String fullPath = path + File.separator + newFileName;

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Files.copy(file.getInputStream(), Paths.get(fullPath));

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(newFileName);
        fileEntity.setData(file.getBytes());
        FileEntity savedEntity = this.fileRepo.save(fileEntity);
        FileDto savedDto = this.mapper.map(fileEntity, FileDto.class);

        return "File saved with ID: " + savedDto.getId();
    }


    @Override
    public byte[] downloadFile(Long fileId) throws IOException {
        FileEntity file = fileRepo.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File ", "File Id",fileId));
        return  file.getData();
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator +fileName;

        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
