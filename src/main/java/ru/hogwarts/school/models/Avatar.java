package ru.hogwarts.school.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "avatar")
@Data
public class Avatar {
    @Id
    @GeneratedValue
    Long id;
    String filePath;
    long fileSize;
    String mediaType;
    @Lob
    byte[] data;
    @OneToOne
    @JoinColumn(name = "student_id")
    Student student;

}
