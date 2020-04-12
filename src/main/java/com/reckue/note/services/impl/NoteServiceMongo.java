package com.reckue.note.services.impl;

import com.reckue.note.exceptions.NoteNotFoundException;
import com.reckue.note.models.entities.Note;
import com.reckue.note.repositories.NoteRepository;
import com.reckue.note.services.NoteService;
import com.reckue.note.utils.NoteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException("Note Not Found by id", HttpStatus.NOT_FOUND));
    }

    @Override
    public Note createNote(Note note) {
        Note validNote = NoteValidator.validateNote(note);
        return noteRepository.save(validNote);
    }

    @Override
    public Note editNote(String id, Note note) {
        Note noteToUpdate = noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException("Note Not Found by id", HttpStatus.NOT_FOUND));
        noteToUpdate.setPayload(note.getPayload());
        Note validNote = NoteValidator.validateNote(noteToUpdate);
        return noteRepository.save(validNote);
    }

    @Override
    public void deleteNote(String id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException("Note Not Found by id", HttpStatus.NOT_FOUND);
        }
        noteRepository.deleteById(id);
    }
}
