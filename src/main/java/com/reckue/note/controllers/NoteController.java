package com.reckue.note.controllers;

import com.reckue.note.models.entities.Note;
import com.reckue.note.models.forms.NoteForm;
import com.reckue.note.models.transfers.NoteTransfer;
import com.reckue.note.services.NoteService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Qualifier("noteServiceMongo")
    private final NoteService noteService;

    private final Mapper mapper;

    @Autowired
    public NoteController(NoteService noteService, Mapper mapper) {
        this.noteService = noteService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public NoteTransfer getNoteById(@PathVariable UUID id) {
        Note note = noteService.getNoteById(id);
        return mapper.map(note, NoteTransfer.class);
    }

    @GetMapping("/")
    public List<NoteTransfer> getAllNotes() {
        List<Note> noteList = noteService.getAllNotes();
        return noteList.stream()
                .map(note -> mapper.map(note, NoteTransfer.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public NoteTransfer createNote(@RequestBody NoteForm noteForm) {
        Note noteToCreate = mapper.map(noteForm, Note.class);
        Note savedNote = noteService.createNote(noteToCreate);
        return mapper.map(savedNote, NoteTransfer.class);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable UUID id) {
        noteService.deleteNote(id);
    }
}
