package com.reckue.note.controllers;

import com.reckue.note.models.transfers.NoteDescription;
import com.reckue.note.services.NoteStorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final NoteStorageService noteStorageService;

    public StorageController(NoteStorageService noteStorageService) {
        this.noteStorageService = noteStorageService;
    }

    @GetMapping("/{id}")
    public NoteDescription getNoteDescription(@PathVariable String id) {
        return noteStorageService.getNoteDescriptionById(id);
    }

    //TODO:: Get all note descriptions by userId
}
