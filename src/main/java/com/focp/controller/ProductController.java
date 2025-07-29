package com.focp.controller;

import com.focp.dto.Helper;
import com.focp.entity.Product;
import com.focp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
        if(Helper.checkExcelFormat(file)){
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("Message","File is uploaded and saved to db"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
        }
    }

    @GetMapping("/product")
    public List<Product> getAllProduct(){
        return this.productService.getAllProducts();
    }

}
