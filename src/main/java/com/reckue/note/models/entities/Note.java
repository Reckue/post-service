package com.reckue.note.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {

    @Id
    private UUID id;
    private String payload;
}
