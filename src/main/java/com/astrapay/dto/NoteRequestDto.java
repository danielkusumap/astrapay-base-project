package com.astrapay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NoteRequestDto {
    @NotNull(message = "title cannot be null")
    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be blank")
    private String description;
}
