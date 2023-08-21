package com.example.demoViettel2.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "classes")
public class classStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "class_name")
    private String className;
}
