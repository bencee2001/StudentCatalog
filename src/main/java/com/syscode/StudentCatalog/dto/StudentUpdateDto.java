package com.syscode.StudentCatalog.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDto {
    private String name;

    @Email
    private String email;
}
