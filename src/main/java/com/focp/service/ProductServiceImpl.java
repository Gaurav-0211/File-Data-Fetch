package com.focp.service;

import com.focp.Repo.ProductRepo;
import com.focp.dto.Helper;
import com.focp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void save(MultipartFile file) {
        try {
            List<Product> products = Helper.convertExcelToList(file.getInputStream());
            this.productRepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }
}
