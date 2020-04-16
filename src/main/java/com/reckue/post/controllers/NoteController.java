package com.reckue.post.controllers;

import com.reckue.post.models.entities.Note;
import com.reckue.post.models.requests.NoteRequest;
import com.reckue.post.models.transfers.NoteTransfer;
import com.reckue.post.services.NoteService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public NoteTransfer getNoteById(@PathVariable String id) {
        final Note note = noteService.getNoteById(id);
        return mapper.map(note, NoteTransfer.class);
    }

    @GetMapping
    public List<NoteTransfer> getAllNotes() {
        final List<Note> noteList = noteService.getAllNotes();
        return noteList.stream()
                .map(note -> mapper.map(note, NoteTransfer.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public NoteTransfer createNote(@RequestBody NoteRequest noteRequest) {
        final Note noteToCreate = mapper.map(noteRequest, Note.class);
        final Note savedNote = noteService.createNote(noteToCreate);
        return mapper.map(savedNote, NoteTransfer.class);
    }

    @PutMapping("/{id}")
    public NoteTransfer editNote(@RequestBody NoteRequest noteRequest, @PathVariable String id) {
        final Note noteToUpdate = mapper.map(noteRequest, Note.class);
        final Note updatedNote = noteService.editNote(id, noteToUpdate);
        return mapper.map(updatedNote, NoteTransfer.class);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
    }
}
