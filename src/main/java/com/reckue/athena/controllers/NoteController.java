package com.reckue.athena.controllers;

import com.reckue.athena.models.Note;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @GetMapping
    public Note getNoteById() {
        return new Note("Empty note");
    }
}
