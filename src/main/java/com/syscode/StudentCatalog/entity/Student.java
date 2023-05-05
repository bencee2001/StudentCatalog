package com.syscode.StudentCatalog;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student", schema="public")
@Data
public class Student {

    @Id
    private Long id;
}
