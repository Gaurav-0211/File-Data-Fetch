package com.focp.repo;

import com.focp.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileEntity, Integer> {
}
