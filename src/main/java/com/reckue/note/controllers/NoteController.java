package com.reckue.note.controllers;

import com.reckue.note.models.Note;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @GetMapping
    public Note getNoteById() {
        return new Note(
                UUID.randomUUID().toString().replace("-", ""),
                "<b>Plan:</b><br>1. Java Core<br>2. Spring Framework",
                null);
    }
}
