package com.syscode.StudentCatalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "student", schema="public")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;

    @Email
    @NotNull
    @NonNull
    @Column(nullable = false)
    private String email;

}
