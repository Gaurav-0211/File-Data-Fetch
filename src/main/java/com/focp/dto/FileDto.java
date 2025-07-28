package com.focp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class FileDto {
    private long id;
    private String fileName;
//    @Column(length = 10000)
//    private byte[] data;
}
