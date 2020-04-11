package com.reckue.note.services;

import com.reckue.note.models.entities.Note;
import com.reckue.note.repositories.NoteRepository;
import com.reckue.note.utils.NoteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Note getNoteById(String id) {
        return noteRepository.findById(id).orElseThrow();
    }

    @Override
    public Note createNote(Note note) {
        Note validNote = NoteValidator.validateNote(note);
        return noteRepository.save(validNote);
    }

    @Override
    public Note editNote(String id, Note note) {
        Note noteToUpdate = noteRepository.findById(id).orElseThrow();
        noteToUpdate.setPayload(note.getPayload());
        Note validNote = NoteValidator.validateNote(noteToUpdate);
        return noteRepository.save(validNote);
    }

    @Override
    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }
}
