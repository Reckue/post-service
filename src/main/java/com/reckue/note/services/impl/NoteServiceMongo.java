package com.reckue.note.services.impl;

import com.reckue.note.models.entities.Note;
import com.reckue.note.repositories.NoteRepository;
import com.reckue.note.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NoteServiceMongo implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceMongo(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(UUID id) {
        return noteRepository.findById(id).orElseThrow();
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note editNote(UUID id, Note note) {
        Note noteToUpdate = noteRepository.findById(id).orElseThrow();
        noteToUpdate.setPayload(note.getPayload());
        return noteRepository.save(noteToUpdate);
    }

    @Override
    public void deleteNote(UUID id) {
        noteRepository.deleteById(id);
    }
}
