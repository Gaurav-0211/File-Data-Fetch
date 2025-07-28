package com.focp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "uploaded-files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileName;

    @Lob
    @Column(length = 10000)
    private byte[] data;
}
